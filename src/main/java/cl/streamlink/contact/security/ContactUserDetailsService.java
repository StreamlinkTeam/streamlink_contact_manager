package cl.streamlink.contact.security;

import cl.streamlink.contact.repository.ResourceRepository;
import cl.streamlink.contact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ContactUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        ContactUserDetails user = userRepository.findOneByEmail(email).orElse(null);

        if (user == null) {
            user = resourceRepository.findOneByEmail(email).orElse(null);

            if (user == null)
                throw new UsernameNotFoundException("User '" + email + "' not found");
        }

        return user;
    }

}
