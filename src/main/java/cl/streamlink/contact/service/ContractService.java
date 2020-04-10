package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.AbstractDevResProfile;
import cl.streamlink.contact.domain.Contract;
import cl.streamlink.contact.domain.User;
import cl.streamlink.contact.domain.WishedContract;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.ContractRepository;
import cl.streamlink.contact.repository.WishedContractRepository;
import cl.streamlink.contact.utils.Constants;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.ContractDTO;
import cl.streamlink.contact.web.dto.WishedContractDTO;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
class ContractService {

    private final Logger logger = LoggerFactory.getLogger(ContractService.class);

    @Inject
    private ContractRepository contractRepository;

    @Inject
    private WishedContractRepository wishedContractRepository;

    @Inject
    private UserService userService;

    @Inject
    private ApiMapper mapper;


    @Secured(Constants.ROLE_ADMIN)
    public ContractDTO createContract(ContractDTO contractDTO, AbstractDevResProfile devResProfile) throws ContactApiException {


        Contract contract = mapper.fromDTOToBean(contractDTO);
        contract.setReference("con" + MiscUtils.generateReference());
        contract.setResponsible(((User) userService.getCurrentUser()));
        contract.setDeveloperReference(devResProfile.getReference());

        return mapper.fromBeanToDTO(contractRepository.save(contract));
    }

    @Secured(Constants.ROLE_ADMIN)
    public ContractDTO updateContract(ContractDTO contractDTO, AbstractDevResProfile devResProfile) throws ContactApiException {


        Contract contract = contractRepository.findOneByDeveloperReference(devResProfile.getReference()).orElse(null);

        if (contract == null) {
            contract = new Contract();
            contract.setReference(MiscUtils.generateReference());
            contract.setDeveloperReference(devResProfile.getReference());
            contract.setResponsible(((User) userService.getCurrentUser()));

        }

        mapper.updateBeanFromDto(contractDTO, contract);
        return mapper.fromBeanToDTO(contractRepository.save(contract));
    }

    public ContractDTO getContract(AbstractDevResProfile devResProfile) throws ContactApiException {

        Contract contract = contractRepository.findOneByDeveloperReference(devResProfile.getReference()).orElse(null);

        if (contract != null) {
            ContractDTO contractDTO = mapper.fromBeanToDTO(contract);
            contractDTO.setDeveloperReference(devResProfile.getReference());

            return contractDTO;
        }

        return null;
    }

    public JSONObject deleteContract(AbstractDevResProfile devResProfile) throws ContactApiException {

        Contract contract = contractRepository.findOneByDeveloperReference(devResProfile.getReference()).orElse(null);

        if (contract != null)
            contractRepository.delete(contract);

        return MiscUtils.createSuccessfullyResult();
    }

    @Secured(Constants.ROLE_ADMIN)
    public WishedContractDTO updateWishedContract(WishedContractDTO wishedContractDTO, AbstractDevResProfile devResProfile) throws ContactApiException {


        WishedContract wishedContract = wishedContractRepository.findOneByDeveloperReference(devResProfile.getReference()).orElse(null);

        if (wishedContract == null) {
            wishedContract = new WishedContract();
            wishedContract.setReference(MiscUtils.generateReference());
            wishedContract.setDeveloperReference(devResProfile.getReference());
            wishedContract.setResponsible(((User) userService.getCurrentUser()));
        }

        mapper.updateBeanFromDto(wishedContractDTO, wishedContract);
        return mapper.fromBeanToDTO(wishedContractRepository.save(wishedContract));
    }

    public WishedContractDTO getWishedContract(AbstractDevResProfile devResProfile) throws ContactApiException {

        WishedContractDTO wishedContract =
                mapper.fromBeanToDTO(wishedContractRepository.findOneByDeveloperReference(devResProfile.getReference()).orElse(new WishedContract()));

        wishedContract.setDeveloperReference(devResProfile.getReference());
        return wishedContract;
    }
}
