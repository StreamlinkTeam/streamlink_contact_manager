package cl.streamlink.contact.service;


import cl.streamlink.contact.domain.CurriculumVitae;
import cl.streamlink.contact.domain.Developer;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.exception.FieldErrorDTO;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.CurriculumVitaeRepository;
import cl.streamlink.contact.repository.DeveloperRepository;
import cl.streamlink.contact.repository.UserRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.CurriculumVitaeDTO;
import net.minidev.json.JSONObject;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CurriculumVitaeService {

    private final Logger logger = LoggerFactory.getLogger(CurriculumVitaeService.class);

    @Inject
    private ApiMapper mapper;

    @Value("${contact.avatar.url}")
    String baseUrl;

    @Value("${contact.avatar.path}")
    String basePath;

    @Inject
    private UserRepository userRepository;

    @Inject
    private DeveloperRepository developerRepository;

    @Inject
    private CurriculumVitaeRepository curriculumVitaeRepository;

    public CurriculumVitaeDTO addDeveloperCV(MultipartFile cv, String developerReference) throws IOException {

        if (cv != null) {
            Developer developer = developerRepository.findOneByReference(developerReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

            if (curriculumVitaeRepository.countByDeveloperReference(developerReference) >= 5)
                throw ContactApiException.unprocessableEntityExceptionBuilder("", null);

            return mapper.fromBeanToDTO(saveCurriculumVitae(cv, developer));
        } else
            throw ContactApiException.validationErrorBuilder(new FieldErrorDTO("Cv", "CV", "must_be_not_null"));
    }

    public List<CurriculumVitaeDTO> findDeveloperCVs(String developerReference) {

        developerRepository.findOneByReference(developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));


        return curriculumVitaeRepository.findByDeveloperReference(developerReference)
                .stream().map(mapper::fromBeanToDTO).collect(Collectors.toList());


    }

    public JSONObject removeDeveloperCV(String reference, String developerReference) {

        Developer developer = developerRepository.findOneByReference(developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Developer", developerReference));

        CurriculumVitae curriculumVitae = curriculumVitaeRepository.findOneByReferenceAndDeveloperReference
                (reference, developerReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Cv", reference));

        removeCurriculumVitae(curriculumVitae);

        return MiscUtils.createSuccessfullyResult();


    }


    private CurriculumVitae saveCurriculumVitae(MultipartFile cv, Developer developer) throws IOException {

        CurriculumVitae curriculumVitae = new CurriculumVitae();
        curriculumVitae.setReference(MiscUtils.generateReference());
        curriculumVitae.setLabel(cv.getOriginalFilename());
        curriculumVitae.setName(curriculumVitae.getReference() + "." +
                FilenameUtils.getExtension(cv.getOriginalFilename()));
        curriculumVitae.setDeveloper(developer);
        cv.transferTo(new File(basePath, curriculumVitae.getName()));

        curriculumVitae = curriculumVitaeRepository.save(curriculumVitae);

        return curriculumVitae;

    }

    private void removeCurriculumVitae(CurriculumVitae curriculumVitae) {

        File file = new File(basePath , curriculumVitae.getName());
        file.delete();
        curriculumVitaeRepository.delete(curriculumVitae);


    }
}
