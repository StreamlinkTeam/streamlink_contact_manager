package cl.streamlink.contact.security;

import cl.streamlink.contact.domain.Resource;
import org.springframework.security.core.userdetails.UserDetails;

public interface ContactUserDetails extends UserDetails {

    String getReference();

    void setPassword(String password);

    default boolean isResource() {
        return this instanceof Resource;
    }


}
