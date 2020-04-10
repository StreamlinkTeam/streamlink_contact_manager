package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.AbstractDevResProfile;
import cl.streamlink.contact.domain.Action;
import cl.streamlink.contact.domain.Need;
import cl.streamlink.contact.domain.Project;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.*;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.ActionDTO;
import cl.streamlink.contact.web.dto.ResourceActionDTO;
import cl.streamlink.contact.web.dto.SocietyActionDTO;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chemakh on 13/07/2018.
 */

@Service
public class ActionService {

    private final Logger logger = LoggerFactory.getLogger(ActionService.class);

    @Inject
    private ActionRepository actionRepository;

    @Inject
    private ProjectRepository projectRepository;

    @Inject
    private NeedRepository needRepository;

    @Inject
    private SocietyContactRepository societyContactRepository;

    @Inject
    private SocietyRepository societyRepository;

    @Inject
    private UserService userService;

    @Inject
    private ApiMapper mapper;


    public List<ActionDTO> getActions() {
        return actionRepository.findAll().stream().map(mapper::fromBeanToDTO).collect(Collectors.toList());

    }


    ResourceActionDTO createResourceAction(ResourceActionDTO resourceActionDTO, AbstractDevResProfile devResProfile) throws ContactApiException {


        resourceActionDTO.setReference("act" + MiscUtils.generateReference());
        resourceActionDTO.setDeveloperReference(devResProfile.getReference());
        resourceActionDTO.setResponsibleReference(userService.getCurrentUser().getReference());

        Action action = mapper.fromDTOToBean(resourceActionDTO);

        return mapper.fromBeanToDeveloperActionDTO(actionRepository.save(action));
    }

    ResourceActionDTO updateResourceAction(ResourceActionDTO resourceActionDTO, String reference,
                                           AbstractDevResProfile devResProfile) throws ContactApiException {

        Action action = actionRepository.findOneByReferenceAndDeveloperReference(reference, devResProfile.getReference())
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

        mapper.updateBeanFromDto(resourceActionDTO, action);

        action = actionRepository.save(action);

        return mapper.fromBeanToDeveloperActionDTO(action);

    }

    List<ResourceActionDTO> getResourceAction(String reference, AbstractDevResProfile devResProfile) throws ContactApiException {

        if (MiscUtils.isEmpty(reference))
            return actionRepository.findByDeveloperReference(devResProfile.getReference()).stream()
                    .map(mapper::fromBeanToDeveloperActionDTO).collect(Collectors.toList());
        else {
            Action action = actionRepository.findOneByReferenceAndDeveloperReference(reference, devResProfile.getReference())
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

            return Collections.singletonList(mapper.fromBeanToDeveloperActionDTO(action));
        }
    }

    JSONObject deleteResourceAction(String reference, AbstractDevResProfile devResProfile) throws ContactApiException {

        Action action = actionRepository.findOneByReferenceAndDeveloperReference(reference, devResProfile.getReference())
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

        actionRepository.delete(action);
        return MiscUtils.createSuccessfullyResult();

    }

