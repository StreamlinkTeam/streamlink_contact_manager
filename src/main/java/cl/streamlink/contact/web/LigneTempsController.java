package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.TempsLine;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.LigneTempsService;
import cl.streamlink.contact.web.dto.LigneTempsDTO;
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
@RequestMapping("/ws/lignetemps")
public class LigneTempsController {


    @Inject
    private LigneTempsService ligneTempsService;

    @GetMapping

    @ResponseStatus(HttpStatus.OK)

    @ApiOperation(value = "Get ligneTemps Details Service")

    @ApiResponses(value = {

            // @ApiResponse(code = 200, message = "Operation Executed Successfully", response = TempsLine.class),

            @ApiResponse(code = 404, message = "ligneTemps with Ref not Found")})

    public LigneTempsDTO getLigneTemps(@RequestParam(value = "ligneTempsReference") String ligneTempsReference) throws ContactApiException {
        return ligneTempsService.getLigne(ligneTempsReference);
    }


    @RequestMapping(value = "", method = RequestMethod.DELETE, produces =
            MediaType.APPLICATION_JSON_VALUE)

    @ResponseStatus(HttpStatus.OK)

    @ApiOperation(value = "Delete ligne Temps Service")

    @ApiResponses(value = {

            @ApiResponse(code = 200, message = "Operation Executed Successfully",
                    response = TempsLine.class),

            @ApiResponse(code = 404, message = "ligneTemps with Ref not Found")})
    public JSONObject deleteLigneTemps(@RequestParam("ligneTempsReference") String
                                               ligneTempsReference) throws ContactApiException {

        return ligneTempsService.deleteLigneTemps(ligneTempsReference);
    }


    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create TempsLine Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = TempsLine.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })

    public LigneTempsDTO createLigneTemps(@RequestBody @Valid LigneTempsDTO ligneTemps) {

        return ligneTempsService.createLigneTemps(ligneTemps);
    }

}
