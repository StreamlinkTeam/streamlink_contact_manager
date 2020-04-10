package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.Developer;
import cl.streamlink.contact.domain.Resource;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ResourceApiMapper;
import cl.streamlink.contact.repository.ResourceRepository;
import cl.streamlink.contact.service.ResourceService;
import cl.streamlink.contact.utils.Constants;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.Experience;
import cl.streamlink.contact.utils.enums.Formation;
import cl.streamlink.contact.utils.enums.ResourceStage;
import cl.streamlink.contact.utils.enums.ResourceType;
import cl.streamlink.contact.web.abstractdevres.AbstractDevResController;
import cl.streamlink.contact.web.dto.AvatarDTO;
import cl.streamlink.contact.web.dto.ResourceDTO;
import cl.streamlink.contact.web.dto.ResourceResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.minidev.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/ws/resources")
public class ResourceController extends AbstractDevResController
        <Resource, ResourceRepository, ResourceDTO, ResourceResponseDTO, ResourceApiMapper, ResourceService> {


//    @RequestMapping(value = "/current",
//            method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    @Secured(Constants.ROLE_RESOURCE)
//    public ResourceDTO getCurrentResource() throws ContactApiException {
//        return devResService.getResource(userService.getCurrentUser().getReference());
//    }


    @RequestMapping(value = "from_developer",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Resource Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Resource.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public ResourceDTO createResourceFromDeveloper(@RequestParam(value = "developerReference") String developerReference) throws ContactApiException {
        return devResService.createResourceFromDeveloper(developerReference);
    }

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Resource Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Resource.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public ResourceDTO createResource(@RequestBody @Valid ResourceDTO resource) throws ContactApiException {

        return devResService.createResource(resource);
    }

    @RequestMapping(value = "",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Resource Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Resource.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public ResourceDTO updateResource(@Valid @RequestBody ResourceDTO resource, @RequestParam(value = "developerReference") String developerReference) throws ContactApiException {

        return devResService.updateResource(resource, developerReference);
    }


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Page<ResourceResponseDTO> getResources(Pageable pageable, @RequestParam boolean fromAngular,
                                                  @RequestParam(required = false) String value,
                                                  @RequestParam(required = false) Formation formation,
                                                  @RequestParam(required = false) ResourceStage stage,
                                                  @RequestParam(required = false) Experience experience,
                                                  @RequestParam(required = false) ResourceType type,
                                                  @RequestParam(required = false) Sort.Direction dir) {

        if (fromAngular) {

            pageable = MiscUtils.convertFromAngularPage(pageable, dir, false);
        }
        return devResService.searchResources(value, stage, experience, formation, type, pageable);

    }

    @RequestMapping(value = "currentLeave",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Developer Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Developer.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    @Secured(Constants.ROLE_RESOURCE)
    public int currentLeave() throws ContactApiException {
        return (new Date().getMonth() + 1) - (devResService.getResourceByEmail(userService.getCurrentUser().getUsername()).getAvailability().getMonthValue());
    }


    @PutMapping(value = "avatar",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Add user avatar")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = AvatarDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict"),
            @ApiResponse(code = 404, message = "Object with Ref not Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden")

    })
    public AvatarDTO addResourceAvatar(@RequestParam String developerReference, @RequestPart(value = "avatar") MultipartFile avatar) throws IOException, ContactApiException {

        return devResService.addResourceAvatar(avatar, developerReference);
    }

    @DeleteMapping(value = "avatar",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "remove user avatar")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = JSONObject.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict"),
            @ApiResponse(code = 404, message = "Object with Ref not Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden")

    })
    public JSONObject removeResourceAvatar(@RequestParam String reference, @RequestParam String developerReference) throws ContactApiException {

        return devResService.removeResourceAvatar(reference, developerReference);
    }

    @GetMapping(value = "avatar",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Resources avatars")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = AvatarDTO.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public AvatarDTO getResourceAvatar(@RequestParam String developerReference) throws ContactApiException {
        return devResService.getResourceAvatar(developerReference);
    }

}
