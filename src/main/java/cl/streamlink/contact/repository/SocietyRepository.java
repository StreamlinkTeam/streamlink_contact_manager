package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.Society;
import cl.streamlink.contact.domain.SocietyActivityArea;
import cl.streamlink.contact.domain.SocietyStage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SocietyRepository extends JpaRepository<Society, Long> {

    Optional<Society> findOneByReference(String reference);

    Page<Society> findByLabelContainingAndStageInAndActivityAreaIn
            (String value, List<SocietyStage> stages, List<SocietyActivityArea> activityAreas, Pageable pageable);
}