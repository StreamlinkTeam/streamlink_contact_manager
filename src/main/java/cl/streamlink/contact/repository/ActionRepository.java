package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.Action;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by chemakh on 13/07/2018.
 */
public interface ActionRepository extends JpaRepository<Action, Long> {

    Optional<Action> findOneByReference(String reference);

    Optional<Action> findOneByReferenceAndDeveloperReference(String reference,String developerReference);

    List<Action> findByDeveloperReference(String developerReference);

}
