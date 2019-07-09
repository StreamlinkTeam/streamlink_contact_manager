package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.Absence;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.AbsenceRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.DeveloperActionDTO;
import cl.streamlink.contact.web.dto.hireability.AbsenceDTO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class AbsenceService {

    @Inject
    private AbsenceRepository absenceRepository;

    @Inject
    private ApiMapper mapper;



    public AbsenceDTO createAbsence(AbsenceDTO absenceDTO) {

        Absence absence = mapper.fromDTOToBean(absenceDTO);

        absence.setReference(MiscUtils.generateReference());
        return
                mapper.fromBeanToDTO(absenceRepository.save(absence));
    }
}
