package cl.streamlink.contact.web.abstractdevres;

import cl.streamlink.contact.domain.AbstractDevResProfile;
import cl.streamlink.contact.domain.Evaluation;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.DevResMapper;
import cl.streamlink.contact.repository.AbstractDevResRepository;
import cl.streamlink.contact.service.AbstractDevResService;
import cl.streamlink.contact.web.dto.AbstractDevResProfileDTO;
import cl.streamlink.contact.web.dto.AbstractDevResResponseDTO;
import cl.streamlink.contact.web.dto.EvaluationDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by chemakh on 13/07/2018.
 */
public abstract class AbstractEvaluationController<E extends AbstractDevResProfile, T extends AbstractDevResRepository<E>, D extends AbstractDevResProfileDTO<E>,
        R extends AbstractDevResResponseDTO<E>, M extends DevResMapper<E, D, R>, S extends AbstractDevResService<E, T, D, R, M>> {

    @Inject
    private S devResService;


    @RequestMapping(value = "evaluations",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Evaluation Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Evaluation.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public EvaluationDTO createEvaluation(@RequestBody @Valid EvaluationDTO evaluation,
                                          @RequestParam(value = "developerReference") String developerReference) throws ContactApiException {

        return devResService.createEvaluation(evaluation, developerReference);
    }

    @RequestMapping(value = "evaluations",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Evaluation Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Evaluation.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public EvaluationDTO updateEvaluation(@Valid @RequestBody EvaluationDTO evaluation, @RequestParam(value = "developerReference") String developerReference,
                                          @RequestParam(value = "reference") String reference) throws ContactApiException {

        return devResService.updateEvaluation(evaluation, reference, developerReference);
    }

    @RequestMapping(value = "evaluations",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Evaluation Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Evaluation.class),
            @ApiResponse(code = 404, message = "Evaluation with Ref not Found")
    })
    public List<EvaluationDTO> getEvaluation(@RequestParam(value = "developerReference") String developerReference,
                                             @RequestParam(value = "reference", required = false) String reference) throws ContactApiException {

        return devResService.getEvaluation(reference, developerReference);
    }

    @RequestMapping(value = "evaluations",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete Evaluation Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Evaluation.class),
            @ApiResponse(code = 404, message = "Evaluation with Ref not Found")
    })
    public JSONObject deleteEvaluation(@RequestParam("reference") String reference
            , @RequestParam(value = "developerReference") String developerReference) throws ContactApiException {

        return devResService.deleteEvaluation(reference, developerReference);
    }
}
