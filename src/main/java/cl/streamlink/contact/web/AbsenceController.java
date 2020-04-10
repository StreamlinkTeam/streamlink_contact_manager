package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.Absence;
import cl.streamlink.contact.domain.AbsenceList;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.AbsenceListService;
import cl.streamlink.contact.service.AbsenceService;
import cl.streamlink.contact.service.ResourceService;
import cl.streamlink.contact.service.UserService;
import cl.streamlink.contact.web.dto.ResourceDTO;
import cl.streamlink.contact.web.dto.hireability.AbsenceDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ws/absences")
public class AbsenceController {

    @Inject
    private AbsenceService absenceService;
    @Inject
    private UserService userService;
    @Autowired
    private AbsenceListService absenceListService;

    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value = "",
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


  /*  @GetMapping

    @ResponseStatus(HttpStatus.OK)

    @ApiOperation(value = "Get lineTemps Details Service")

    @ApiResponses(value = {

            // @ApiResponse(code = 200, message = "Operation Executed Successfully", response = TimeLine.class),

            @ApiResponse(code = 404, message = "lineTemps with Ref not Found")})

    public AbsenceDTO getLineTemps(@RequestParam(value = "absenceReference") String lineTempsReference) throws ContactApiException {
        return timeLineService.getLine(lineTempsReference);
    }*/

    @RequestMapping(value = "all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Absence Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Absence.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public List<AbsenceDTO> getAbsences() throws ContactApiException {
        return absenceService.getAbsences(null);
    }

    @GetMapping(value = "resource")
    public List<Absence> getAllAbsencesByUser(@RequestParam String developerReference) throws ContactApiException {
        ResourceDTO resource = resourceService.getResource(developerReference);
        List<AbsenceList> absenceLists2 = absenceListService.getAll();

        List<AbsenceList> absenceLists = absenceLists2.stream().filter(e -> e.getResource().getReference().equals(resource.getReference())).collect(Collectors.toList());

        List<Absence> absences = new ArrayList<>();

        for (int i = 0; i < absenceLists.size(); i++) {
            absences.addAll(absenceService.getAbsenceByAbsenceListId(absenceLists.get(i)));
        }
        return absences;
    }

    @GetMapping(value = "absence_list/user")
    public List<AbsenceList> getAllAbsencesListByUser(@RequestParam String mail) throws ContactApiException {
        ResourceDTO resource = resourceService.getResourceByEmail(mail);
        List<AbsenceList> absenceLists2 = absenceListService.getAll();
        List<AbsenceList> absenceLists = absenceLists2.stream().filter(e -> e.getResource().getReference().equals(resource.getReference())).collect(Collectors.toList());
        return absenceLists;
    }


    @RequestMapping(value = "managerResource",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Developer Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Absence.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public List<AbsenceDTO> getResourceManager(@RequestParam(value = "managerReference") String managerReference,
                                               @RequestParam(value = "developerReference") String developerReference) throws ContactApiException {
        return absenceService.getAbsenceByMangerAndResource(managerReference, developerReference);
    }

    @RequestMapping(value = "manager",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Developer Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Absence.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public List<AbsenceDTO> getResourceManager() throws ContactApiException {
        String refmanager = userService.getCurrentUser().getReference();
        return absenceService.getAbsenceByManger(refmanager );
    }

    @RequestMapping(value = "",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create absence Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response =  Absence.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public AbsenceDTO updateAbsence(@Valid @RequestBody AbsenceDTO absenceDTO, @RequestParam(value = "absenceReference") String absenceReference) throws ContactApiException {

        return absenceService.updateAbsence(absenceDTO, absenceReference);

    }


    @GetMapping(value = "absence-reference")
    public List<Absence> getAbsenceByAbsenceListReference(@RequestParam(value = "reference") String reference) throws ContactApiException {

        return absenceService.getAbsenceByAbsenceListReference(reference);
    }

    @PutMapping(value = "validate")
    public Absence validateAbsence(@RequestBody Absence absence) {
        return absenceService.validateAbsence(absence);
    }
}
