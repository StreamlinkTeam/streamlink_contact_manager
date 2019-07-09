package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.AbsenceList;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.AbsenceListRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.AbsenceListDTO;
import cl.streamlink.contact.web.dto.DeveloperActionDTO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
@Service
public class AbsenceListService {

    @Inject
    private ApiMapper mapper;

    @Inject
    private AbsenceListRepository absenceListRepository;



    /*public AbsenceListDTO createAbsenceList(AbsenceListDTO absenceListDTO, String resourceReference) {

        absenceListRepository.findOneByReference(developerReference).orElseThrow(
                () -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", resourceReference));

        absenceListDTO.setReference(MiscUtils.generateReference());
        absenceListDTO.setResourceReference(resourceReference);
      //  developerActionDTO.setResponsibleReference(userService.getCurrentUser().getReference());

        AbsenceList absenceList = mapper.fromDTOToBean(absenceListDTO);

        return mapper.fromBeanToDTO()
                //fromBeanToAbsenceListDTO(absenceListRepository.save(absenceList));
    }
*/

    public AbsenceListDTO createAbsence(AbsenceListDTO absenceListDTO) {

        AbsenceList absenceList = mapper.fromDTOToBean(absenceListDTO);

        absenceList.setReference(MiscUtils.generateReference());
        return
                mapper.fromBeanToDTO(absenceListRepository.save(absenceList));
    }
}
