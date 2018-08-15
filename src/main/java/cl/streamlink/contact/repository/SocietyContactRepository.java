package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.SocietyContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SocietyContactRepository extends JpaRepository<SocietyContact, Long> {

//    Optional<SocietyContact> findOneByReference(String reference);

    List<SocietyContact> findBySocietyReference(String societyReference);

    Optional<SocietyContact> findOneByReferenceAndSocietyReference(String reference,String societyReference);

    @Transactional
    long deleteBySocietyReference(String societyReference);


}