package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.AttachedFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttachedFileRepository  extends JpaRepository<AttachedFile, Long> {

    Optional<AttachedFile> findOneByReference(String reference);

     long countByResourceReference(String resourceReference);

}
