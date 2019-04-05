package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.Positioning;
import cl.streamlink.contact.domain.ProjectPos;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.PositioningRepository;
import cl.streamlink.contact.repository.ProjectPosRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.ActivityArea;
import cl.streamlink.contact.utils.enums.PositioningStage;
import cl.streamlink.contact.utils.enums.ProjectStage;
import cl.streamlink.contact.utils.enums.ProjectType;
import cl.streamlink.contact.web.dto.ProjectPosDTO;
import cl.streamlink.contact.web.dto.ProjectResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectPosService {

    @Inject
    ProjectPosRepository projectPosRepository;

    @Inject
    PositioningRepository positioningRepository;

    @Inject
    private ApiMapper mapper;

    public ProjectPosDTO createProjectFromPositioning(String positioningReference) {

        if (!projectPosRepository.existsByReference(positioningReference)) {
            Positioning positioning = positioningRepository.findOneByReference(positioningReference)

                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Positioning", positioningReference));

            ProjectPos projectPos = mapper.fromPositioningToProject(positioning);
            projectPos.setStage(PositioningStage.Won);

            positioningRepository.delete(positioning);
            return mapper.fromBeanToDTO(projectPosRepository.save(projectPos));
        } else
            throw ContactApiException.unprocessableEntityExceptionBuilder("project-exist", null);
    }


    public List<ProjectPosDTO> getProjects(String projectReference) throws ContactApiException {

        if (MiscUtils.isNotEmpty(projectReference))
            return Collections.singletonList(mapper.fromBeanToDTO(projectPosRepository.findOneByReference(projectReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("ProjectPos", projectReference))));

        else
            return projectPosRepository.findAll().stream().map(mapper::fromBeanToDTO).collect(Collectors.toList());
    }

    public ProjectPosDTO getProject(String projectReference) throws ContactApiException {

        return mapper.fromBeanToDTO(projectPosRepository.findOneByReference(projectReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("ProjectPos", projectReference)));
    }

    public ProjectPosDTO updateProject(ProjectPosDTO projectPosDTO, String projectReference) throws ContactApiException {

        ProjectPos projectPos = projectPosRepository.findOneByReference(projectReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("ProjectPos", projectReference));

        mapper.updateBeanFromDto(projectPosDTO, projectPos);
        return mapper.fromBeanToDTO(projectPosRepository.save(projectPos));
    }

    public Page<ProjectPosDTO> searchProjects(String value, ProjectStage stage, ProjectType type,
                                                   ActivityArea activityArea, Pageable pageable) {


        if (MiscUtils.isEmpty(value))
            value = "";

        List<ProjectStage> stages;
        if (stage != null)
            stages = Collections.singletonList(stage);
        else
            stages = ProjectStage.getAll();

        List<ActivityArea> activityAreas;
        if (activityArea != null)
            activityAreas = Collections.singletonList(activityArea);
        else
            activityAreas = ActivityArea.getAll();

        List<ProjectType> types;
        if (type != null)
            types = Collections.singletonList(type);
        else
            types = ProjectType.getAll();

        return projectPosRepository.
                findByNeedContainingAndStageIn
                        (value,  stages,  types, activityAreas,  pageable)
                .map(projectPos -> mapper.fromBeanToDTO(projectPos));
    }
}
