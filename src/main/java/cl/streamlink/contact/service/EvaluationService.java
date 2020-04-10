package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.AbstractDevResProfile;
import cl.streamlink.contact.domain.Evaluation;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.EvaluationRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.EvaluationDTO;
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
class EvaluationService {

    private final Logger logger = LoggerFactory.getLogger(DeveloperService.class);

    @Inject
    private EvaluationRepository evaluationRepository;

    @Inject
    private UserService userService;


    @Inject
    private ApiMapper mapper;

    EvaluationDTO createEvaluation(EvaluationDTO evaluationDTO, AbstractDevResProfile devResProfile) throws ContactApiException {


        evaluationDTO.setReference(MiscUtils.generateReference());
        evaluationDTO.setDeveloperReference(devResProfile.getReference());
        evaluationDTO.setResponsibleReference(userService.getCurrentUser().getReference());

        Evaluation evaluation = mapper.fromDTOToBean(evaluationDTO);

        return mapper.fromBeanToDTO(evaluationRepository.save(evaluation));
    }

    EvaluationDTO updateEvaluation(EvaluationDTO evaluationDTO, String reference, AbstractDevResProfile devResProfile) throws ContactApiException {


        Evaluation evaluation = evaluationRepository.findOneByReferenceAndDeveloperReference(reference, devResProfile.getReference())
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Evaluation", reference));

        mapper.updateBeanFromDto(evaluationDTO, evaluation);

        evaluation = evaluationRepository.save(evaluation);

        return mapper.fromBeanToDTO(evaluation);

    }

    List<EvaluationDTO> getEvaluation(String reference, AbstractDevResProfile devResProfile) throws ContactApiException {


        if (MiscUtils.isEmpty(reference))
            return evaluationRepository.findByDeveloperReference(devResProfile.getReference()).
                    stream().map(mapper::fromBeanToDTO).collect(Collectors.toList());
        else {
            Evaluation evaluation = evaluationRepository.findOneByReferenceAndDeveloperReference(reference, devResProfile.getReference())
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Evaluation", reference));

            return Collections.singletonList(mapper.fromBeanToDTO(evaluation));
        }
    }

    JSONObject deleteEvaluation(String reference, AbstractDevResProfile devResProfile) throws ContactApiException {

        Evaluation evaluation = evaluationRepository.findOneByReferenceAndDeveloperReference(reference, devResProfile.getReference())
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Evaluation", reference));

        evaluationRepository.delete(evaluation);
        return MiscUtils.createSuccessfullyResult();


    }
}
