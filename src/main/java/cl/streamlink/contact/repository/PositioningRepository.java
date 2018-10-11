package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.Positioning;
import cl.streamlink.contact.domain.Project;
import cl.streamlink.contact.utils.enums.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PositioningRepository extends JpaRepository<Positioning, Long> {

    Optional<Positioning> findOneByReference(String reference);

    Optional<Positioning> findOneByReferenceAndProjectReference(String reference, String projectReference);


    Page<Positioning> findByProjectTitleContainingAndProjectStageInAndProjectTypeInAndResourceResourceTypeInAndStageIn
            (String value, List<ProjectStage> projectStages, List<ProjectType> projectTypes,
             List<ResourceType> resourceTypes, List<PositioningStage> stages, Pageable pageable);
}