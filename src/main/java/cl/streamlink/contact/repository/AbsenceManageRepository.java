package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.AbsenceManage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AbsenceManageRepository extends JpaRepository<AbsenceManage, Long> {

    Optional<AbsenceManage> findOneByReference(String reference);
    Optional<AbsenceManage> findOneByResourceReference(String resourceReference);

}
