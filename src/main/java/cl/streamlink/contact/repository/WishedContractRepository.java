package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.WishedContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by chemakh on 13/07/2018.
 */
public interface WishedContractRepository extends JpaRepository<WishedContract, Long> {

    Optional<WishedContract> findOneByReference(String reference);

    Optional<WishedContract> findOneByDeveloperReference(String developerReference);
}
