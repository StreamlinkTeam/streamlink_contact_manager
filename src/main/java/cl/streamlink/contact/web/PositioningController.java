package cl.streamlink.contact.web;


import cl.streamlink.contact.domain.Positioning;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.DeveloperService;
import cl.streamlink.contact.service.PositioningService;
import cl.streamlink.contact.service.ProjectService;
import cl.streamlink.contact.service.UserService;
import cl.streamlink.contact.utils.Constants;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.NeedStage;
import cl.streamlink.contact.utils.enums.NeedType;
import cl.streamlink.contact.utils.enums.PositioningStage;
import cl.streamlink.contact.utils.enums.ResourceType;
import cl.streamlink.contact.web.dto.PositioningDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ws/positionings")
public class PositioningController {

    @Inject
    private PositioningService positioningService;
    @Inject
    private UserService userService;
    @Inject
    private DeveloperService developerService;

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Positioning Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Positioning.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public PositioningDTO createPositioning(@RequestBody @Valid PositioningDTO positioning) throws ContactApiException {

        return positioningService.createPositioning(positioning);

    }

    @RequestMapping(value = "",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Upadte Positioning Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Positioning.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public PositioningDTO updatePositioning(@Valid @RequestBody PositioningDTO positioning, @RequestParam String positioningReference)
            throws ContactApiException {

        return positioningService.updatePositioning(positioning, positioningReference);
    }

    @RequestMapping(value = "current",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Positioning Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Positioning.class),
            @ApiResponse(code = 404, message = "Positioning with Ref not Found")
    })
    @Secured(Constants.ROLE_RESOURCE)
    public List<PositioningDTO> getCurrentResourcePositioning() throws ContactApiException {
        return positioningService.getCurrentResourcePositioning();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Page<PositioningDTO> getPositionings(Pageable pageable, @RequestParam boolean fromAngular,
                                                @RequestParam(required = false) String value,
                                                @RequestParam(required = false) NeedStage needStage,
                                                @RequestParam(required = false) NeedType needType,
                                                @RequestParam(required = false) ResourceType type,
                                                @RequestParam(required = false) PositioningStage stage,
                                                @RequestParam(required = false) Sort.Direction dir) {

        if (fromAngular) {

            pageable = MiscUtils.convertFromAngularPage(pageable, dir, false);

        }

        return positioningService.searchPositioning(value, needStage, needType, type, stage, pageable);

    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Positioning Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = PositioningDTO.class),
            @ApiResponse(code = 404, message = "Positioning with Ref not Found")
    })
    public PositioningDTO getPositioning(@RequestParam String positioningReference) throws ContactApiException {
        return positioningService.getPositioning(positioningReference);
    }

    @RequestMapping(value = "",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete Positioning Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Positioning.class),
            @ApiResponse(code = 404, message = "Positioning with Ref not Found")
    })
    public JSONObject deletePositioning(@RequestParam String positioningReference) throws ContactApiException {

        return positioningService.deletePositioning(positioningReference);
    }


    @GetMapping(value = "current")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Positioning Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = PositioningDTO.class),
            @ApiResponse(code = 404, message = "Positioning with Ref not Found")
    })
    public List<PositioningDTO> getNeedUser() throws ContactApiException {

        String developerReference = userService.getCurrentUser().getReference();

        return positioningService.getPositioningResource(developerReference);
    }



}
