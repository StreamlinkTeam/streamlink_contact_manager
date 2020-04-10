package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.AbstractDevResProfile;
import cl.streamlink.contact.domain.Developer;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.DeveloperApiMapper;
import cl.streamlink.contact.repository.DeveloperRepository;
import cl.streamlink.contact.service.DeveloperService;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.Experience;
import cl.streamlink.contact.utils.enums.Formation;
import cl.streamlink.contact.utils.enums.Stage;
import cl.streamlink.contact.web.abstractdevres.AbstractDevResController;
import cl.streamlink.contact.web.dto.AbstractDevResProfileDTO;
import cl.streamlink.contact.web.dto.DeveloperDTO;
import cl.streamlink.contact.web.dto.DeveloperResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/ws/developers")
@CrossOrigin("*")
public class DeveloperController extends AbstractDevResController
        <Developer, DeveloperRepository, DeveloperDTO, DeveloperResponseDTO, DeveloperApiMapper, DeveloperService> {


    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Developer Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Developer.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public AbstractDevResProfileDTO<? extends AbstractDevResProfile> createResource(@RequestBody @Valid DeveloperDTO developer) throws ContactApiException {

        return devResService.createResource(developer);
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
    public AbstractDevResProfileDTO<? extends AbstractDevResProfile> updateResource(@Valid @RequestBody DeveloperDTO developer, @RequestParam(value = "developerReference") String developerReference) throws ContactApiException {

        return devResService.updateResource(developer, developerReference);
    }


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Page<DeveloperResponseDTO> searchDevelopers(Pageable pageable, @RequestParam boolean fromAngular,
                                                       @RequestParam(required = false) String value,
                                                       @RequestParam(required = false) Formation formation,
                                                       @RequestParam(required = false) Stage stage,
                                                       @RequestParam(required = false) Experience experience,
                                                       @RequestParam(required = false) Sort.Direction dir) {

        if (fromAngular) {

            pageable = MiscUtils.convertFromAngularPage(pageable, dir, false);
        }
        return devResService.searchDevelopers(value, stage, experience, formation, pageable);

    }

    @PostMapping(value = "cv",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Add developer from CV")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = DeveloperResponseDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict"),
            @ApiResponse(code = 404, message = "Object with Ref not Found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden")

    })
    public DeveloperResponseDTO addDeveloperFromCV(@RequestPart(value = "cv") MultipartFile cv) throws ContactApiException {

        return devResService.generateDeveloperFromCv(cv);
    }


}
