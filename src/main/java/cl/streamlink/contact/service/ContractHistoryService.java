package cl.streamlink.contact.service;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cl.streamlink.contact.domain.Contract;
import cl.streamlink.contact.domain.ContractHistory;
import cl.streamlink.contact.domain.Developer;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.repository.ContractHistoryRepository;
import cl.streamlink.contact.repository.ContractRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.ContractDTO;

@Service
public class ContractHistoryService {
    private final Logger logger = LoggerFactory.getLogger(ContractHistoryService.class);

    @Inject
    private ContractHistoryRepository contractHistoryRepository;

    public ContractHistory createContractHistory(ContractHistory contractHistory) {

        ContractHistory contractHistoryC = contractHistoryRepository.save(contractHistory);

        return contractHistoryC;
    }

    public List<ContractHistory> getContractHistory(String developerReference) {

        List<ContractHistory> contractHistoyList = contractHistoryRepository
                .findByDeveloperReference(developerReference);

        return contractHistoyList;

    }
}
