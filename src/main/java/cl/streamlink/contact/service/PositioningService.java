package cl.streamlink.contact.service;


import cl.streamlink.contact.domain.Positioning;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.PositioningRepository;
import cl.streamlink.contact.repository.ProjectRepository;
import cl.streamlink.contact.repository.ResourceRepository;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.PositioningStage;
import cl.streamlink.contact.utils.enums.ProjectStage;
import cl.streamlink.contact.utils.enums.ProjectType;
import cl.streamlink.contact.utils.enums.ResourceType;
import cl.streamlink.contact.web.dto.PositioningDTO;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositioningService {

    private final Logger logger = LoggerFactory.getLogger(PositioningService.class);

    @Inject
    private ProjectRepository projectRepository;

    @Inject
    private ProjectService projectService;

    @Inject
    private ResourceRepository resourceRepository;

    @Inject
    private PositioningRepository positioningRepository;


    @Inject
    private ApiMapper mapper;

    public PositioningDTO createPositioning(PositioningDTO positioningDTO, String projectReference) {

        projectService.getProject(projectReference);


        positioningDTO.setProjectReference(projectReference);
        Positioning positioning = mapper.fromDTOToBean(positioningDTO);

        positioning.setReference(MiscUtils.generateReference());
        return mapper.fromBeanToDTO(positioningRepository.save(positioning));
    }

    public PositioningDTO updatePositioning(PositioningDTO positioningDTO, String positioningReference, String projectReference) throws ContactApiException {


        projectService.getProject(projectReference);

        Positioning positioning = positioningRepository.findOneByReferenceAndProjectReference(positioningReference, projectReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Positioning", positioningReference));

        mapper.updateBeanFromDto(positioningDTO, positioning);
        return mapper.fromBeanToDTO(positioningRepository.save(positioning));
    }

    public PositioningDTO getPositioning(String positioningReference, String projectReference) throws ContactApiException {

        projectService.getProject(projectReference);
        return mapper.fromBeanToDTO(positioningRepository.findOneByReferenceAndProjectReference(positioningReference, projectReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Positioning", positioningReference)));
    }


    public List<PositioningDTO> getPositionings(String positioningReference) throws ContactApiException {

        if (positioningReference != null)
            return Collections.singletonList(mapper.fromBeanToDTO(positioningRepository.findOneByReference(positioningReference)
                    .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Positioning", positioningReference))));

        else
            return positioningRepository.findAll().stream().map(mapper::fromBeanToDTO).collect(Collectors.toList());
    }

    public Page<PositioningDTO> searchPositionings(String value, ProjectStage projectStage, ProjectType projectType,
                                                   ResourceType type, PositioningStage stage, Pageable pageable) {


        if (MiscUtils.isEmpty(value))
            value = "";

        List<PositioningStage> stages;
        if (stage != null)
            stages = Collections.singletonList(stage);
        else
            stages = PositioningStage.getAll();

        List<ProjectStage> projectStages;
        if (projectStage != null)
            projectStages = Collections.singletonList(projectStage);
        else
            projectStages = ProjectStage.getAll();

        List<ProjectType> projectTypes;
        if (projectType != null)
            projectTypes = Collections.singletonList(projectType);
        else
            projectTypes = ProjectType.getAll();

        List<ResourceType> types;
        if (type != null)
            types = Collections.singletonList(type);
        else
            types = ResourceType.getAll();

        return positioningRepository.
                findByProjectTitleContainingAndProjectStageInAndProjectTypeInAndResourceTypeInAndStageIn
                        (value, projectStages, projectTypes, types, stages, pageable)
                .map(positioning -> mapper.fromBeanToDTO(positioning));
    }

    public JSONObject deletePositioning(String positioningReference, String projectReference) throws ContactApiException {

        projectService.getProject(projectReference);
        Positioning positioning = positioningRepository.findOneByReferenceAndProjectReference(positioningReference, projectReference)
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Positioning", positioningReference));

        positioningRepository.delete(positioning);

        return MiscUtils.createSuccessfullyResult();
    }

}
