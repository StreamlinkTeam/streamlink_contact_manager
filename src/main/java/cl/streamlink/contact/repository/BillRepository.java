package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill, Long> {

    Optional<Bill> findOneByReference(String reference);

    Optional<Bill> findByResourceReference(String resourceReference);

    List<Bill> findByReferenceContaining(String value);

    Page<Bill> findByTitleContaining(String value, Pageable pageable);

//    Page<ProjectPos> findByNeedTitleContaining(String value, Pageable pageable);


}
