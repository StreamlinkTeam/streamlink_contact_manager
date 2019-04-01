package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.TempsLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface LigneTempsRepository extends JpaRepository<TempsLine, Long> {

    Optional<TempsLine> findOneByReference(String reference);


    @Transactional
    long deleteByReference(String ligneTempsReference);

}
