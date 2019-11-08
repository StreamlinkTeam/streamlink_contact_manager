package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.Project;
import cl.streamlink.contact.service.PositioningService;
import cl.streamlink.contact.service.ProjectService;
import cl.streamlink.contact.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ws/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    ResourceService resourceService;

    @Autowired
    PositioningService positioningService;


   @PostMapping("/create")
    public Project CreateProject(@RequestBody Project project) {
       return projectService.save(project);
   }

}


