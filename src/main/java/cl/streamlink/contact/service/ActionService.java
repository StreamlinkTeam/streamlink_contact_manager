package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.Action;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.ActionRepository;
import cl.streamlink.contact.repository.DeveloperRepository;
import cl.streamlink.contact.repository.SocietyContactRepository;
import cl.streamlink.contact.repository.SocietyRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.DeveloperActionDTO;
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

    private final Logger logger = LoggerFactory.getLogger(DeveloperService.class);

    @Inject
    private ActionRepository actionRepository;

    @Inject
    private DeveloperRepository developerRepository;

    @Inject
    private SocietyContactRepository societyContactRepository;

    @Inject
    private SocietyRepository societyRepository;

    @Inject
    private UserService userService;


    @Inject
    private ApiMapper mapper;

    public DeveloperActionDTO createAction(DeveloperActionDTO developerActionDTO, String developerReference) {

        developerRepository.findOneByReference(developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

        developerActionDTO.setReference(MiscUtils.generateReference());
        developerActionDTO.setDeveloperReference(developerReference);
        developerActionDTO.setResponsibleReference(userService.getCurrentUser().getReference());

        Action action = mapper.fromDTOToBean(developerActionDTO);

        return mapper.fromBeanToDeveloperActionDTO(actionRepository.save(action));
    }

    public DeveloperActionDTO updateAction(DeveloperActionDTO developerActionDTO, String reference, String developerReference) {

        developerRepository.findOneByReference(developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

        Action action = actionRepository.findOneByReferenceAndDeveloperReference(reference, developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

        mapper.updateBeanFromDto(developerActionDTO, action);

        action = actionRepository.save(action);

        return mapper.fromBeanToDeveloperActionDTO(action);

    }

    public List<DeveloperActionDTO> getAction(String reference, String developerReference) {

        developerRepository.findOneByReference(developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

        if (MiscUtils.isEmpty(reference))
            return actionRepository.findByDeveloperReference(developerReference).
                    stream().map(mapper::fromBeanToDeveloperActionDTO).collect(Collectors.toList());
        else {
            Action action = actionRepository.findOneByReferenceAndDeveloperReference(reference, developerReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

            return Collections.singletonList(mapper.fromBeanToDeveloperActionDTO(action));
        }
    }

    public JSONObject deleteAction(String reference, String developerReference) {

        developerRepository.findOneByReference(developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

        Action action = actionRepository.findOneByReferenceAndDeveloperReference(reference, developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

        actionRepository.delete(action);
        return MiscUtils.createSuccessfullyResult();


    }


    public SocietyActionDTO createAction(SocietyActionDTO societyActionDTO, String societyContactReference, String societyReference) {

        societyRepository.findOneByReference(societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Society", societyReference));

        societyContactRepository.findOneByReferenceAndSocietyReference(societyContactReference, societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("SocietyContact", societyContactReference));

        societyActionDTO.setReference(MiscUtils.generateReference());
        societyActionDTO.setSocietyContactReference(societyContactReference);
        societyActionDTO.setResponsibleReference(userService.getCurrentUser().getReference());

        Action action = mapper.fromDTOToBean(societyActionDTO);

        return mapper.fromBeanToSocietyActionDTO(actionRepository.save(action));
    }

    public SocietyActionDTO updateAction(SocietyActionDTO societyActionDTO, String reference, String societyContactReference, String societyReference) {

        societyRepository.findOneByReference(societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Society", societyReference));
        Action action;

        if (MiscUtils.isNotEmpty(societyContactReference)) {

            societyContactRepository.findOneByReferenceAndSocietyReference(societyContactReference, societyReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("SocietyContact", societyContactReference));

            action = actionRepository.findOneByReferenceAndSocietyContactReference(reference, societyContactReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

        } else {
            societyContactRepository.findOneByReferenceAndSocietyReference(societyActionDTO.getSocietyContactReference(), societyReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("SocietyContact", societyActionDTO.getSocietyContactReference()));

            action = actionRepository.findOneByReferenceAndSocietyContactSocietyReference(reference, societyReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));
        }

        mapper.updateBeanFromDto(societyActionDTO, action);

        action = actionRepository.save(action);

        return mapper.fromBeanToSocietyActionDTO(action);

    }

    public List<SocietyActionDTO> getAction(String reference, String societyContactReference, String societyReference) {

        societyRepository.findOneByReference(societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Society", societyReference));


        if (MiscUtils.isEmpty(societyContactReference)) {
            return actionRepository.findBySocietyContactSocietyReference(societyReference).
                    stream().map(mapper::fromBeanToSocietyActionDTO).collect(Collectors.toList());
        } else {
            societyContactRepository.findOneByReferenceAndSocietyReference(societyContactReference, societyReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("SocietyContact", societyContactReference));
            if (MiscUtils.isEmpty(reference)) {
                return actionRepository.findBySocietyContactReference(societyContactReference).
                        stream().map(mapper::fromBeanToSocietyActionDTO).collect(Collectors.toList());
            } else {
                Action action = actionRepository.findOneByReferenceAndSocietyContactReference(reference, societyContactReference)
                        .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

                return Collections.singletonList(mapper.fromBeanToSocietyActionDTO(action));
            }
        }
    }

    public JSONObject deleteAction(String reference, String societyContactReference, String societyReference) {


        societyRepository.findOneByReference(societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Society", societyReference));

        societyContactRepository.findOneByReferenceAndSocietyReference(societyContactReference, societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("SocietyContact", societyContactReference));

        Action action = actionRepository.findOneByReferenceAndSocietyContactReference(reference, societyContactReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

        actionRepository.delete(action);
        return MiscUtils.createSuccessfullyResult();


    }
}
