package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.AbstractDevResProfile;
import cl.streamlink.contact.domain.Developer;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.DeveloperApiMapper;
import cl.streamlink.contact.repository.DeveloperRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.Experience;
import cl.streamlink.contact.utils.enums.Formation;
import cl.streamlink.contact.utils.enums.Stage;
import cl.streamlink.contact.web.dto.AbstractDevResProfileDTO;
import cl.streamlink.contact.web.dto.DeveloperDTO;
import cl.streamlink.contact.web.dto.DeveloperResponseDTO;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Service
@CrossOrigin("*")
public class DeveloperService extends AbstractDevResService<Developer, DeveloperRepository, DeveloperDTO, DeveloperResponseDTO, DeveloperApiMapper> {

    private final Logger logger = LoggerFactory.getLogger(DeveloperService.class);

    @Inject
    private ResourceService resourceService;

    @Override
    protected String getEntityName() {
        return "Developer";
    }

    @Override
    public DeveloperResponseDTO generateDeveloperFromCv(MultipartFile cv) throws ContactApiException {
        return curriculumVitaeService.generateDeveloperFromCv(cv);
    }

    public AbstractDevResProfileDTO<? extends AbstractDevResProfile> createResource(DeveloperDTO developerDTO) throws ContactApiException {

        if (developerDTO.getStage() == Stage.ConvertedToResource)
            return resourceService.createResource(apiMapper.fromDeveloperToResource(developerDTO));

        Developer developer = mapper.fromDTOToBean(developerDTO);

        developer.setReference(MiscUtils.generateReference());
        return mapper.fromBeanToDTO(devResRepository.save(developer));
    }

    public AbstractDevResProfileDTO<? extends AbstractDevResProfile> updateResource(DeveloperDTO developerDTO,
                                                                                    String developerReference)
            throws ContactApiException {

        Developer developer = devResRepository.findOneByReference(developerReference).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder(getEntityName(), developerReference));

        if (developerDTO.getStage() == Stage.ConvertedToResource && developer.getStage() != Stage.ConvertedToResource)
            return resourceService.createResourceFromDeveloper(developerReference, developerDTO);

        mapper.updateBeanFromDto(developerDTO, developer);
        return mapper.fromBeanToDTO(devResRepository.save(developer));
    }


    public Page<DeveloperResponseDTO> searchDevelopers(String value, Stage stage, Experience experience,
                                                       Formation formation, Pageable pageable) {

        if (MiscUtils.isEmpty(value))
            value = "";

        List<Stage> stages;
        if (stage != null)
            stages = Collections.singletonList(stage);
        else
            stages = Stage.getAll();

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

        return devResRepository
                .findByFirstnameContainingAndStageInAndSkillsInformationFormationInAndSkillsInformationExperienceInOrLastnameContainingAndStageInAndSkillsInformationFormationInAndSkillsInformationExperienceInOrSkillsInformationTitleContainingAndStageInAndSkillsInformationFormationInAndSkillsInformationExperienceInOrSkillsInformationLanguagesContainingAndStageInAndSkillsInformationFormationInAndSkillsInformationExperienceIn(
                        value, stages, formations, experiences,
                        value, stages, formations, experiences,
                        value, stages, formations, experiences,
                        value, stages, formations, experiences,
                        pageable)
                .map(developer -> mapper.fromBeanToDTOResponse(developer));
    }

    public JSONObject deleteResource(String developerReference) throws ContactApiException {

        Developer developer = devResRepository.findOneByReference(developerReference).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder(getEntityName(), developerReference));

        devResRepository.delete(developer);

        return MiscUtils.createSuccessfullyResult();
    }


    public DeveloperDTO getResourceByEmail(String email) throws ContactApiException {

        return mapper.fromBeanToDTO(devResRepository.findByContact_Email1(email).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder(getEntityName(), email)));
    }

}
