package cl.streamlink.contact.security;

import cl.streamlink.contact.domain.Resource;
import cl.streamlink.contact.domain.User;
import cl.streamlink.contact.repository.ResourceRepository;
import cl.streamlink.contact.repository.UserRepository;
import cl.streamlink.contact.utils.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final User user = userRepository.findOneByEmail(email).orElse(null);

        Resource resource = null;

        if (user == null) {
            resource= resourceRepository.findOneByEmail(email).orElse(null);

            if(resource == null)
                throw new UsernameNotFoundException("User '" + email + "' not found");


            return org.springframework.security.core.userdetails.User//
                    .withUsername(email)//
                    .password(resource.getPassword())//
                    .authorities(Role.ROLE_RESOURCE)//
                    .accountExpired(false)//
                    .accountLocked(false)//
                    .credentialsExpired(false)//
                    .disabled(false)//
                    .build();


        }

        return org.springframework.security.core.userdetails.User//
                .withUsername(email)//
                .password(user.getPassword())//
                .authorities(user.getRoles())//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }

}
