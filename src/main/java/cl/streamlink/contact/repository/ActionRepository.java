package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.Action;
import cl.streamlink.contact.domain.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Radouen on 13/07/2018.
 */
public interface ActionRepository extends JpaRepository<Action, Long> {

    Optional<Action> findOneByReference(String reference);

    Optional<Action> findOneByReferenceAndDeveloperReference(String reference,String developerReference);

    List<Action> findByDeveloperReference(String developerReference);

}
