package cl.streamlink.contact.web;


import cl.streamlink.contact.domain.ProjectPos;
import cl.streamlink.contact.domain.Resource;
import cl.streamlink.contact.service.ProjectPosService;
import cl.streamlink.contact.web.dto.ProjectPosDTO;
import cl.streamlink.contact.web.dto.ResourceDTO;
import cl.streamlink.contact.web.dto.UserDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/ws/projectspos")
public class ProjectPosController {

    @Inject
    ProjectPosService projectPosService;

    @PostMapping(value = "from-positioning")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create Project Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = ProjectPos.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public ProjectPosDTO createProjectFromPositioning(@RequestParam(value = "positioningReference") String positioningReference) {
        return projectPosService.createProjectFromPositioning(positioningReference);
    }

    @GetMapping(value = "all")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Projects Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = ProjectPos.class),
            @ApiResponse(code = 404, message = "Project with Ref not Found")
    })
    public List<ProjectPosDTO> getProjects() {
        return projectPosService.getProjects(null);
    }



   @GetMapping("aa")
    public List<ProjectPos> getProjectByMe() {
        return projectPosService.getProjectByCurrentUser();
    }
}
