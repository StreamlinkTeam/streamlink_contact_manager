package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.TimeList;
import cl.streamlink.contact.domain.SocietyContact;
import cl.streamlink.contact.domain.TimeLine;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.TimeListService;
import cl.streamlink.contact.web.dto.TimeListDTO;
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
@RequestMapping("/ws/time_list")
public class TimeListController {
    @Inject
    private TimeListService timeListService;


    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    @ResponseStatus(HttpStatus.OK)

    @ApiOperation(value = "Get Project Details Service")

    @ApiResponses(value = {

            @ApiResponse(code = 200, message = "Operation Executed Successfully",
                    response = TimeList.class),

            @ApiResponse(code = 404, message = "listeTemps with Ref not Found")})
    public TimeListDTO getListeTemps(@RequestParam(value = "listeTempsReference") String listeTempsReference) throws ContactApiException {

        return timeListService.getListeTemps(listeTempsReference);


    }


    @RequestMapping(value = "",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete ligne temps Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = SocietyContact.class),
            @ApiResponse(code = 404, message = "listeTemps with Ref not Found")
    })
    public JSONObject deleteLigneTemps(@RequestParam("listeTempsReference") String listeTempsReference) throws ContactApiException {

        return timeListService.deleteListeTemps(listeTempsReference);
    }


    @RequestMapping(value = "", method = RequestMethod.POST, produces =
            MediaType.APPLICATION_JSON_VALUE)

    @ResponseStatus(HttpStatus.OK)

    @ApiOperation(value = "Create TimeLine Service")

    @ApiResponses(value = {

            @ApiResponse(code = 200, message = "Operation Executed Successfully",
                    response = TimeLine.class),

            @ApiResponse(code = 400, message = "Validation Error, Database conflict")})

    public TimeListDTO createListeTemps(@RequestBody @Valid TimeListDTO listeTemps) {

        return timeListService.createListeTemps(listeTemps);
    }


}
