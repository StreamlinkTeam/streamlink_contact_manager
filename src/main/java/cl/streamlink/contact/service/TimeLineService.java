package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.TimeLine;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.TimeLineRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.TimeLineDTO;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;


@Service
public class TimeLineService {


    private final Logger logger = LoggerFactory.getLogger(TimeLineService.class);

    @Inject
    private TimeLineRepository timeLineRepository;


    @Inject
    private ApiMapper mapper;


    public TimeLineDTO getLigne(String ligneTempsReference) throws ContactApiException {

        return mapper.fromBeanToDTO(timeLineRepository.findOneByReference(
                ligneTempsReference).orElseThrow(() ->
                ContactApiException.resourceNotFoundExceptionBuilder("TimeLine"
                        , ligneTempsReference)));
    }






    public JSONObject deleteLigneTemps(String ligneTempsReference) throws ContactApiException {


        timeLineRepository.deleteByReference(ligneTempsReference);

        return MiscUtils.createSuccessfullyResult();
    }


    public TimeLineDTO createLigneTemps(TimeLineDTO ligneTempsDTO) {

        TimeLine timeLine = mapper.fromDTOToBean(ligneTempsDTO);

        timeLine.setReference(MiscUtils.generateReference());
        return
                mapper.fromBeanToDTO(timeLineRepository.save(timeLine));
    }


    public TimeLineDTO updateLigneTemps(TimeLineDTO ligneTempsDTO, String ligneTempsReference) throws ContactApiException {

        TimeLine ligneTemps =
                timeLineRepository.findOneByReference(ligneTempsReference).orElseThrow(()
                        -> ContactApiException.resourceNotFoundExceptionBuilder("TimeLine",
                        ligneTempsReference));

        mapper.updateBeanFromDto(ligneTempsDTO, ligneTemps);
        return
                mapper.fromBeanToDTO(timeLineRepository.save(ligneTemps));
    }

}
