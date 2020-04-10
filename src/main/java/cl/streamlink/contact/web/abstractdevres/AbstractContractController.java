package cl.streamlink.contact.web.abstractdevres;

import cl.streamlink.contact.domain.AbstractDevResProfile;
import cl.streamlink.contact.domain.Contract;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.DevResMapper;
import cl.streamlink.contact.repository.AbstractDevResRepository;
import cl.streamlink.contact.service.AbstractDevResService;
import cl.streamlink.contact.web.dto.AbstractDevResProfileDTO;
import cl.streamlink.contact.web.dto.AbstractDevResResponseDTO;
import cl.streamlink.contact.web.dto.ContractDTO;
import cl.streamlink.contact.web.dto.WishedContractDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;


public abstract class AbstractContractController
        <E extends AbstractDevResProfile, T extends AbstractDevResRepository<E>, D extends AbstractDevResProfileDTO<E>,
                R extends AbstractDevResResponseDTO<E>, M extends DevResMapper<E, D, R>, S extends AbstractDevResService<E, T, D, R, M>> {

    @Inject
    private S devResService;

    @RequestMapping(value = "contract",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Developer Contract Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = ContractDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public ContractDTO createContract(@RequestBody @Valid ContractDTO contract,
                                      @RequestParam(value = "developerReference") String developerReference) throws ContactApiException {

        return devResService.createContract(contract, developerReference);
    }

    @RequestMapping(value = "contract",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update Contract Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Contract.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public ContractDTO updateContract(@Valid @RequestBody ContractDTO contract, @RequestParam(value = "developerReference") String developerReference) throws ContactApiException {

        return devResService.updateContract(contract, developerReference);
    }

    @RequestMapping(value = "contract",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Developer Contract Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = ContractDTO.class),
            @ApiResponse(code = 404, message = "Contract with Ref not Found")
    })
    public ContractDTO getContract(@RequestParam(value = "developerReference") String developerReference) throws ContactApiException {
        return devResService.getContract(developerReference);
    }

    @RequestMapping(value = "contract",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete Contract Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = Contract.class),
            @ApiResponse(code = 404, message = "Contract with Ref not Found")
    })
    public JSONObject deleteContract(@RequestParam("developerReference") String developerReference) throws ContactApiException {

        return devResService.deleteContract(developerReference);
    }

    @RequestMapping(value = "contract/wished",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update Developer Wished Contract Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = WishedContractDTO.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public WishedContractDTO updateWishedContract(@Valid @RequestBody WishedContractDTO wishedContract,
                                                  @RequestParam(value = "developerReference") String developerReference) throws ContactApiException {

        return devResService.updateWishedContract(wishedContract, developerReference);
    }

    @RequestMapping(value = "contract/wished",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Developer Wished Contract Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = WishedContractDTO.class),
            @ApiResponse(code = 404, message = "Contract with Ref not Found")
    })
    public WishedContractDTO getWishedContract(@RequestParam(value = "developerReference") String developerReference) throws ContactApiException {
        return devResService.getWishedContract(developerReference);
    }

}
