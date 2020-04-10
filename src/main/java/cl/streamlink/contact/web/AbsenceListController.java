package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.AbsenceList;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.AbsenceListService;
import cl.streamlink.contact.service.ResourceService;
import cl.streamlink.contact.service.UserService;
import cl.streamlink.contact.utils.Constants;
import cl.streamlink.contact.web.dto.AbsenceListDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/ws")
public class AbsenceListController {

    @Autowired
    UserService userService;

    @Inject
    private AbsenceListService absenceListService;

    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value = "absenceList",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create AbsenceList Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = AbsenceList.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public AbsenceListDTO createAbsence(@RequestBody AbsenceListDTO absenceListDTO) throws ContactApiException {
        return absenceListService.createAbsence(absenceListDTO);
    }

    @RequestMapping(value = "absence_list/save",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create AbsenceList Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = AbsenceList.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public AbsenceListDTO createAbsenceList() throws ContactApiException {
        AbsenceListDTO absenceListDTO = new AbsenceListDTO();
        absenceListDTO.setState("NV");
        absenceListDTO.setSubject("Timesheet");
        absenceListDTO.setComment("Absence");

        return absenceListService.createAbsence(absenceListDTO);
    }

    @RequestMapping(value = "absenceList/developer", method = RequestMethod.GET)
    public List<AbsenceList> getByDeveloper(@RequestParam String developer) {
        return absenceListService.getByDeveloper(developer);
    }


    /*
    @RequestMapping(value = "",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create absenceList temps Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response =  AbsenceList.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public AbsenceListDTO updateProject(@Valid @RequestBody AbsenceListDTO absenceListDTO, @RequestParam(value = "absenceListReference") String absenceListReference) throws ContactApiException {

        return absenceListService.updateAbsence(absenceListDTO,absenceListReference);

    }*/

    /*
    @RequestMapping(value = "allAbsence",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get AbsenceList Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = AbsenceList.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public List<AbsenceListDTO> getAbsences() {
        return absenceListService.getListAbsences(null);
    }

     */

    @GetMapping(value = "managerReference")
    @Secured(Constants.ROLE_ADMIN)
    public List<AbsenceList> getResourceManager() throws ContactApiException {
        String managerReference = userService.getCurrentUser().getReference();
        return absenceListService.getByManager(managerReference);
    }

    @PutMapping(value = "absence_list/validate")
    public AbsenceList validateAbsenceList(@RequestBody AbsenceList absenceList) {
        return absenceListService.validateAbsenceList(absenceList);
    }

}
