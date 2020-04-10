package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    Optional<Avatar> findOneByReference(String reference);

    Optional<Avatar> findFirstByUserReferenceOrderByCreatedDateDesc(String userReference);

    Optional<Avatar> findOneByReferenceAndUserReference(String reference, String userReference);

    List<Avatar> findByUserReference(String userReference);

    long countByUserReference(String userReference);

    Optional<Avatar> findFirstByResourceReferenceOrderByCreatedDateDesc(String userReference);

    Optional<Avatar> findOneByReferenceAndResourceReference(String reference, String userReference);

    List<Avatar> findByResourceReference(String userReference);
}
