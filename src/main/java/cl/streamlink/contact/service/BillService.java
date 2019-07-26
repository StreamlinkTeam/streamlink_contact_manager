package cl.streamlink.contact.service;

import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.BillRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.BillDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillService {

    @Inject
    private BillRepository billRepository;

    @Inject
    private ApiMapper mapper;

    public List<BillDTO> getBills() {
        return billRepository.findAll().stream().map(mapper::fromBeanToDTO).collect(Collectors.toList());
    }

    public Page<BillDTO> searchBills(String value, Pageable pageable) {
        if (MiscUtils.isEmpty(value))
            value = "";
        return billRepository.findByTitleContaining(value, pageable)
                .map(bill -> mapper.fromBeanToDTO(bill));
    }

    public BillDTO getDeveloper(String resourceReference) throws ContactApiException {
        return mapper.fromBeanToDTO(billRepository.findByResourceReference(resourceReference).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder("Bill", resourceReference)));
    }

    public BillDTO getBillByReference(String billReference) throws ContactApiException {
        return mapper.fromBeanToDTO(billRepository.findOneByReference(billReference).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder("Bill", billReference)));
    }
}
