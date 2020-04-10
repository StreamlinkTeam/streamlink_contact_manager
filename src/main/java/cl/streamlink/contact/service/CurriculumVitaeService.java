package cl.streamlink.contact.service;


import cl.streamlink.contact.domain.AbstractDevResProfile;
import cl.streamlink.contact.domain.CurriculumVitae;
import cl.streamlink.contact.domain.Developer;
import cl.streamlink.contact.domain.User;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.exception.FieldErrorDTO;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.CurriculumVitaeRepository;
import cl.streamlink.contact.repository.DeveloperRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.MultipartFilesUtils;
import cl.streamlink.contact.web.dto.CurriculumVitaeDTO;
import cl.streamlink.contact.web.dto.DeveloperResponseDTO;
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
class CurriculumVitaeService {

    private final Logger logger = LoggerFactory.getLogger(CurriculumVitaeService.class);

    @Value("${contact.cv.url}")
    private String baseUrl;

    @Value("${contact.cv.path}")
    private String basePath;

    @Inject
    private ApiMapper mapper;

    @Inject
    private DeveloperRepository developerRepository;

    @Inject
    private HireabilityService hireabilityService;

    @Inject
    private UserService userService;

    @Inject
    private CurriculumVitaeRepository curriculumVitaeRepository;


    DeveloperResponseDTO generateDeveloperFromCv(MultipartFile cv) throws ContactApiException {

        try {
            String reference = MiscUtils.generateReference();
            File cvFile = MultipartFilesUtils.saveMultipartFile(basePath, reference + "." + FilenameUtils.getExtension(cv.getOriginalFilename()), cv);
            Developer developer = hireabilityService.parseResume(cvFile);
            if (developer != null) {
                developer.setManager(((User) userService.getCurrentUser()));
                developer.setReference(MiscUtils.generateReference());
                developer = developerRepository.save(developer);

                CurriculumVitae curriculumVitae = new CurriculumVitae();
                curriculumVitae.setReference(reference);
                curriculumVitae.setLabel(cv.getOriginalFilename());
                curriculumVitae.setName(curriculumVitae.getReference() + "." +
                        FilenameUtils.getExtension(cv.getOriginalFilename()));
                curriculumVitae.setDeveloperReference(developer.getReference());

                curriculumVitaeRepository.save(curriculumVitae);

                return mapper.fromBeanToDTOResponse(developer);
            } else throw ContactApiException.validationErrorBuilder(new FieldErrorDTO("Cv", "CV", "format_error"));
        } catch (IOException e) {
            throw ContactApiException.unprocessableEntityExceptionBuilder("parse_error", null);
        }


    }

    CurriculumVitaeDTO addResourceCv(MultipartFile cv, AbstractDevResProfile devResProfile) throws IOException, ContactApiException {

        if (cv != null) {
            if (curriculumVitaeRepository.countByDeveloperReference(devResProfile.getReference()) >= 5)
                throw ContactApiException.unprocessableEntityExceptionBuilder("cv-number", null);

            CurriculumVitae curriculumVitae = new CurriculumVitae();
            curriculumVitae.setReference(MiscUtils.generateReference());
            curriculumVitae.setLabel(cv.getOriginalFilename());
            curriculumVitae.setName(curriculumVitae.getReference() + "." +
                    FilenameUtils.getExtension(cv.getOriginalFilename()));
            curriculumVitae.setDeveloperReference(devResProfile.getReference());
            MultipartFilesUtils.saveMultipartFile(basePath, curriculumVitae.getName(), cv);

            curriculumVitae = curriculumVitaeRepository.save(curriculumVitae);

            return mapper.fromBeanToDTO(curriculumVitae);
        } else
            throw ContactApiException.validationErrorBuilder(new FieldErrorDTO("Cv", "CV", "must_be_not_null"));
    }

    List<CurriculumVitaeDTO> findResourceCvs(AbstractDevResProfile devResProfile) {

        return curriculumVitaeRepository.findByDeveloperReference(devResProfile.getReference())
                .stream().map(mapper::fromBeanToDTO).collect(Collectors.toList());


    }

    JSONObject removeResourceCv(String reference, AbstractDevResProfile devResProfile) throws ContactApiException {


        CurriculumVitae curriculumVitae = curriculumVitaeRepository.findOneByReferenceAndDeveloperReference
                (reference, devResProfile.getReference())
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Cv", reference));

        MultipartFilesUtils.removeFile(basePath, curriculumVitae.getName());
        curriculumVitaeRepository.delete(curriculumVitae);

        return MiscUtils.createSuccessfullyResult();


    }

    private void removeCurriculumVitae(CurriculumVitae curriculumVitae) {

        File file = new File(basePath, curriculumVitae.getName());
        file.delete();
        curriculumVitaeRepository.delete(curriculumVitae);


    }
}
