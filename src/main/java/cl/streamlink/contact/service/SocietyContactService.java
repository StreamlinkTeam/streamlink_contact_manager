package cl.streamlink.contact.service;


import cl.streamlink.contact.domain.Society;
import cl.streamlink.contact.domain.SocietyContact;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.SocietyContactRepository;
import cl.streamlink.contact.repository.SocietyRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.*;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SocietyContactService {

    private final Logger logger = LoggerFactory.getLogger(SocietyContactService.class);

    @Inject
    private SocietyContactRepository societyContactRepository;

    @Inject
    private SocietyRepository societyRepository;

    @Inject
    private ApiMapper mapper;

    public SocietyContactDTO createSocietyContact(SocietyContactDTO societyContactDTO, String societyReference) {

        Society society = societyRepository.findOneByReference(societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Society", societyReference));

        SocietyContact societyContact = mapper.fromDTOToBean(societyContactDTO);

        societyContact.setReference(MiscUtils.generateReference());
        societyContact.setSociety(society);
        return mapper.fromBeanToDTO(societyContactRepository.save(societyContact));
    }

    public SocietyContactDTO updateSocietyContact(SocietyContactDTO societyContactDTO, String societyContactReference
            , String societyReference) throws ContactApiException {

        SocietyContact societyContact = societyContactRepository.findOneByReferenceAndSocietyReference(societyContactReference,societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("SocietyContact", societyContactReference));

        mapper.updateBeanFromDto(societyContactDTO, societyContact);
        return mapper.fromBeanToDTO(societyContactRepository.save(societyContact));
    }

    public SocietyContactDTO getSocietyContact(String societyContactReference,String societyReference) throws ContactApiException {

        return mapper.fromBeanToDTO(societyContactRepository.findOneByReferenceAndSocietyReference(societyContactReference,societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("SocietyContact", societyContactReference)));
    }


    public List<SocietyContactDTO> getSocietyContacts(String societyContactReference,String societyReference) throws ContactApiException {

        if (societyContactReference != null)
            return Collections.singletonList(mapper.fromBeanToDTO
                    (societyContactRepository.findOneByReferenceAndSocietyReference(societyContactReference,societyReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("SocietyContact", societyContactReference))));

        else
            return societyContactRepository.findBySocietyReference(societyReference)
                    .stream().map(mapper::fromBeanToDTO).collect(Collectors.toList());
    }

    public JSONObject deleteSocietyContacts(String societyReference) throws ContactApiException {


        societyContactRepository.deleteBySocietyReference(societyReference);

        return MiscUtils.createSuccessfullyResult();
    }

    public JSONObject deleteSocietyContact(String societyContactReference,String societyReference) throws ContactApiException {

        SocietyContact societyContact = societyContactRepository.findOneByReferenceAndSocietyReference(societyContactReference,societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("SocietyContact", societyContactReference));

        societyContactRepository.delete(societyContact);

        return MiscUtils.createSuccessfullyResult();
    }


    public ContactDTO updateSocietyContactContact(ContactDTO contact, String societyContactReference,String societyReference) {

        SocietyContact societyContact = societyContactRepository.findOneByReferenceAndSocietyReference(societyContactReference,societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("SocietyContact", societyContactReference));

        mapper.updateBeanFromDto(contact, societyContact.getContact());

        societyContact = societyContactRepository.save(societyContact);

        return mapper.fromBeanToDTO(societyContact.getContact(), societyContactReference);

    }

    public ContactDTO getSocietyContactContact(String societyContactReference,String societyReference) {

        SocietyContact societyContact = societyContactRepository.findOneByReferenceAndSocietyReference(societyContactReference,societyReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("SocietyContact", societyContactReference));

        ContactDTO contact = mapper.fromBeanToDTO(societyContact.getContact(),societyContactReference);
        contact.setOwnerReference(societyContactReference);
        return contact;
    }
}


