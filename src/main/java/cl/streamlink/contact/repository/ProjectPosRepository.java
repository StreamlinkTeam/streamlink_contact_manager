package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.Project;
import cl.streamlink.contact.domain.ProjectPos;
import cl.streamlink.contact.domain.Resource;
import cl.streamlink.contact.utils.enums.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProjectPosRepository extends JpaRepository<ProjectPos, Long> {

    boolean existsByReference(String reference);

    Optional<ProjectPos> findOneByReference(String reference);

    List<ProjectPos> findByNeedContaining(String value);


    Page<ProjectPos> findByNeedContainingAndResourceIn
            (String value, String res, Pageable pageable);



//    @Transactional
//    long deleteBySocietyContactReference(String societyContactReference);
//
//    @Transactional
//    long deleteBySocietyContactSocietyReference(String societyReference);
}
