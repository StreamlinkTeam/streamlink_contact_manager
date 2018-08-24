package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.Action;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.ActionService;
import cl.streamlink.contact.web.dto.DeveloperActionDTO;
import cl.streamlink.contact.web.dto.SocietyActionDTO;
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
@RestController
@RequestMapping("/ws")
public class ActionController {

    @Inject
    private ActionService actionService;



    @RequestMapping(value = "developers/actions",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Action Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Action.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public DeveloperActionDTO createDeveloperAction(@RequestBody @Valid DeveloperActionDTO action,
                                           @RequestParam(value = "developerReference") String developerReference)
    {

        return actionService.createAction(action,developerReference);
    }

    @RequestMapping(value = "developers/actions",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Action Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Action.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public DeveloperActionDTO updateDeveloperAction(@Valid @RequestBody DeveloperActionDTO action, @RequestParam(value = "developerReference") String developerReference,
                                           @RequestParam(value = "reference") String reference) throws ContactApiException
    {

        return actionService.updateAction(action, reference,developerReference);
    }

    @RequestMapping(value = "developers/actions",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Action Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Action.class),
            @ApiResponse(code = 404, message = "Action with Ref not Found")
    })
    public List<DeveloperActionDTO> getDeveloperAction(@RequestParam(value = "developerReference") String developerReference,
                                              @RequestParam(value = "reference", required = false) String reference) throws ContactApiException
    {

        return actionService.getAction(reference,developerReference);
    }

    @RequestMapping(value = "developers/actions",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete Action Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Action.class),
            @ApiResponse(code = 404, message = "Action with Ref not Found")
    })
    public JSONObject deleteDeveloperAction(@RequestParam("reference") String reference
            ,@RequestParam(value = "developerReference") String developerReference) throws ContactApiException
    {

        return actionService.deleteAction(reference,developerReference);
    }

    @RequestMapping(value = "societies/contacts/actions",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Action Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Action.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public SocietyActionDTO createDeveloperAction(@RequestBody @Valid SocietyActionDTO action,
                                                    @RequestParam String societyContactReference,@RequestParam  String societyReference)
    {

        return actionService.createAction(action,societyContactReference,societyReference);
    }

    @RequestMapping(value = "societies/contacts/actions",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Action Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Action.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public SocietyActionDTO updateDeveloperAction(@Valid @RequestBody SocietyActionDTO action, @RequestParam String societyContactReference,@RequestParam  String societyReference,
                                                    @RequestParam(value = "reference") String reference) throws ContactApiException
    {

        return actionService.updateAction(action, reference,societyContactReference,societyReference);
    }

    @RequestMapping(value = "societies/contacts/actions",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Action Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Action.class),
            @ApiResponse(code = 404, message = "Action with Ref not Found")
    })
    public List<SocietyActionDTO> getDeveloperAction(@RequestParam String societyContactReference,@RequestParam  String societyReference,
                                                       @RequestParam(value = "reference", required = false) String reference) throws ContactApiException
    {

        return actionService.getAction(reference,societyContactReference,societyReference);
    }

    @RequestMapping(value = "societies/contacts/actions",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete Action Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Action.class),
            @ApiResponse(code = 404, message = "Action with Ref not Found")
    })
    public JSONObject deleteDeveloperAction(@RequestParam("reference") String reference
            ,@RequestParam String societyContactReference,@RequestParam  String societyReference) throws ContactApiException
    {

        return actionService.deleteAction(reference,societyContactReference,societyReference);
    }
}
