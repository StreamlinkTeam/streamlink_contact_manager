package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.Positioning;
import cl.streamlink.contact.domain.ProjectPos;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.PositioningRepository;
import cl.streamlink.contact.repository.ProjectPosRepository;
import cl.streamlink.contact.security.SecurityUtils;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.PositioningStage;
import cl.streamlink.contact.web.dto.ProjectPosDTO;
import cl.streamlink.contact.web.dto.UserDTO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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
    UserService userService;
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

    public List<ProjectPos> getProjectByCurrentUser() {
        String a = SecurityUtils.getCurrentUserLogin();
        return projectPosRepository.findByResource(a);
    }
}
