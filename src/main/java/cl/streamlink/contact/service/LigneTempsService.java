package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.TempsLine;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.LigneTempsRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.LigneTempsDTO;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;


@Service
public class LigneTempsService {


    private final Logger logger = LoggerFactory.getLogger(LigneTempsService.class);

    @Inject
    private LigneTempsRepository ligneTempsRepository;


    @Inject
    private ApiMapper mapper;


    public LigneTempsDTO getLigne(String ligneTempsReference) throws ContactApiException {

        return mapper.fromBeanToDTO(ligneTempsRepository.findOneByReference(
                ligneTempsReference).orElseThrow(() ->
                ContactApiException.resourceNotFoundExceptionBuilder("TempsLine"
                        , ligneTempsReference)));
    }


    public JSONObject deleteLigneTemps(String ligneTempsReference) throws ContactApiException {


        ligneTempsRepository.deleteByReference(ligneTempsReference);

        return MiscUtils.createSuccessfullyResult();
    }


    public LigneTempsDTO createLigneTemps(LigneTempsDTO ligneTempsDTO) {

        TempsLine tempsLine = mapper.fromDTOToBean(ligneTempsDTO);

        tempsLine.setReference(MiscUtils.generateReference());
        return
                mapper.fromBeanToDTO(ligneTempsRepository.save(tempsLine));
    }






    /*
     * public LigneTempsDTO updateLigneTemps(LigneTempsDTO ligneTempsDTO, String
     * ligneTempsReference) throws ContactApiException {
     *
     * TempsLine ligneTemps =
     * ligneTempsRepository.findOneByReference(ligneTempsReference).orElseThrow(()
     * -> ContactApiException.resourceNotFoundExceptionBuilder("TempsLine",
     * ligneTempsReference));
     *
     * mapper.updateBeanFromDto(ligneTempsDTO, ligneTemps); return
     * mapper.fromBeanToDTO(ligneTempsRepository.save(ligneTemps)); }
     */

}
