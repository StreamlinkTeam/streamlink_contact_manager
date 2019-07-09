package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.Absence;
import cl.streamlink.contact.service.AbsenceService;
import cl.streamlink.contact.web.dto.DeveloperActionDTO;
import cl.streamlink.contact.web.dto.hireability.AbsenceDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
@RestController
@RequestMapping("/ws")
public class AbsenceController {

    @Inject
    private AbsenceService absenceService;

    @RequestMapping(value = "absence",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Absence Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Absence.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public AbsenceDTO createAbsence(@RequestBody @Valid AbsenceDTO absence) {

        return  absenceService.createAbsence(absence);
    }
}