    public SocietyActionDTO createAction(SocietyActionDTO societyActionDTO, String societyContactReference,
                                         String societyReference) throws ContactApiException {

        societyRepository.findOneByReference(societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Society", societyReference));

        societyContactRepository.findOneByReferenceAndSocietyReference(societyContactReference, societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("SocietyContact",
                        societyContactReference));

        societyActionDTO.setReference(MiscUtils.generateReference());
        societyActionDTO.setSocietyContactReference(societyContactReference);
        societyActionDTO.setResponsibleReference(userService.getCurrentUser().getReference());

        Action action = mapper.fromDTOToBean(societyActionDTO);

        return mapper.fromBeanToSocietyActionDTO(actionRepository.save(action));
    }

    public SocietyActionDTO updateAction(SocietyActionDTO societyActionDTO, String reference,
                                         String societyContactReference, String societyReference) throws ContactApiException {

        societyRepository.findOneByReference(societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Society", societyReference));
        Action action;

        if (MiscUtils.isNotEmpty(societyContactReference)) {

            societyContactRepository.findOneByReferenceAndSocietyReference(societyContactReference, societyReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("SocietyContact",
                            societyContactReference));

            action = actionRepository
                    .findOneByReferenceAndSocietyContactReferenceAndProjectIsNull(reference, societyContactReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

        } else {
            societyContactRepository
                    .findOneByReferenceAndSocietyReference(societyActionDTO.getSocietyContactReference(),
                            societyReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("SocietyContact",
                            societyActionDTO.getSocietyContactReference()));

            action = actionRepository
                    .findOneByReferenceAndSocietyContactSocietyReferenceAndProjectIsNull(reference, societyReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));
        }

        mapper.updateBeanFromDto(societyActionDTO, action);

        action = actionRepository.save(action);

        return mapper.fromBeanToSocietyActionDTO(action);

    }

    public List<SocietyActionDTO> getAction(String reference, String societyContactReference, String societyReference) throws ContactApiException {

        societyRepository.findOneByReference(societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Society", societyReference));

        if (MiscUtils.isEmpty(societyContactReference)) {
            return actionRepository.findBySocietyContactSocietyReferenceAndProjectIsNull(societyReference).stream()
                    .map(mapper::fromBeanToSocietyActionDTO).collect(Collectors.toList());
        } else {
            societyContactRepository.findOneByReferenceAndSocietyReference(societyContactReference, societyReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("SocietyContact",
                            societyContactReference));
            if (MiscUtils.isEmpty(reference)) {
                return actionRepository.findBySocietyContactReferenceAndProjectIsNull(societyContactReference).stream()
                        .map(mapper::fromBeanToSocietyActionDTO).collect(Collectors.toList());
            } else {
                Action action = actionRepository
                        .findOneByReferenceAndSocietyContactReferenceAndProjectIsNull(reference,
                                societyContactReference)
                        .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

                return Collections.singletonList(mapper.fromBeanToSocietyActionDTO(action));
            }
        }
    }

    public JSONObject deleteAction(String reference, String societyContactReference, String societyReference) throws ContactApiException {

        societyRepository.findOneByReference(societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Society", societyReference));

        societyContactRepository.findOneByReferenceAndSocietyReference(societyContactReference, societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("SocietyContact",
                        societyContactReference));

        Action action = actionRepository
                .findOneByReferenceAndSocietyContactReferenceAndProjectIsNull(reference, societyContactReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

        actionRepository.delete(action);
        return MiscUtils.createSuccessfullyResult();

    }

    public SocietyActionDTO createProjectAction(SocietyActionDTO societyActionDTO, String projectReference) throws ContactApiException {

        Project project = projectRepository.findOneByReference(projectReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Project", projectReference));

        societyActionDTO.setReference(MiscUtils.generateReference());
        societyActionDTO.setProjectReference(projectReference);
        societyActionDTO.setSocietyContactReference(project.getSocietyContact().getReference());
        societyActionDTO.setProjectReference(projectReference);
        societyActionDTO.setResponsibleReference(userService.getCurrentUser().getReference());

        Action action = mapper.fromDTOToBean(societyActionDTO);

        return mapper.fromBeanToSocietyActionDTO(actionRepository.save(action));
    }

    public SocietyActionDTO updateProjectAction(SocietyActionDTO societyActionDTO, String reference,
                                                String projectReference) throws ContactApiException {

        projectRepository.findOneByReference(projectReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Project", projectReference));

        Action action = actionRepository.findOneByReferenceAndProjectReference(reference, projectReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

        mapper.updateBeanFromDto(societyActionDTO, action);

        action = actionRepository.save(action);

        return mapper.fromBeanToSocietyActionDTO(action);
    }

    public List<SocietyActionDTO> getProjectAction(String reference, String projectReference) throws ContactApiException {

        projectRepository.findOneByReference(projectReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Project", projectReference));

        if (MiscUtils.isEmpty(reference))
            return actionRepository.findByProjectReference(projectReference).stream()
                    .map(mapper::fromBeanToSocietyActionDTO).collect(Collectors.toList());
        else {
            Action action = actionRepository.findOneByReferenceAndProjectReference(reference, projectReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

            return Collections.singletonList(mapper.fromBeanToSocietyActionDTO(action));
        }
    }

    public JSONObject deleteProjectAction(String reference, String projectReference) throws ContactApiException {

        projectRepository.findOneByReference(projectReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Project", projectReference));

        Action action = actionRepository.findOneByReferenceAndProjectReference(reference, projectReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

        actionRepository.delete(action);
        return MiscUtils.createSuccessfullyResult();
    }


    public SocietyActionDTO createNeedAction(SocietyActionDTO societyActionDTO, String needReference) throws ContactApiException {

        Need need = needRepository.findOneByReference(needReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Need", needReference));

        societyActionDTO.setReference(MiscUtils.generateReference());
        societyActionDTO.setNeedReference(needReference);
        societyActionDTO.setSocietyContactReference(need.getSocietyContact().getReference());
        societyActionDTO.setNeedReference(needReference);
        societyActionDTO.setResponsibleReference(userService.getCurrentUser().getReference());

        Action action = mapper.fromDTOToBean(societyActionDTO);

        return mapper.fromBeanToSocietyActionDTO(actionRepository.save(action));
    }

    public SocietyActionDTO updateNeedAction(SocietyActionDTO societyActionDTO, String reference,
                                             String needReference) throws ContactApiException {

        needRepository.findOneByReference(needReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Need", needReference));

        Action action = actionRepository.findOneByReferenceAndNeedReference(reference, needReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

        mapper.updateBeanFromDto(societyActionDTO, action);

        action = actionRepository.save(action);

        return mapper.fromBeanToSocietyActionDTO(action);
    }

    public List<SocietyActionDTO> getNeedAction(String reference, String needReference) throws ContactApiException {

        needRepository.findOneByReference(needReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Need", needReference));

        if (MiscUtils.isEmpty(reference))
            return actionRepository.findByNeedReference(needReference).stream()
                    .map(mapper::fromBeanToSocietyActionDTO).collect(Collectors.toList());
        else {
            Action action = actionRepository.findOneByReferenceAndNeedReference(reference, needReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

            return Collections.singletonList(mapper.fromBeanToSocietyActionDTO(action));
        }
    }

    public JSONObject deleteNeedAction(String reference, String needReference) throws ContactApiException {

        needRepository.findOneByReference(needReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Need", needReference));

        Action action = actionRepository.findOneByReferenceAndNeedReference(reference, needReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

        actionRepository.delete(action);
        return MiscUtils.createSuccessfullyResult();
    }
}
