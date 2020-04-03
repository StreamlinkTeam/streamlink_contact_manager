package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.AbsenceManage;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.AbsenceManageRepository;
import cl.streamlink.contact.repository.ResourceRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.AbsenceManageDTO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class AbsenceManageService {

    @Inject
    private AbsenceManageRepository absenceManageRepository;

    @Inject
    private ResourceRepository resourceRepository;

    @Inject
    private ApiMapper mapper;

    public AbsenceManageDTO createAbsenceManage(AbsenceManageDTO AbsenceManageDTO) {

        AbsenceManage absenceManage = mapper.fromDTOToBean(AbsenceManageDTO);

        absenceManage.setReference("absMng" + MiscUtils.generateReference());
        return mapper.fromBeanToDTO(absenceManageRepository.save(absenceManage));
    }

    public AbsenceManageDTO getAbsenceManage(String absenceManageReference) throws ContactApiException {

        return mapper.fromBeanToDTO(absenceManageRepository.findOneByReference(absenceManageReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("AbsenceManage", absenceManageReference)));
    }

    public AbsenceManageDTO getAbsenceManageByResourceReference(String resourceReference) throws ContactApiException {

        if (!absenceManageRepository.findOneByResourceReference(resourceReference).isPresent()) {

            resourceRepository.findOneByReference(resourceReference);

            AbsenceManageDTO absenceManageDTO = new AbsenceManageDTO();
            absenceManageDTO.setReference("abs" + MiscUtils.generateReference());
            absenceManageDTO.setResourceReference(resourceReference);
            absenceManageDTO.setAcquired(0);
            absenceManageDTO.setActual_Balance(0);
            absenceManageDTO.setConsumed(0);
            absenceManageDTO.setProvisional_Balance(0);
            absenceManageDTO.setRequested(0);
            AbsenceManage absenceManage = mapper.fromDTOToBean(absenceManageDTO);
             mapper.fromBeanToDTO(absenceManageRepository.save(absenceManage));
        }

            return mapper.fromBeanToDTO(absenceManageRepository.findOneByResourceReference(resourceReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("AbsenceManage", resourceReference)));
    }


    public AbsenceManageDTO updateAbsenceManage(AbsenceManageDTO absenceManageDTO, String absenceManageReference) throws ContactApiException {

        AbsenceManage absenceManage = absenceManageRepository.findOneByReference(absenceManageReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("AbsenceManage", absenceManageReference));

        mapper.updateBeanFromDto(absenceManageDTO, absenceManage);
        return mapper.fromBeanToDTO(absenceManageRepository.save(absenceManage));
    }

    public AbsenceManageDTO updateAbsenceManage2(AbsenceManageDTO absenceManageDTO, String resourceReference) throws ContactApiException {

        AbsenceManage absenceManage = absenceManageRepository.findOneByResourceReference(resourceReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("AbsenceManage", resourceReference));

        mapper.updateBeanFromDto(absenceManageDTO, absenceManage);
        return mapper.fromBeanToDTO(absenceManageRepository.save(absenceManage));
    }

    public AbsenceManageDTO createAbsence(AbsenceManageDTO absenceManageDTO, String resourceReference) {

        resourceRepository.findOneByReference(resourceReference).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder("Resource", resourceReference));

        absenceManageDTO.setReference("abs" + MiscUtils.generateReference());
        absenceManageDTO.setResourceReference(resourceReference);
        AbsenceManage absenceManage = mapper.fromDTOToBean(absenceManageDTO);
        return mapper.fromBeanToDTO(absenceManageRepository.save(absenceManage));
    }
}
