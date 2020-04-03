package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.Absence;
import cl.streamlink.contact.domain.AbsenceList;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.AbsenceRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.hireability.AbsenceDTO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AbsenceService {

    @Inject
    private AbsenceRepository absenceRepository;

    @Inject
    private TimeLineService timeLineService;

    @Inject
    private ApiMapper mapper;



    public AbsenceDTO createAbsence(AbsenceDTO absenceDTO) {

        Absence absence = mapper.fromDTOToBean(absenceDTO);

        // get absence by user and date
        absence.setReference(MiscUtils.generateReference());
        LocalDate startDate = absence.getDateAbsence().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        timeLineService.validate(startDate, absence.getAbsenceList().getResource());

        return
                mapper.fromBeanToDTO(absenceRepository.save(absence));
    }

    public List<AbsenceDTO> getAbcences(String absenceReference) throws ContactApiException {

        if (absenceReference!= null)
            return Collections.singletonList(mapper.fromBeanToDTO(
                    absenceRepository.findOneByReference(absenceReference).orElseThrow(() -> ContactApiException
                            .resourceNotFoundExceptionBuilder("Absence", absenceReference))));

        else
            return absenceRepository.findAll().stream().map(mapper::fromBeanToDTO)
                    .collect(Collectors.toList());
    }

    public List<AbsenceDTO> getAbsenceByMangerAndResource(String managerReference, String resourceReference) throws ContactApiException {

        return absenceRepository.findByAbsenceListManagerReferenceAndAbsenceListResourceReference(managerReference, resourceReference).stream().map(mapper::fromBeanToDTO).collect(Collectors.toList());


    }

    public List<AbsenceDTO> getAbsenceByManger(String managerReference) throws ContactApiException {

        return absenceRepository.findByAbsenceListManagerReference(managerReference).stream().map(mapper::fromBeanToDTO).collect(Collectors.toList());


    }
    public AbsenceDTO updateAbsence(AbsenceDTO absenceDTO, String absenceReference) throws ContactApiException {

        Absence absence =
                absenceRepository.findOneByReference(absenceReference).orElseThrow(()
                        -> ContactApiException.resourceNotFoundExceptionBuilder("Absence",
                        absenceReference));

        mapper.updateBeanFromDto(absenceDTO, absence);
        return
                mapper.fromBeanToDTO(absenceRepository.save(absence));
    }


    public List<Absence> getAbsenceByAbsenceListReference(String absenceListReference) throws ContactApiException {

        return absenceRepository.findByAbsenceList_Reference(absenceListReference);
    }

    public List<Absence> getAbsenceByAbsenceListId(AbsenceList absenceList) {

        return absenceRepository.findByAbsenceList(absenceList);
    }

    public List<Absence> getAllAbsences() {
        return absenceRepository.findAll();
    }

    public Absence validateAbsence(Absence absence) {
        absenceRepository.validateAbsence(absence.getId());
        return absence;
    }
}
