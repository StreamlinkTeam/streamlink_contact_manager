package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.Action;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.ActionRepository;
import cl.streamlink.contact.repository.DeveloperRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.ActionDTO;
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
    private UserService userService;


    @Inject
    private ApiMapper mapper;

    public ActionDTO createAction(ActionDTO actionDTO, String developerReference) {

        developerRepository.findOneByReference(developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

        actionDTO.setReference(MiscUtils.generateReference());
        actionDTO.setDeveloperReference(developerReference);
        actionDTO.setResponsibleReference(userService.getCurrentUser().getReference());

        Action action = mapper.fromDTOToBean(actionDTO);

        return mapper.fromBeanToDTO(actionRepository.save(action));
    }

    public ActionDTO updateAction(ActionDTO actionDTO, String reference, String developerReference) {

        developerRepository.findOneByReference(developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

        Action action = actionRepository.findOneByReferenceAndDeveloperReference(reference, developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

        mapper.updateBeanFromDto(actionDTO, action);

        action = actionRepository.save(action);

        return mapper.fromBeanToDTO(action);

    }

    public List<ActionDTO> getAction(String reference, String developerReference) {

        developerRepository.findOneByReference(developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

        if (MiscUtils.isEmpty(reference))
            return actionRepository.findByDeveloperReference(developerReference).
                    stream().map(mapper::fromBeanToDTO).collect(Collectors.toList());
        else {
            Action action = actionRepository.findOneByReferenceAndDeveloperReference(reference, developerReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Action", reference));

            return Collections.singletonList(mapper.fromBeanToDTO(action));
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
}
