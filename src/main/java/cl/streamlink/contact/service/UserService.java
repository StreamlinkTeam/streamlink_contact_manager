package cl.streamlink.contact.service;

import cl.streamlink.contact.config.ApplicationConfig;
import cl.streamlink.contact.domain.User;
import cl.streamlink.contact.exception.ContactApiError;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.UserRepository;
import cl.streamlink.contact.security.JwtTokenProvider;
import cl.streamlink.contact.security.SecurityUtils;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Inject
    private ApiMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    public String signin(String username, String password) {
        try {
            ApplicationConfig.getService(AuthenticationManager.class)
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, userRepository.findOneByEmail(username).get().getRoles());
        } catch (AuthenticationException e) {
            throw new ContactApiException("Invalid email/password supplied", ContactApiError.UNAUTHORIZED, null);
        }
    }

    public UserDTO signup(UserDTO user) {

        if (!userRepository.existsByEmail(user.getEmail())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setReference(MiscUtils.generateReference());
            return mapper.fromBeanToDTO(userRepository.save(mapper.fromDTOToBean(user)));
        } else {
            throw new ContactApiException("Email is already in use", ContactApiError.UNAUTHORIZED, null);
        }
    }

    public void delete(String email) {
        userRepository.deleteByEmail(email);
    }

    public UserDTO search(String email) {
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

    public User getCurrentUser() {
        String currentUserName = SecurityUtils.getCurrentUserLogin();

        if (!SecurityUtils.checkIfThereIsUserLogged())
            throw new ContactApiException("Access denied", ContactApiError.UNAUTHORIZED, null);

        return userRepository.findOneByEmail(currentUserName).
                orElseThrow(() -> ContactApiException.
                        resourceNotFoundExceptionBuilder("User", currentUserName));
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(mapper::fromBeanToDTO).collect(Collectors.toList());
    }
}
