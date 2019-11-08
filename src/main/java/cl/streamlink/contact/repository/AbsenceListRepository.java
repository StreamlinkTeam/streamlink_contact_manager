package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface AbsenceListRepository extends JpaRepository<AbsenceList, Long> {
    Optional<AbsenceList> findOneByReference(String reference);
    Optional<AbsenceList> findOneByManager(String reference);

    List<AbsenceList> findByManagerAndResource(String managerReference, String resourceReference);

    List<AbsenceList> findByResource(String resourceReference);

    List<AbsenceList> findAllByResource(Resource resource);

    List<AbsenceList> findByManager(User manager);

    @Modifying
    @Query(value = "update AbsenceList t set t.state = 'V' where t.id = ?1")
    @Transactional
    void validateAbsenceList(long id);
}
