package cl.streamlink.contact.web;
import cl.streamlink.contact.domain.AbsenceList;
import cl.streamlink.contact.service.AbsenceListService;
import cl.streamlink.contact.web.dto.AbsenceListDTO;
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
public class AbsenceListController {


    @Inject
    private AbsenceListService absenceListService;

    @RequestMapping(value = "absenceList",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create AbsenceList Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = AbsenceList.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public AbsenceListDTO createAbsence(@RequestBody @Valid AbsenceListDTO absenceListDTO) {

        return absenceListService.createAbsence(absenceListDTO);
    }
}
