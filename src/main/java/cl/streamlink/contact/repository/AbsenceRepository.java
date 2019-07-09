package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.Absence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AbsenceRepository extends JpaRepository<Absence, Long> {


    Optional<Absence> findOneByReference(String reference);

}
