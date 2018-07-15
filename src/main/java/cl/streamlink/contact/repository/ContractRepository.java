package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.Action;
import cl.streamlink.contact.domain.Contract;
import cl.streamlink.contact.domain.Developer;
import cl.streamlink.contact.domain.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Radouen on 13/07/2018.
 */
public interface ContractRepository extends JpaRepository<Contract, Long> {

    Optional<Contract> findOneByReference(String reference);

    Optional<Contract> findOneByDeveloperReference(String developerReference);

}
