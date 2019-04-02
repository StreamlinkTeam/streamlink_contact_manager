package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.TimeLine;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.TimeLineService;
import cl.streamlink.contact.web.dto.TimeLineDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;

@RestController
@RequestMapping("/ws/time_line")
public class TimeLineController {


    @Inject
    private TimeLineService timeLineService;

    @GetMapping

    @ResponseStatus(HttpStatus.OK)

    @ApiOperation(value = "Get ligneTemps Details Service")

    @ApiResponses(value = {

            // @ApiResponse(code = 200, message = "Operation Executed Successfully", response = TimeLine.class),

            @ApiResponse(code = 404, message = "ligneTemps with Ref not Found")})

    public TimeLineDTO getLigneTemps(@RequestParam(value = "ligneTempsReference") String ligneTempsReference) throws ContactApiException {
        return timeLineService.getLigne(ligneTempsReference);
    }


    @RequestMapping(value = "", method = RequestMethod.DELETE, produces =
            MediaType.APPLICATION_JSON_VALUE)

    @ResponseStatus(HttpStatus.OK)

    @ApiOperation(value = "Delete ligne Temps Service")

    @ApiResponses(value = {

            @ApiResponse(code = 200, message = "Operation Executed Successfully",
                    response = TimeLine.class),

            @ApiResponse(code = 404, message = "ligneTemps with Ref not Found")})
    public JSONObject deleteLigneTemps(@RequestParam("ligneTempsReference") String
                                               ligneTempsReference) throws ContactApiException {

        return timeLineService.deleteLigneTemps(ligneTempsReference);
    }


    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create TimeLine Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = TimeLine.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })

    public TimeLineDTO createLigneTemps(@RequestBody @Valid TimeLineDTO ligneTemps) {

        return timeLineService.createLigneTemps(ligneTemps);
    }

   @RequestMapping(value = "",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create ligne temps Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response =  TimeLine.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public TimeLineDTO updateProject(@Valid @RequestBody TimeLineDTO ligneTemps, @RequestParam(value = "ligneTempsReference") String ligneTempsReference) throws ContactApiException {

        return timeLineService.updateLigneTemps(ligneTemps,ligneTempsReference);

    }

}
