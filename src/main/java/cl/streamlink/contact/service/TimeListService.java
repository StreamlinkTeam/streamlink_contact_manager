package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.TimeList;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.TimeListRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.TimeListDTO;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;


@Service
public class TimeListService {


    private final Logger logger = LoggerFactory.getLogger(TimeListService.class);

    @Inject
    private TimeListRepository timeListRepository;

    @Inject
    private ApiMapper mapper;


    public TimeListDTO getListeTemps(String listeTempsReference) throws ContactApiException {

        return mapper.fromBeanToDTO(timeListRepository.findOneByReference(
                listeTempsReference).orElseThrow(() ->
                ContactApiException.resourceNotFoundExceptionBuilder("TimeList", listeTempsReference)));
    }


    public JSONObject deleteListeTemps(String listeTempsReference) throws ContactApiException {

        // projectService.deleteBySociety(societyReference, null);
        timeListRepository.deleteByReference(listeTempsReference);

        return MiscUtils.createSuccessfullyResult();
    }


    public TimeListDTO createListeTemps(TimeListDTO listeTempsDTO) {

        TimeList timeList = mapper.fromDTOToBean(listeTempsDTO);

        timeList.setReference(MiscUtils.generateReference());
        return
                mapper.fromBeanToDTO(timeListRepository.save(timeList));
    }

}
