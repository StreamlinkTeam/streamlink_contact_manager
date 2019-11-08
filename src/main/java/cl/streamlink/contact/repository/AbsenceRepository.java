package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.Absence;
import cl.streamlink.contact.domain.AbsenceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface AbsenceRepository extends JpaRepository<Absence, Long> {


    Optional<Absence> findOneByReference(String reference);
    Optional<Absence> findOneByAbsenceList(String reference);
    List<Absence> findByAbsenceListManagerReferenceAndAbsenceListResourceReference(String managerReference, String resourceReference);
    List<Absence> findByAbsenceListManagerReference(String managerReference);

//to do
    List<Absence> findByAbsenceList_Reference(String ref);

    List<Absence> findByAbsenceList(AbsenceList absenceList);

    @Modifying
    @Query(value = "update Absence t set t.state = 'V' where t.id = ?1")
    @Transactional
    void validateAbsence(long id);
}
