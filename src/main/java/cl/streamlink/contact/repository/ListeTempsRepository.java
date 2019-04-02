package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.ListeTemps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface ListeTempsRepository extends JpaRepository<ListeTemps, Long> {


    Optional<ListeTemps> findOneByReference(String reference);


    @Transactional
    long deleteByReference(String listeTempsReference);

}
