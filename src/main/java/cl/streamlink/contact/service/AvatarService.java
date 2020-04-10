package cl.streamlink.contact.service;


import cl.streamlink.contact.domain.Avatar;
import cl.streamlink.contact.domain.Resource;
import cl.streamlink.contact.domain.User;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.exception.FieldErrorDTO;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.AvatarRepository;
import cl.streamlink.contact.security.ContactUserDetails;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.MultipartFilesUtils;
import cl.streamlink.contact.web.dto.AvatarDTO;
import net.minidev.json.JSONObject;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.IOException;

@Service
public class AvatarService {

    private final Logger logger = LoggerFactory.getLogger(AvatarService.class);

    @Value("${contact.avatar.path}")
    private String baseAvatarPath;

    @Inject
    private ApiMapper mapper;

    @Inject
    private AvatarRepository avatarRepository;


    AvatarDTO updateUserAvatar(MultipartFile multipartFile, ContactUserDetails userDetails) throws IOException, ContactApiException {

        if (multipartFile != null) {


            Avatar avatar = new Avatar();
            avatar.setReference(MiscUtils.generateReference());
            avatar.setLabel(multipartFile.getOriginalFilename());
            avatar.setName(avatar.getReference() + "." +
                    FilenameUtils.getExtension(multipartFile.getOriginalFilename()));
            if (userDetails.isResource())
                avatar.setResource(((Resource) userDetails));
            else
                avatar.setUser(((User) userDetails));

            MultipartFilesUtils.saveMultipartFile(baseAvatarPath, avatar.getName(), multipartFile);

            avatar = avatarRepository.save(avatar);

            return mapper.fromBeanToDTO(avatar);
        } else
            throw ContactApiException.validationErrorBuilder(new FieldErrorDTO("Avatar", "Avatar", "must_be_not_null"));
    }

    AvatarDTO getUserAvatar(ContactUserDetails userDetails) throws ContactApiException {


        if (userDetails.isResource())
            return avatarRepository.findFirstByResourceReferenceOrderByCreatedDateDesc
                    (userDetails.getReference())
                    .map(avatar -> mapper.fromBeanToDTO(avatar))
                    .orElse(null);
        else
            return avatarRepository.findFirstByUserReferenceOrderByCreatedDateDesc
                    (userDetails.getReference())
                    .map(avatar -> mapper.fromBeanToDTO(avatar))
                    .orElse(null);

    }


    JSONObject removeUserAvatar(String reference, ContactUserDetails userDetails) throws ContactApiException {


        Avatar avatar;

        if (userDetails.isResource())
            avatar = avatarRepository.findOneByReferenceAndResourceReference
                    (reference, userDetails.getReference())
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("UserAvatar", reference));
        else
            avatar = avatarRepository.findOneByReferenceAndUserReference
                    (reference, userDetails.getReference())
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("UserAvatar", reference));

        MultipartFilesUtils.removeFile(baseAvatarPath, avatar.getName());
        avatarRepository.delete(avatar);


        return MiscUtils.createSuccessfullyResult();

    }
}
