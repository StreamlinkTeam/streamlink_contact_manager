package cl.streamlink.contact.security;

import cl.streamlink.contact.exception.ContactApiError;
import cl.streamlink.contact.exception.ContactApiException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;


public final class SecurityUtils {

    private SecurityUtils() {

    }

    public static boolean checkIfThereIsUserLogged() {
        return SecurityContextHolder.getContext().getAuthentication() != null;
    }


    public static String getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userName = null;
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                userName = springSecurityUser.getUsername();
            } else if (authentication.getPrincipal() instanceof String) {
                userName = (String) authentication.getPrincipal();
            }
        }
        return userName;
    }

    public static Optional<ContactUserDetails> getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        if (authentication != null) {
            if (authentication.getPrincipal() instanceof ContactUserDetails)

                return Optional.of((ContactUserDetails) authentication.getPrincipal());


        }
        return Optional.empty();
    }

    public static ContactUserDetails getCurrentUserOrThrowUnauthorizedException() throws ContactApiException {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        if (authentication != null) {
            if (authentication.getPrincipal() instanceof ContactUserDetails)

                return (ContactUserDetails) authentication.getPrincipal();


        }
        throw new ContactApiException("Access denied", ContactApiError.UNAUTHORIZED, null, null);
    }


}
