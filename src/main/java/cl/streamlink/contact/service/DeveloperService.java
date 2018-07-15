package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.Developer;
import cl.streamlink.contact.domain.Language;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.DeveloperRepository;
import cl.streamlink.contact.repository.LanguageRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.*;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeveloperService {
    private final Logger logger = LoggerFactory.getLogger(DeveloperService.class);
    @Inject
    private DeveloperRepository developerRepository;

    @Inject
    private LanguageService languageService;

    @Inject
    private LanguageRepository languageRepository;

    @Inject
    private ApiMapper mapper;

    public DeveloperResponseDTO createDeveloper(DeveloperDTO developerDTO) {

        Developer developer = mapper.fromDTOToBean(developerDTO);

        developer.setReference(MiscUtils.generateReference());
        return mapper.fromBeanToDTOResponse(developerRepository.save(developer));
    }

    public DeveloperResponseDTO updateDeveloper(DeveloperDTO developerDTO, String developerReference) throws ContactApiException {

        Developer developer = developerRepository.findOneByReference(developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

        mapper.updateBeanFromDto(developerDTO, developer);
        return mapper.fromBeanToDTOResponse(developerRepository.save(developer));
    }


    public List<DeveloperResponseDTO> getDeveloper(String developerReference) throws ContactApiException {

        if (developerReference != null)
            return Collections.singletonList(mapper.fromBeanToDTOResponse(developerRepository.findOneByReference(developerReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference))));

        else
            return developerRepository.findAll().stream().map(mapper::fromBeanToDTOResponse).collect(Collectors.toList());
    }

    public JSONObject deleteDeveloper(String developerReference) throws ContactApiException {

        Developer developer = developerRepository.findOneByReference(developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

        developerRepository.delete(developer);

        return MiscUtils.createSuccessfullyResult();
    }

    public DeveloperResponseDTO addLanguage(String developerReference, String languageReference) throws ContactApiException {
        Developer developer = developerRepository.findOneByReference(developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

        Language language = languageRepository.findOneByReference(languageReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Language", languageReference));


        if (developer.getSkillsInformation().getLanguages().add(language)) {
            return mapper.fromBeanToDTOResponse(developerRepository.save(developer));

        } else {
            throw ContactApiException.unprocessableEntityExceptionBuilder("relation.exist", new String[]{developerReference, languageReference});
        }
    }

    public DeveloperResponseDTO removeLanguage(String developerReference, String languageReference) throws ContactApiException {
        Developer developer = developerRepository.findOneByReference(developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

        Language language = languageRepository.findOneByReference(languageReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Language", languageReference));


        if (developer.getSkillsInformation().getLanguages().remove(language)) {
            return mapper.fromBeanToDTOResponse(developerRepository.save(developer));

        } else {
            throw ContactApiException.unprocessableEntityExceptionBuilder("relation.not.exist", new String[]{developerReference, languageReference});
        }
    }

    public ContactDTO updateDeveloperContact(ContactDTO contact, String developerReference) {
        Developer developer = developerRepository.findOneByReference(developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));
        mapper.updateBeanFromDto(contact, developer.getContact());

        developer = developerRepository.save(developer);

        return mapper.fromBeanToDTO(developer.getContact(), developerReference);

    }

    public ContactDTO getDeveloperContact(String developerReference) {

        return getDeveloper(developerReference).get(0).getContact();
    }

    public SkillsInformationDTO updateDeveloperSkills(SkillsInformationDTO skillsInformation, String developerReference) {

        skillsInformation.getLanguages().stream().filter(lan -> MiscUtils.isEmpty(lan.getReference())).
                forEach(languageService::createLanguage);

        Developer developer = developerRepository.findOneByReference(developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));
        mapper.updateBeanFromDto(skillsInformation, developer.getSkillsInformation());

        developer = developerRepository.save(developer);

        return mapper.fromBeanToDTO(developer.getSkillsInformation(), developerReference);

    }

    public SkillsInformationDTO getDeveloperSkills(String developerReference) {

        return getDeveloper(developerReference).get(0).getSkillsInformation();

    }

    public PersonalInformationDTO updateDeveloperPersonalInformation(PersonalInformationDTO personalInformation, String developerReference) {

        Developer developer = developerRepository.findOneByReference(developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));
        mapper.updateBeanFromDto(personalInformation, developer.getPersonalInformation());

        developer = developerRepository.save(developer);

        return mapper.fromBeanToDTO(developer.getPersonalInformation(), developerReference);
    }

    public PersonalInformationDTO getDeveloperPersonalInformation(String developerReference) {
        return getDeveloper(developerReference).get(0).getPersonalInformation();

    }
}
