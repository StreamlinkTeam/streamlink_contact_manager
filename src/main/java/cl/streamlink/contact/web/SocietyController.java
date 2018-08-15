package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.*;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.CurriculumVitaeService;
import cl.streamlink.contact.service.SocietyService;
import cl.streamlink.contact.web.dto.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.minidev.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ws/societies")
public class SocietyController {

    @Inject
    private SocietyService societyService;

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Society Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Society.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public SocietyResponseDTO createSociety(@RequestBody @Valid SocietyDTO society) {

        return societyService.createSociety(society);
    }

    @RequestMapping(value = "",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Society Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Society.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public SocietyResponseDTO updateSociety(@Valid @RequestBody SocietyDTO society, @RequestParam(value = "societyReference") String societyReference) throws ContactApiException {

        return societyService.updateSociety(society, societyReference);
    }

    @RequestMapping(value = "all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Society Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Society.class),
            @ApiResponse(code = 404, message = "Society with Ref not Found")
    })
    public List<SocietyResponseDTO> getSocieties() {
        return societyService.getSocieties(null);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Page<SocietyResponseDTO> getSocieties(Pageable pageable, @RequestParam boolean fromAngular,
                                                    @RequestParam(required = false) String value,
                                                    @RequestParam(required = false) SocietyActivityArea activityArea,
                                                    @RequestParam(required = false) SocietyStage stage,
                                                    @RequestParam(required = false) Sort.Direction dir) {

        if (fromAngular) {

            pageable = pageable.previousOrFirst();

            if (dir != null) {

                PageRequest req = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                        new Sort(dir, pageable.getSort().stream().map(order -> {

                            if (order.getProperty().equalsIgnoreCase("email1"))
                                return "contact.email1";

                            return order.getProperty();
                        }).collect(Collectors.toList())));

                return societyService.searchSocieties(value, stage, activityArea, req);
            }
        }
        return societyService.searchSocieties(value, stage, activityArea, pageable);

    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Society Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = SocietyDTO.class),
            @ApiResponse(code = 404, message = "Society with Ref not Found")
    })
    public SocietyDTO getSociety(@RequestParam(value = "societyReference") String societyReference) throws ContactApiException {
        return societyService.getSociety(societyReference);
    }

    @RequestMapping(value = "",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete Society Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Society.class),
            @ApiResponse(code = 404, message = "Society with Ref not Found")
    })
    public JSONObject deleteSociety(@RequestParam("societyReference") String societyReference) throws ContactApiException {

        return societyService.deleteSociety(societyReference);
    }
    //contact

    @RequestMapping(value = "contact",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "update Society Contact Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = ContactDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public ContactDTO updateSocietyContact(@Valid @RequestBody ContactDTO contact, @RequestParam(value = "societyReference") String societyReference) throws ContactApiException {

        return societyService.updateSocietyContact(contact, societyReference);
    }

    @RequestMapping(value = "contact",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Society Contact Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = ContactDTO.class),
            @ApiResponse(code = 404, message = "Society with Ref not Found")
    })
    public ContactDTO getSocietyContact(@RequestParam(value = "societyReference") String societyReference) throws ContactApiException {
        return societyService.getSocietyContact(societyReference);
    }
    //legal_information

    @RequestMapping(value = "legal_information",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "update Society legal information Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = SkillsInformationDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public SocietyLegalInformationDTO updateSocietySkills(@Valid @RequestBody SocietyLegalInformationDTO legalInformation
            , @RequestParam(value = "societyReference") String societyReference) throws ContactApiException {

        return societyService.updateSocietyLegalInformation(legalInformation, societyReference);
    }

    @RequestMapping(value = "legal_information",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Society legal information Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = SocietyLegalInformationDTO.class),
            @ApiResponse(code = 404, message = "Society with Ref not Found")
    })
    public SocietyLegalInformationDTO getSocietySkills(@RequestParam(value = "societyReference") String societyReference) throws ContactApiException {
        return societyService.getSocietyLegalInformation(societyReference);
    }
}
