package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.Action;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.ActionService;
import cl.streamlink.contact.web.dto.ActionDTO;
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
 * Created by Radouen on 13/07/2018.
 */
@RestController
@RequestMapping("/ws/developers/actions")
public class ActionController {

    @Inject
    private ActionService actionService;



    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Action Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Action.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public ActionDTO createAction(@RequestBody @Valid ActionDTO action,
                                          @RequestParam(value = "developerReference") String developerReference)
    {

        return actionService.createAction(action,developerReference);
    }

    @RequestMapping(value = "",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Action Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Action.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public ActionDTO updateAction(@Valid @RequestBody ActionDTO action, @RequestParam(value = "developerReference") String developerReference,
                                          @RequestParam(value = "reference") String reference) throws ContactApiException
    {

        return actionService.updateAction(action, reference,developerReference);
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Action Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Action.class),
            @ApiResponse(code = 404, message = "Action with Ref not Found")
    })
    public List<ActionDTO> getAction(@RequestParam(value = "developerReference") String developerReference,
                                             @RequestParam(value = "reference", required = false) String reference) throws ContactApiException
    {

        return actionService.getAction(reference,developerReference);
    }

    @RequestMapping(value = "",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete Action Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Action.class),
            @ApiResponse(code = 404, message = "Action with Ref not Found")
    })
    public JSONObject deleteAction(@RequestParam("reference") String reference
            ,@RequestParam(value = "developerReference") String developerReference) throws ContactApiException
    {

        return actionService.deleteAction(reference,developerReference);
    }
}
