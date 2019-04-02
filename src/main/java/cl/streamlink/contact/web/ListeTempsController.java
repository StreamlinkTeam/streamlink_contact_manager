package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.ListeTemps;
import cl.streamlink.contact.domain.SocietyContact;
import cl.streamlink.contact.domain.TempsLine;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.ListeTempsService;
import cl.streamlink.contact.web.dto.ListeTempsDTO;
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
@RequestMapping("/ws/ListeTemps")
public class ListeTempsController {
    @Inject
    private ListeTempsService listeTempsService;


    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    @ResponseStatus(HttpStatus.OK)

    @ApiOperation(value = "Get Project Details Service")

    @ApiResponses(value = {

            @ApiResponse(code = 200, message = "Operation Executed Successfully",
                    response = ListeTemps.class),

            @ApiResponse(code = 404, message = "listeTemps with Ref not Found")})
    public ListeTempsDTO getListeTemps(@RequestParam(value = "listeTempsReference") String listeTempsReference) throws ContactApiException {

        return listeTempsService.getListeTemps(listeTempsReference);


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

        return listeTempsService.deleteListeTemps(listeTempsReference);
    }


    @RequestMapping(value = "", method = RequestMethod.POST, produces =
            MediaType.APPLICATION_JSON_VALUE)

    @ResponseStatus(HttpStatus.OK)

    @ApiOperation(value = "Create TempsLine Service")

    @ApiResponses(value = {

            @ApiResponse(code = 200, message = "Operation Executed Successfully",
                    response = TempsLine.class),

            @ApiResponse(code = 400, message = "Validation Error, Database conflict")})

    public ListeTempsDTO createListeTemps(@RequestBody @Valid ListeTempsDTO
                                                  listeTemps) {

        return listeTempsService.createListeTemps(listeTemps);
    }


}
