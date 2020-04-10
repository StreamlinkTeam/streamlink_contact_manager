package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.Contact;
import cl.streamlink.contact.domain.Developer;
import cl.streamlink.contact.domain.Resource;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.mapper.ResourceApiMapper;
import cl.streamlink.contact.repository.DeveloperRepository;
import cl.streamlink.contact.repository.ResourceRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.Experience;
import cl.streamlink.contact.utils.enums.Formation;
import cl.streamlink.contact.utils.enums.ResourceStage;
import cl.streamlink.contact.utils.enums.ResourceType;
import cl.streamlink.contact.web.dto.AvatarDTO;
import cl.streamlink.contact.web.dto.DeveloperDTO;
import cl.streamlink.contact.web.dto.ResourceDTO;
import cl.streamlink.contact.web.dto.ResourceResponseDTO;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ResourceService extends AbstractDevResService<Resource, ResourceRepository, ResourceDTO, ResourceResponseDTO, ResourceApiMapper> {

    private final Logger logger = LoggerFactory.getLogger(ResourceService.class);

    @Inject
    private PositioningService positioningService;

    @Inject
    private AvatarService avatarService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Inject
    private DeveloperRepository developerRepository;

    @Inject
    private AbsenceManageService absenceManageService;

    @Inject
    private ApiMapper mapper;


    @Override
    protected String getEntityName() {
        return "Resource";
    }

    @Override
    public ResourceResponseDTO generateDeveloperFromCv(MultipartFile cv) throws ContactApiException {
        throw ContactApiException.unprocessableEntityExceptionBuilder
                ("can not generate Resource From CV directly ,generate new developer instead", null);
    }

    @Override
    public ResourceDTO getResourceByEmail(String email) throws ContactApiException {
        return mapper.fromBeanToDTO(devResRepository.findOneByEmail(email).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder(getEntityName(), email)));
    }


    public ResourceDTO createResource(ResourceDTO resourceDTO) throws ContactApiException {

        checkIfEmailIsUsed(resourceDTO.getEmail(), null);
        resourceDTO.setPassword(passwordEncoder.encode(resourceDTO.getPassword()));

        Resource resource = mapper.fromDTOToBean(resourceDTO);
        Contact contact = new Contact();
        contact.setEmail1(resourceDTO.getEmail());
        resource.setContact(contact);
        resource.setReference("res" + MiscUtils.generateReference());

        return mapper.fromBeanToDTO(devResRepository.save(resource));
    }

    public ResourceDTO createResourceFromDeveloper(String developerReference) throws ContactApiException {

        if (!devResRepository.existsByReference(developerReference)) {
            Developer developer = developerRepository.findOneByReference(developerReference)

                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

            Resource resource = mapper.fromDeveloperToResource(developer);
            resource.setResourceStage(ResourceStage.NOT_DEFINED);
            resource.setResourceType(ResourceType.NOT_DEFINED);

            resource.setEmail(developer.getContact().getEmail1());
            resource.setPassword(passwordEncoder.encode("change-me"));

            developerRepository.delete(developer);
            return mapper.fromBeanToDTO(devResRepository.save(resource));
        } else
            throw ContactApiException.unprocessableEntityExceptionBuilder("resource-exist", null);
    }


    public ResourceDTO createResourceFromDeveloper(String developerReference, DeveloperDTO developerDTO) throws ContactApiException {

        if (!devResRepository.existsByReference(developerReference)) {
            Developer developer = developerRepository.findOneByReference(developerReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

            mapper.updateBeanFromDto(developerDTO, developer);

            checkIfEmailIsUsed(developer.getContact().getEmail1(), developerReference);

            Resource resource = mapper.fromDeveloperToResource(developer);
            resource.setResourceStage(ResourceStage.NOT_DEFINED);
            resource.setResourceType(ResourceType.NOT_DEFINED);
            resource.setEmail(developer.getContact().getEmail1());
            resource.setPassword(passwordEncoder.encode("change-me"));

            developerRepository.delete(developer);
            return mapper.fromBeanToDTO(devResRepository.save(resource));
        } else
            throw ContactApiException.unprocessableEntityExceptionBuilder("resource-exist", null);
    }

    public ResourceDTO updateResource(ResourceDTO resourceDTO, String resourceReference) throws ContactApiException {

        Resource resource = devResRepository.findOneByReference(resourceReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder(getEntityName(), resourceReference));

        checkIfEmailIsUsed(resourceDTO.getEmail(), resourceReference);

        mapper.updateBeanFromDto(resourceDTO, resource);
        return mapper.fromBeanToDTO(devResRepository.save(resource));
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

        return devResRepository.
                findByFirstnameContainingAndResourceStageInAndSkillsInformationFormationInAndSkillsInformationExperienceInAndResourceTypeInOrLastnameContainingAndResourceStageInAndSkillsInformationFormationInAndSkillsInformationExperienceInAndResourceTypeInOrSkillsInformationTitleContainingAndResourceStageInAndSkillsInformationFormationInAndSkillsInformationExperienceInAndResourceTypeInOrSkillsInformationLanguagesContainingAndResourceStageInAndSkillsInformationFormationInAndSkillsInformationExperienceInAndResourceTypeIn
                        (value, stages, formations, experiences, types, value, stages, formations, experiences, types, value, stages, formations, experiences, types, value, stages, formations, experiences, types, pageable)
                .map(resource -> mapper.fromBeanToDTOResponse(resource));
    }

    public JSONObject deleteResource(String resourceReference) throws ContactApiException {

        Resource resource = devResRepository.findOneByReference(resourceReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder(getEntityName(), resourceReference));

        positioningService.deletePositioningByResource(resourceReference);

        devResRepository.delete(resource);

        return MiscUtils.createSuccessfullyResult();
    }

    private void checkIfEmailIsUsed(String email, String reference) throws ContactApiException {

        if (MiscUtils.isNotEmpty(email)) {
            Optional<Resource> find = devResRepository.findOneByEmail(email).
                    filter(user -> MiscUtils.isEmpty(reference) || !user.getReference().equals(reference));
            if (find.isPresent()) {
                throw ContactApiException.unauthorizedExceptionBuilder("email_exist", null);
            }
        }

    }

    public AvatarDTO addResourceAvatar(MultipartFile file, String resourceReference) throws ContactApiException, IOException {

        Resource resource = devResRepository.findOneByReference(resourceReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Resource", resourceReference));

        return avatarService.updateUserAvatar(file, resource);

    }

    public JSONObject removeResourceAvatar(String reference, String resourceReference) throws ContactApiException {

        Resource resource = devResRepository.findOneByReference(resourceReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Resource", resourceReference));
        return avatarService.removeUserAvatar(reference, resource);

    }

    public AvatarDTO getResourceAvatar(String resourceReference) throws ContactApiException {
        Resource resource = devResRepository.findOneByReference(resourceReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Resource", resourceReference));

        return avatarService.getUserAvatar(resource);

    }
}
