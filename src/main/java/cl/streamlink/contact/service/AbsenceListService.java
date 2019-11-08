package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.*;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.AbsenceListRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.AbsenceListDTO;
import cl.streamlink.contact.web.dto.DeveloperActionDTO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.Console;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AbsenceListService {
    @Inject
    private UserService userService;
    @Inject
    private DeveloperService developerService;
    @Inject
    private ApiMapper mapper;

    @Inject
    private AbsenceListRepository absenceListRepository;

    public AbsenceListDTO createAbsence(AbsenceListDTO absenceListDTO) {
       String email = userService.getCurrentUser().getEmail();
       // System.out.println('ssss'+email);
        String ref = developerService.getDeveloperEmail(email).getReference();
        String referenceManager = developerService.getDeveloperEmail(email).getManagerReference();
        absenceListDTO.setResourceReference(ref);
        absenceListDTO.setManagerReference(referenceManager);
        AbsenceList absenceList = mapper.fromDTOToBean(absenceListDTO);
       // absenceList.setResource(ref);

        absenceList.setReference(MiscUtils.generateReference());
        absenceList.setAbsenceListDate(new Date());
        absenceList.setState("NV");

       // absenceList.setResource(userService.getCurrentUser().getReference());
        /*absenceListDTO.setReference(MiscUtils.generateReference());
        absenceListDTO.setAbsenceListDate(new Date());
        absenceListDTO.setResourceReference(ref);*/

        return
                mapper.fromBeanToDTO(absenceListRepository.save(absenceList));
    }
    public List<AbsenceList> getByDeveloper(String developer) {
        return absenceListRepository.findByResource(developer);
    }

    public AbsenceList validateAbsenceList(AbsenceList absenceList) {
        absenceListRepository.validateAbsenceList(absenceList.getId());
        return absenceList;
    }

    public List<AbsenceList> getByResource(Resource resource) {
        return absenceListRepository.findAllByResource(resource);
    }


    public List<AbsenceList> getAll() {
        return absenceListRepository.findAll();
    }

    public List<AbsenceList> getByManager(User manager) {
        return absenceListRepository.findByManager(manager);
    }
}
