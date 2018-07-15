package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.Developer;
import cl.streamlink.contact.domain.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {

    Optional<Developer> findOneByReference(String reference);

}
