package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.Contact;
import cl.streamlink.contact.domain.Developer;
import cl.streamlink.contact.domain.Resource;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.DeveloperRepository;
import cl.streamlink.contact.repository.ResourceRepository;
import cl.streamlink.contact.utils.FakerService;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.*;
import cl.streamlink.contact.web.dto.*;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResourceService {

    private final Logger logger = LoggerFactory.getLogger(ResourceService.class);

    @Inject
    private ResourceRepository resourceRepository;

    @Inject
    private DeveloperRepository developerRepository;

    @Inject
    private UserService userService;

    @Inject
    private DeveloperService developerService;

    @Inject
    private ApiMapper mapper;

    public ResourceDTO createResource(ResourceDTO resourceDTO) {

        //Retrieve Object from resourceDTO
        Resource resource = mapper.fromDTOToBean(resourceDTO);
        //Generate Reference Key & store data to resource
        resource.setReference("res" + MiscUtils.generateReference());
        resource.setStage(Stage.ConvertedToResource);
        //Create Resource
        mapper.fromBeanToDTO(resourceRepository.save(resource));

        //Create new user with Resource credentials
        UserDTO user=new UserDTO();
        user.setPassword("Stream2023");
        user.setFirstname(resource.getFirstname());
        user.setLastname(resource.getLastname());
        user.setRoles(new ArrayList<>(Collections.singletonList(Role.ROLE_RESOURCE)));
        user.setEmail(resourceDTO.getEmail());
        //Save User with resource information
        userService.signup(user);

        //Search Developer by Reference
        Developer res = developerRepository.findOneByReference(resource.getReference())
                        .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", resource.getReference()));

        //Store email into Contact
        ContactDTO contact = new ContactDTO();
        contact.setEmail1(resourceDTO.getEmail());

        //Update Developer details
        developerService.updateDeveloperContact(contact,resource.getReference());
        return resourceDTO;
    }

    public ResourceDTO createResourceFromDeveloper(String developerReference) {

        if (!resourceRepository.existsByReference(developerReference)) {
            Developer developer = developerRepository.findOneByReference(developerReference)

                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

            Resource resource = mapper.fromDeveloperToResource(developer);
            resource.setResourceStage(ResourceStage.NOT_DEFINED);
            resource.setResourceType(ResourceType.NOT_DEFINED);
            resource.setStage(Stage.ConvertedToResource);

            developerRepository.delete(developer);
            return mapper.fromBeanToDTO(resourceRepository.save(resource));
        } else
            throw ContactApiException.unprocessableEntityExceptionBuilder("resource-exist", null);
    }


    public List<ResourceResponseDTO> searchResources(String term) {

        if (MiscUtils.isEmpty(term) || term.length() < 3)
            return Collections.EMPTY_LIST;

        else return resourceRepository.findByFirstnameContaining(term).stream()
                .map(resource -> mapper.fromBeanToDTOResponse(resource))
                .collect(Collectors.toList());
    }


    public ResourceDTO createResourceFromDeveloper(String developerReference, DeveloperDTO developerDTO) {

        if (!resourceRepository.existsByReference(developerReference)) {
            Developer developer = developerRepository.findOneByReference(developerReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

            mapper.updateBeanFromDto(developerDTO, developer);

            Resource resource = mapper.fromDeveloperToResource(developer);
            resource.setResourceStage(ResourceStage.NOT_DEFINED);
            resource.setResourceType(ResourceType.NOT_DEFINED);
            resource.setStage(Stage.ConvertedToResource);

            developerRepository.delete(developer);
            return mapper.fromBeanToDTO(resourceRepository.save(resource));
        } else
            throw ContactApiException.unprocessableEntityExceptionBuilder("resource-exist", null);
    }

    public ResourceDTO updateResource(ResourceDTO resourceDTO, String resourceReference) throws ContactApiException {

        Resource resource = resourceRepository.findOneByReference(resourceReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Resource", resourceReference));

        mapper.updateBeanFromDto(resourceDTO, resource);
        return mapper.fromBeanToDTO(resourceRepository.save(resource));
    }

    public ResourceDTO getResource(String resourceReference) throws ContactApiException {

        return mapper.fromBeanToDTO(resourceRepository.findOneByReference(resourceReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Resource", resourceReference)));
    }


    public List<ResourceResponseDTO> getResources(String resourceReference) throws ContactApiException {

        if (MiscUtils.isNotEmpty(resourceReference))
            return Collections.singletonList(mapper.fromBeanToDTOResponse(resourceRepository.findOneByReference(resourceReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Resource", resourceReference))));

        else
            return resourceRepository.findAll().stream().map(mapper::fromBeanToDTOResponse).collect(Collectors.toList());
    }

    public Page<ResourceResponseDTO> searchResources(String value, ResourceStage stage, Experience experience, Formation formation, ResourceType type, Pageable pageable) {

        if (MiscUtils.isEmpty(value))
            value = "";

        List<ResourceStage> stages;
        if (stage != null)
            stages = Collections.singletonList(stage);
        else
            stages = ResourceStage.getAll();

        List<Experience> experiences;
        if (experience != null)
            experiences = Collections.singletonList(experience);
        else
            experiences = Experience.getAll();

        List<Formation> formations;
        if (formation != null)
            formations = Collections.singletonList(formation);
        else
            formations = Formation.getAll();

        List<ResourceType> types;
        if (type != null)
            types = Collections.singletonList(type);
        else
            types = ResourceType.getAll();

        return resourceRepository.
                findByFirstnameContainingAndResourceStageInAndSkillsInformationFormationInAndSkillsInformationExperienceInAndResourceTypeInOrLastnameContainingAndResourceStageInAndSkillsInformationFormationInAndSkillsInformationExperienceInAndResourceTypeInOrSkillsInformationTitleContainingAndResourceStageInAndSkillsInformationFormationInAndSkillsInformationExperienceInAndResourceTypeInOrSkillsInformationLanguagesContainingAndResourceStageInAndSkillsInformationFormationInAndSkillsInformationExperienceInAndResourceTypeIn
                        (value, stages, formations, experiences, types, value, stages, formations, experiences, types, value, stages, formations, experiences, types, value, stages, formations, experiences, types, pageable)
                .map(resource -> mapper.fromBeanToDTOResponse(resource));
    }

    public JSONObject deleteResource(String resourceReference) throws ContactApiException {

        Resource resource = resourceRepository.findOneByReference(resourceReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Resource", resourceReference));

        resourceRepository.delete(resource);

        return MiscUtils.createSuccessfullyResult();
    }


}
