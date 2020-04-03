package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.AbsenceManage;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.AbsenceManageService;
import cl.streamlink.contact.web.dto.AbsenceManageDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;

@RestController
@RequestMapping("/ws/absenceManage")
public class AbsenceManageController {

    @Inject
    private AbsenceManageService absenceManageService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create AbsenceManage Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = AbsenceManage.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")})
    public AbsenceManageDTO createAbsenceManage(@RequestBody @Valid AbsenceManageDTO absenceManage) {

        return absenceManageService.createAbsenceManage(absenceManage);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get AbsenceManage Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = AbsenceManageDTO.class),
            @ApiResponse(code = 404, message = "Need with Ref not Found")})
    public AbsenceManageDTO getAbsenceManage(@RequestParam(value = "absenceManageReference") String absenceManageReference) throws ContactApiException {
        return absenceManageService.getAbsenceManage(absenceManageReference);
    }

    @GetMapping(value = "/byResource")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get AbsenceManage Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = AbsenceManageDTO.class),
            @ApiResponse(code = 404, message = "Need with Ref not Found")})
    public AbsenceManageDTO getAbsenceManageByResource(@RequestParam(value = "resourceReference") String resourceReference) throws ContactApiException {
        return absenceManageService.getAbsenceManageByResourceReference(resourceReference);
    }



    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "update Need legal information Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = AbsenceManageDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")})
    public AbsenceManageDTO updateAbsenceManage(@Valid @RequestBody AbsenceManageDTO absenceManage,
                                                    @RequestParam(value = "resourceReference") String resourceReference) throws ContactApiException {

        return absenceManageService.updateAbsenceManage2(absenceManage, resourceReference);
    }


    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Absence Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = AbsenceManage.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public AbsenceManageDTO createAbsence(@RequestBody @Valid AbsenceManageDTO absence,
                                                    @RequestParam(value = "resourceReference") String resourceReference) {

        return absenceManageService.createAbsence(absence, resourceReference);
    }
}
