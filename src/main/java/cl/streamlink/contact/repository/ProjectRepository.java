package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.Project;
import cl.streamlink.contact.utils.enums.ProjectStage;
import cl.streamlink.contact.utils.enums.ProjectType;
import cl.streamlink.contact.utils.enums.ActivityArea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findOneByReference(String reference);

    Page<Project> findByTitleContainingAndStageInAndTypeInAndProjectInformationActivityAreaIn
            (String value, List<ProjectStage> stages, List<ProjectType> types,
             List<ActivityArea> activityAreas, Pageable pageable);
}