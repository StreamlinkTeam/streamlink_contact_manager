package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.AbsenceList;
import cl.streamlink.contact.domain.Resource;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.AbsenceListRepository;
import cl.streamlink.contact.utils.Constants;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.AbsenceListDTO;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Service
public class AbsenceListService {
    @Inject
    private UserService userService;
    @Inject
    private ResourceService resourceService;
    @Inject
    private ApiMapper mapper;

    @Inject
    private AbsenceListRepository absenceListRepository;

    @Secured(Constants.ROLE_RESOURCE)
    public AbsenceListDTO createAbsence(AbsenceListDTO absenceListDTO) throws ContactApiException {

        Resource currentLoggedResource = (Resource) userService.getCurrentUser();


        absenceListDTO.setResourceReference(currentLoggedResource.getReference());
        absenceListDTO.setManagerReference(currentLoggedResource.getManager().getReference());
        AbsenceList absenceList = mapper.fromDTOToBean(absenceListDTO);


        absenceList.setReference(MiscUtils.generateReference());
        absenceList.setAbsenceListDate(new Date());
        absenceList.setState("NV");

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

    public List<AbsenceList> getByManager(String managerReference) {
        return absenceListRepository.findByManagerReference(managerReference);
    }
}
