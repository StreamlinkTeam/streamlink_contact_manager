package cl.streamlink.contact.service;

import cl.streamlink.contact.config.ApplicationConfig;
import cl.streamlink.contact.domain.Resource;
import cl.streamlink.contact.domain.User;
import cl.streamlink.contact.exception.ContactApiError;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.exception.FieldErrorDTO;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.ResourceRepository;
import cl.streamlink.contact.repository.UserRepository;
import cl.streamlink.contact.security.ContactUserDetails;
import cl.streamlink.contact.security.JwtTokenProvider;
import cl.streamlink.contact.security.SecurityUtils;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.Role;
import cl.streamlink.contact.web.dto.AvatarDTO;
import cl.streamlink.contact.web.dto.ChangePasswordDTO;
import cl.streamlink.contact.web.dto.UserDTO;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Inject
    private ApiMapper mapper;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AvatarService avatarService;


    public JSONObject signIn(String username, String password) throws ContactApiException {
        try {
            Authentication authentication = ApplicationConfig.getService(AuthenticationManager.class)
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));

            String token = jwtTokenProvider.createToken(username, authentication.getAuthorities());

            JSONObject result = new JSONObject();
            result.put("access_token", token);
            result.put("roles", authentication.getAuthorities());

            if (authentication.getPrincipal() instanceof ContactUserDetails)
                result.put("user_reference", ((ContactUserDetails) authentication.getPrincipal()).getReference());

            return result;

        } catch (AuthenticationException e) {
            throw new ContactApiException("Invalid email/password supplied", ContactApiError.UNAUTHORIZED, null, e);
        }
    }

    public UserDTO signUp(UserDTO userDTO) throws ContactApiException {

        checkIfEmailIsUsed(userDTO.getEmail(), null);
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.setReference("usr" + MiscUtils.generateReference());
        if (MiscUtils.isEmpty(userDTO.getRoles()))
            userDTO.setRoles(Collections.singletonList(Role.ROLE_CLIENT));
        return mapper.fromBeanToDTO(userRepository.save(mapper.fromDTOToBean(userDTO)));

    }

    public UserDTO updateUser(UserDTO userDTO, String userReference) throws ContactApiException {

        checkIfEmailIsUsed(userDTO.getEmail(), userReference);

        User user = userRepository.findOneByReference(userReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("User", userReference));

        mapper.updateBeanFromDto(userDTO, user);

        return mapper.fromBeanToDTO(userRepository.save(user));
    }

    public UserDTO getUser(String userReference) throws ContactApiException {

        return mapper.fromBeanToDTO(userRepository.findOneByReference(userReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("User", userReference)));

    }


    public JSONObject deleteUser(String userReference) throws ContactApiException {

        User user = userRepository.findOneByReference(userReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("User", userReference));

        userRepository.delete(user);

        return MiscUtils.createSuccessfullyResult();
    }

    public UserDTO search(String email) throws ContactApiException {
        User user = userRepository.findOneByEmail(email).orElse(null);
        if (user == null) {
            throw ContactApiException.resourceNotFoundExceptionBuilder("User", email);
        }
        return mapper.fromBeanToDTO(user);
    }

    public UserDTO whoami(HttpServletRequest req) {
        return mapper.fromBeanToDTO(userRepository.findOneByEmail
                (jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req))).orElse(null));
    }

    public JSONObject changeCurrentUserPassword(ChangePasswordDTO passwordDTO) throws ContactApiException {

        ContactUserDetails user = getCurrentUser();
        if (passwordEncoder.matches(passwordDTO.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
            if (user.isResource())
                resourceRepository.save(((Resource) user));
            else
                userRepository.save(((User) user));
        } else {
            throw ContactApiException.validationErrorBuilder(new FieldErrorDTO("User", "Password", "must_match"));
        }

        return MiscUtils.createSuccessfullyResult();
    }

    public JSONObject changeUserPassword(String userReference, String oldPassword, String newPassword) throws ContactApiException {

        User user = userRepository.findOneByReference(userReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("User", userReference));

        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        } else {
            throw ContactApiException.validationErrorBuilder(new FieldErrorDTO("User", "Password", "must_match"));
        }
        return MiscUtils.createSuccessfullyResult();
    }

    public ContactUserDetails getCurrentUser() throws ContactApiException {

        return SecurityUtils.getCurrentUserOrThrowUnauthorizedException();
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(mapper::fromBeanToDTO).collect(Collectors.toList());
    }

    private void checkIfEmailIsUsed(String email, String reference) throws ContactApiException {

        if (MiscUtils.isNotEmpty(email)) {
            Optional<User> find = userRepository.findOneByEmail(email).
                    filter(user -> MiscUtils.isEmpty(reference) || !user.getReference().equals(reference));
            if (find.isPresent()) {
                throw ContactApiException.unauthorizedExceptionBuilder("email_exist", null);
            }
        }

    }

    public Page<UserDTO> searchUsers(String value, Pageable pageable) {

        if (MiscUtils.isEmpty(value))
            value = "";

        return userRepository.findByFirstnameContainingOrLastnameContainingOrEmailContaining
                (value, value, value, pageable).map(user -> mapper.fromBeanToDTO(user));
    }

    public long usersCount() {
        return userRepository.count();
    }

    public Optional<User> getById(long id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public AvatarDTO addUserAvatar(MultipartFile file, String userReference) throws ContactApiException, IOException {

        User user = userRepository.findOneByReference(userReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("User", userReference));

        return avatarService.updateUserAvatar(file, user);

    }

    public JSONObject removeUserAvatar(String reference, String userReference) throws ContactApiException {

        User user = userRepository.findOneByReference(userReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("User", userReference));
        return avatarService.removeUserAvatar(reference, user);

    }

    public AvatarDTO getUserAvatar(String userReference) throws ContactApiException {
        User user = userRepository.findOneByReference(userReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("User", userReference));

        return avatarService.getUserAvatar(user);

    }

    public AvatarDTO addCurrentUserAvatar(MultipartFile multipartFile) throws ContactApiException, IOException {

        ContactUserDetails user = getCurrentUser();
        return avatarService.updateUserAvatar(multipartFile, user);

    }

    public JSONObject removeCurrentUserAvatar(String reference) throws ContactApiException {
        ContactUserDetails user = getCurrentUser();
        return avatarService.removeUserAvatar(reference, user);
    }

    public AvatarDTO getCurrentUserAvatar() throws ContactApiException {
        ContactUserDetails user = getCurrentUser();
        return avatarService.getUserAvatar(user);
    }
}
