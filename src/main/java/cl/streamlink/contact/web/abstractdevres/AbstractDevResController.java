package cl.streamlink.contact.web.abstractdevres;

import cl.streamlink.contact.domain.AbstractDevResProfile;
import cl.streamlink.contact.domain.Action;
import cl.streamlink.contact.domain.Resource;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.DevResMapper;
import cl.streamlink.contact.repository.AbstractDevResRepository;
import cl.streamlink.contact.service.AbstractDevResService;
import cl.streamlink.contact.service.UserService;
import cl.streamlink.contact.web.dto.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

public abstract class AbstractDevResController
        <E extends AbstractDevResProfile, T extends AbstractDevResRepository<E>, D extends AbstractDevResProfileDTO<E>,
                R extends AbstractDevResResponseDTO<E>, M extends DevResMapper<E, D, R>, S extends AbstractDevResService<E, T, D, R, M>> {

    @Inject
    protected S devResService;

    @Inject
    protected UserService userService;


    public abstract AbstractDevResProfileDTO<? extends AbstractDevResProfile> createResource(@RequestBody @Valid D resource) throws ContactApiException;

    public abstract AbstractDevResProfileDTO<? extends AbstractDevResProfile> updateResource(@Valid @RequestBody D resource, String developerReference) throws ContactApiException;

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Resource Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = ResourceDTO.class),
            @ApiResponse(code = 404, message = "Resource with Ref not Found")
    })
    public D getResource(@RequestParam(value = "developerReference") String developerReference) throws ContactApiException {
        return devResService.getResource(developerReference);
    }

    @RequestMapping(value = "",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete Resource Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Resource.class),
            @ApiResponse(code = 404, message = "Resource with Ref not Found")
    })
    public JSONObject deleteResource(@RequestParam("developerReference") String developerReference) throws ContactApiException {

        return devResService.deleteResource(developerReference);
    }

    @RequestMapping(value = "all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Resource Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Resource.class),
            @ApiResponse(code = 404, message = "Resource with Ref not Found")
    })
    public List<R> getResources() throws ContactApiException {
        return devResService.getResources(null);
    }


    @RequestMapping(value = "/auto-complete", method = RequestMethod.GET)
    public List<R> getResources(@RequestParam(required = false) String term) {
        return devResService.searchResources(term);

    }

    //contact
    @RequestMapping(value = "contact",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "update Resource Contact Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = ContactDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public ContactDTO updateResourceContact(@Valid @RequestBody ContactDTO contact, @RequestParam(value = "developerReference") String developerReference) throws ContactApiException {

        return devResService.updateResourceContact(contact, developerReference);
    }

    @RequestMapping(value = "contact",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Resource Contact Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = ContactDTO.class),
            @ApiResponse(code = 404, message = "Resource with Ref not Found")
    })
    public ContactDTO getResourceContact(@RequestParam(value = "developerReference") String developerReference) throws ContactApiException {
        return devResService.getResourceContact(developerReference);
    }
    //skills

    @RequestMapping(value = "skills",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "update Resource Skills Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = SkillsInformationDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public SkillsInformationDTO updateResourceSkills(@Valid @RequestBody SkillsInformationDTO skillsInformation
            , @RequestParam(value = "developerReference") String developerReference) throws ContactApiException {

        return devResService.updateResourceSkills(skillsInformation, developerReference);
    }

    @RequestMapping(value = "skills",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Resource Contact Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = SkillsInformationDTO.class),
            @ApiResponse(code = 404, message = "Resource with Ref not Found")
    })
    public SkillsInformationDTO getResourceSkills(@RequestParam(value = "developerReference") String developerReference) throws ContactApiException {
        return devResService.getResourceSkills(developerReference);
    }


    @RequestMapping(value = "personal_info",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "update Resource personal Information Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = PersonalInformationDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public PersonalInformationDTO updateResourcePersonalInformation(@Valid @RequestBody PersonalInformationDTO personalInformation, @RequestParam(value = "developerReference") String developerReference) throws ContactApiException {

        return devResService.updateResourcePersonalInformation(personalInformation, developerReference);
    }

    @RequestMapping(value = "personal_info",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Resource personal Information Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = PersonalInformationDTO.class),
            @ApiResponse(code = 404, message = "Resource with Ref not Found")
    })
    public PersonalInformationDTO getResourcePersonalInformation(@RequestParam(value = "developerReference") String developerReference) throws ContactApiException {
        return devResService.getResourcePersonalInformation(developerReference);
    }


    @PutMapping(value = "cv",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Add resource CV")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = CurriculumVitaeDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict"),
            @ApiResponse(code = 404, message = "Object with Ref not Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden")

    })
    public CurriculumVitaeDTO addResourceCV(@RequestParam String developerReference, @RequestPart(value = "cv") MultipartFile cv) throws IOException, ContactApiException {

        return devResService.addResourceCv(cv, developerReference);
    }

    @DeleteMapping(value = "cv",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "remove resource CV")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = CurriculumVitaeDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict"),
            @ApiResponse(code = 404, message = "Object with Ref not Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden")

    })
    public JSONObject removeResourceCV(@RequestParam String reference, @RequestParam String developerReference) throws ContactApiException {

        return devResService.removeResourceCv(reference, developerReference);
    }

    @GetMapping(value = "cv",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Resource CVs")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = CurriculumVitaeDTO.class),
            @ApiResponse(code = 404, message = "Resource with Ref not Found")
    })
    public List<CurriculumVitaeDTO> findResourceCVs(@RequestParam(value = "developerReference") String developerReference) throws ContactApiException {
        return devResService.findResourceCvs(developerReference);
    }

    /**
     * Actions
     */

    @RequestMapping(value = "actions",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Action Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Action.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public ResourceActionDTO createResourceAction(@RequestBody @Valid ResourceActionDTO action,
                                                  @RequestParam(value = "developerReference") String developerReference) throws ContactApiException {

        return devResService.createAction(action, developerReference);
    }

    @RequestMapping(value = "actions",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Action Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Action.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public ResourceActionDTO updateResourceAction(@Valid @RequestBody ResourceActionDTO action, @RequestParam(value = "developerReference") String developerReference,
                                                  @RequestParam(value = "reference") String reference) throws ContactApiException {

        return devResService.updateAction(action, reference, developerReference);
    }

    @RequestMapping(value = "actions",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Action Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Action.class),
            @ApiResponse(code = 404, message = "Action with Ref not Found")
    })
    public List<ResourceActionDTO> getResourceAction(@RequestParam(value = "developerReference") String developerReference,
                                                     @RequestParam(value = "reference", required = false) String reference) throws ContactApiException {

        return devResService.getAction(reference, developerReference);
    }

    @RequestMapping(value = "actions",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete Action Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Action.class),
            @ApiResponse(code = 404, message = "Action with Ref not Found")
    })
    public JSONObject deleteResourceAction(@RequestParam("reference") String reference
            , @RequestParam(value = "developerReference") String developerReference) throws ContactApiException {

        return devResService.deleteAction(reference, developerReference);
    }
}
