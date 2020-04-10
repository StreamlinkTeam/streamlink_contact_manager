package cl.streamlink.contact.utils.enums;

import cl.streamlink.contact.utils.Constants;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN(Constants.ROLE_ADMIN),
    ROLE_CLIENT(Constants.ROLE_CLIENT),
    ROLE_RESOURCE(Constants.ROLE_RESOURCE);

    Role(String role) {
    }

    public String getAuthority() {
        return name();
    }

}
