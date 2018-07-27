package cl.streamlink.contact.service;


import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.CurriculumVitaeRepository;
import cl.streamlink.contact.repository.DeveloperRepository;
import cl.streamlink.contact.repository.UserRepository;
import cl.streamlink.contact.web.dto.DeveloperDTO;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
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

    public DeveloperDTO addDeveloperCV(MultipartFile avatar, String userRef)  {

        if (avatar != null) {
            User user = userRepository.findOneByReference(userRef).
                    orElseThrow(() -> MazadException.resourceNotFoundExceptionBuilder(User.class, userRef));

            String referenceOldAvatar = user.getAvatar() != null ? user.getAvatar().getReference() : null;
            user.setAvatar(createPhoto("User", avatar, null));
            UserDTO userDTO = mapper.fromUserToDTO(userRepository.save(user));

            if (referenceOldAvatar != null)
                removePhoto(photoRepository.findOneByReference(referenceOldAvatar).orElse(null));

            return userDTO;
        } else
            throw MazadException.validationErrorBuilder(new FieldErrorDTO("Photo", "Photo", "must_be_not_null"));
    }

    public ArticleDTO createArticleAvatar(MultipartFile avatar, String articleRef) throws IOException, MazadException {
        if (avatar != null) {

            Article article = articleRepository.findOneByReference(articleRef).
                    orElseThrow(() -> MazadException.resourceNotFoundExceptionBuilder(Article.class, articleRef));

            String referenceOldAvatar = article.getAvatar() != null ? article.getAvatar().getReference() : null;
            article.setAvatar(createPhoto("Article", avatar, null));
            ArticleDTO articleDTO = mapper.fromArticleToDTO(articleRepository.save(article));

            if (referenceOldAvatar != null)
                removePhoto(photoRepository.findOneByReference(referenceOldAvatar).orElse(null));

            return articleDTO;
        } else
            throw MazadException.validationErrorBuilder(new FieldErrorDTO("Photo", "Photo", "must_be_not_null"));
    }

    public Category createCategoryAvatar(MultipartFile avatar, String categoryRef) throws IOException, MazadException {
        if (avatar != null) {

            Category category = categoryRepository.findOneByReference(categoryRef).
                    orElseThrow(() -> MazadException.resourceNotFoundExceptionBuilder(Category.class, categoryRef));

            String referenceOldAvatar = category.getAvatar() != null ? category.getAvatar().getReference() : null;
            category.setAvatar(createPhoto("Category", avatar, null));
            category = categoryRepository.save(category);

            if (referenceOldAvatar != null)
                removePhoto(photoRepository.findOneByReference(referenceOldAvatar).orElse(null));

            return category;
        } else
            throw MazadException.validationErrorBuilder(new FieldErrorDTO("Photo", "Photo", "must_be_not_null"));
    }

    public Photo createPhoto(String type, MultipartFile avatar, String articleRef) throws MazadException, IOException {

        if (avatar != null) {

            Photo photo = new Photo();
            photo.setReference(TokenUtil.generateReference());
            photo.setLabel(type + "-" + avatar.getName());
            photo.setName(photo.getReference() + "." + FilenameUtils.getExtension(avatar.getOriginalFilename()));
            photo.setArticle(articleRef != null ? articleRepository.findOneByReference(articleRef).
                    orElseThrow(() -> MazadException.resourceNotFoundExceptionBuilder(Article.class, articleRef)) : null);
            avatar.transferTo(new File(mazadProperties.getAvatar().getPath() + photo.getName()));

            photo = photoRepository.save(photo);

            if (articleRef != null) {
                Article article = articleRepository.findOneByReference(articleRef).
                        orElseThrow(() -> MazadException.resourceNotFoundExceptionBuilder(Article.class, articleRef));

                if (article.getAvatar() == null) {
                    article.setAvatar(photo);
                    articleRepository.save(article);
                }
            }
            return photo;

        } else
            throw MazadException.validationErrorBuilder(new FieldErrorDTO("Photo", "Photo", "must_be_not_null"));
    }

    public Photo getPhotoByArticle(String reference, String articleRef) throws MazadException {
        return photoRepository.findOneByReferenceAndArticleReference(reference, articleRef).orElseThrow(() ->
                MazadException.resourceNotFoundExceptionBuilder(Photo.class, reference));
    }

    public List<PhotoDTO> getArticlePhotos(String articleRef) {
        return photoRepository.findByArticleReference(articleRef).stream().map(mapper::fromPhotoToDTO)
                .collect(Collectors.toList());
    }

    public Photo getPhoto(String reference) throws MazadException {
        return photoRepository.findOneByReference(reference).orElseThrow(() ->
                MazadException.resourceNotFoundExceptionBuilder(Photo.class, reference));
    }

    public void removePhoto(Photo photo) throws MazadException, IOException {

        if (photo != null) {
            File file = new File(mazadProperties.getAvatar().getPath() + photo.getName());
            file.delete();
            photoRepository.delete(photo);
        }

    }
}
