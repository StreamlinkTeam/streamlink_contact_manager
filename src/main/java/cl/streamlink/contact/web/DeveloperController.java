package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.Developer;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.DeveloperService;
import cl.streamlink.contact.web.dto.*;
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

@RestController
@RequestMapping("/ws/developers")
public class DeveloperController {

    @Inject
    private DeveloperService developerService;

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Developer Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Developer.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public DeveloperResponseDTO createDeveloper(@RequestBody @Valid DeveloperDTO developer) {

        return developerService.createDeveloper(developer);
    }

    @RequestMapping(value = "",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Developer Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Developer.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public DeveloperResponseDTO updateDeveloper(@Valid @RequestBody DeveloperDTO developer, @RequestParam(value = "developerReference") String developerReference) throws ContactApiException {

        return developerService.updateDeveloper(developer, developerReference);
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Developer Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Developer.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public List<DeveloperResponseDTO> getDeveloper(@RequestParam(value = "developerReference", required = false) String developerReference) throws ContactApiException {
        return developerService.getDeveloper(developerReference);
    }

    @RequestMapping(value = "",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete Developer Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Developer.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public JSONObject deleteDeveloper(@RequestParam("developerReference") String developerReference) throws ContactApiException {

        return developerService.deleteDeveloper(developerReference);
    }
    //contact

    @RequestMapping(value = "contact",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "update Developer Contact Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = ContactDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public ContactDTO updateDeveloperContact(@Valid @RequestBody ContactDTO contact, @RequestParam(value = "developerReference") String developerReference) throws ContactApiException {

        return developerService.updateDeveloperContact(contact, developerReference);
    }

    @RequestMapping(value = "contact",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Developer Contact Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = ContactDTO.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public ContactDTO getDeveloperContact(@RequestParam(value = "developerReference") String developerReference) throws ContactApiException {
        return developerService.getDeveloperContact(developerReference);
    }
    //skills

    @RequestMapping(value = "skills",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "update Developer Skills Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = SkillsInformationDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public SkillsInformationDTO updateDeveloperSkills(@Valid @RequestBody SkillsInformationDTO skillsInformation
            , @RequestParam(value = "developerReference") String developerReference) throws ContactApiException {

        return developerService.updateDeveloperSkills(skillsInformation, developerReference);
    }

    @RequestMapping(value = "skills",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Developer Contact Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = SkillsInformationDTO.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public SkillsInformationDTO getDeveloperSkills(@RequestParam(value = "developerReference") String developerReference) throws ContactApiException {
        return developerService.getDeveloperSkills(developerReference);
    }

    @RequestMapping(value = "skills/languages",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "add Language to developer skills")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = DeveloperResponseDTO.class),
            @ApiResponse(code = 404, message = "Developer or language with Ref not Found")
    })
    public DeveloperResponseDTO addLanguage(@RequestParam(value = "developerReference") String developerReference,
                                            @RequestParam(value = "languageReference") String languageReference) throws ContactApiException {
        return developerService.addLanguage(developerReference, languageReference);
    }

    @RequestMapping(value = "skills/languages",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "remove Language to developer skills")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = DeveloperResponseDTO.class),
            @ApiResponse(code = 404, message = "Developer or language with Ref not Found")
    })
    public DeveloperResponseDTO removeLanguage(@RequestParam(value = "developerReference") String developerReference,
                                               @RequestParam(value = "languageReference") String languageReference) throws ContactApiException {
        return developerService.removeLanguage(developerReference, languageReference);
    }

    //personal
    @RequestMapping(value = "personal_info",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "update Developer personal Information Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = PersonalInformationDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public PersonalInformationDTO updateDeveloperPersonalInformation(@Valid @RequestBody PersonalInformationDTO personalInformation, @RequestParam(value = "developerReference") String developerReference) throws ContactApiException {

        return developerService.updateDeveloperPersonalInformation(personalInformation, developerReference);
    }

    @RequestMapping(value = "personal_info",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Developer personal Information Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = PersonalInformationDTO.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public PersonalInformationDTO getDeveloperPersonalInformation(@RequestParam(value = "developerReference") String developerReference) throws ContactApiException {
        return developerService.getDeveloperPersonalInformation(developerReference);
    }
}
