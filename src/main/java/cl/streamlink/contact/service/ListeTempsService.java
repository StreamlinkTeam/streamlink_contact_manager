package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.ListeTemps;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.ListeTempsRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.ListeTempsDTO;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;


@Service
public class ListeTempsService {


    private final Logger logger = LoggerFactory.getLogger(ListeTempsService.class);

    @Inject
    private ListeTempsRepository listeTempsRepository;

    @Inject
    private ApiMapper mapper;


    public ListeTempsDTO getListeTemps(String listeTempsReference) throws ContactApiException {

        return mapper.fromBeanToDTO(listeTempsRepository.findOneByReference(
                listeTempsReference).orElseThrow(() ->
                ContactApiException.resourceNotFoundExceptionBuilder("ListeTemps", listeTempsReference)));
    }


    public JSONObject deleteListeTemps(String listeTempsReference) throws ContactApiException {

        // projectService.deleteBySociety(societyReference, null);
        listeTempsRepository.deleteByReference(listeTempsReference);

        return MiscUtils.createSuccessfullyResult();
    }


    public ListeTempsDTO createListeTemps(ListeTempsDTO listeTempsDTO) {

        ListeTemps listeTemps = mapper.fromDTOToBean(listeTempsDTO);

        listeTemps.setReference(MiscUtils.generateReference());
        return
                mapper.fromBeanToDTO(listeTempsRepository.save(listeTemps));
    }

}
