package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.SocietyContact;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.SocietyContactService;
import cl.streamlink.contact.web.dto.ContactDTO;
import cl.streamlink.contact.web.dto.SocietyContactDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by chemakh on 13/07/2018.
 */
@RestController
@RequestMapping("/ws/societies/contacts")
public class SocietyContactController {

    @Inject
    private SocietyContactService societyContactService;


    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create SocietyContact Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = SocietyContact.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public SocietyContactDTO createSocietyContact(@RequestBody @Valid SocietyContactDTO societyContact,
                                                  @RequestParam(value = "societyReference") String societyReference) {

        return societyContactService.createSocietyContact(societyContact, societyReference);
    }

    @RequestMapping(value = "",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create SocietyContact Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = SocietyContact.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public SocietyContactDTO updateSocietyContact(@Valid @RequestBody SocietyContactDTO societyContact,
                                                  @RequestParam(value = "societyReference") String societyReference,
                                                  @RequestParam(value = "reference") String reference) throws ContactApiException {

        return societyContactService.updateSocietyContact(societyContact, reference, societyReference);
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get SocietyContact Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = SocietyContact.class),
            @ApiResponse(code = 404, message = "SocietyContact with Ref not Found")
    })
    public SocietyContactDTO getSocietyContact(@RequestParam(value = "societyReference") String societyReference,
                                               @RequestParam(value = "reference") String reference) throws ContactApiException {

        return societyContactService.getSocietyContact(reference, societyReference);
    }

    @RequestMapping(value = "",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete SocietyContact Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = SocietyContact.class),
            @ApiResponse(code = 404, message = "SocietyContact with Ref not Found")
    })
    public JSONObject deleteSocietyContact(@RequestParam("reference") String reference
            , @RequestParam(value = "societyReference") String societyReference) throws ContactApiException {

        return societyContactService.deleteSocietyContact(reference, societyReference);
    }

    @RequestMapping(value = "all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Society Contacts Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = SocietyContact.class),
            @ApiResponse(code = 404, message = "SocietyContact with Ref not Found")
    })
    public List<SocietyContactDTO> getSocietyContact(@RequestParam(value = "societyReference") String societyReference) throws ContactApiException {

        return societyContactService.getSocietyContacts(null, societyReference);
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
    public ContactDTO updateSocietyContactContact(@Valid @RequestBody ContactDTO contact,
                                                  @RequestParam(value = "societyContactReference") String societyContactReference,
                                                  @RequestParam(value = "societyReference") String societyReference) throws ContactApiException {

        return societyContactService.updateSocietyContactContact(contact, societyContactReference, societyReference);
    }

    @RequestMapping(value = "contact",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Society Contact contact Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = ContactDTO.class),
            @ApiResponse(code = 404, message = "Society with Ref not Found")
    })
    public ContactDTO getSocietyContactContact(@RequestParam(value = "societyContactReference") String societyContactReference,
                                               @RequestParam(value = "societyReference") String societyReference) throws ContactApiException {
        return societyContactService.getSocietyContactContact(societyContactReference, societyReference);
    }
}
