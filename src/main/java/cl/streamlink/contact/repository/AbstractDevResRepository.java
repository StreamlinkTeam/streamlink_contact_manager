package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.AbstractDevResProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface AbstractDevResRepository<T extends AbstractDevResProfile> extends JpaRepository<T, Long> {

    Optional<T> findOneByReference(String reference);

    Optional<T> findByContact_Email1(String email);

    List<T> findByManagerReference(String managerReference);

    boolean existsByReference(String reference);

    List<T> findByFirstnameContaining(String value);

}
