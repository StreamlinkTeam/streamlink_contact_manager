package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.Action;
import cl.streamlink.contact.domain.Developer;
import cl.streamlink.contact.domain.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Radouen on 13/07/2018.
 */
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    Optional<Evaluation> findOneByReference(String reference);

    Optional<Evaluation> findOneByReferenceAndDeveloperReference(String reference,String developerReference);

    List<Evaluation> findByDeveloperReference(String developerReference);

}
