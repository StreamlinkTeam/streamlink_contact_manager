package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.Resource;
import cl.streamlink.contact.mapper.ResourceApiMapper;
import cl.streamlink.contact.repository.ResourceRepository;
import cl.streamlink.contact.service.ResourceService;
import cl.streamlink.contact.web.abstractdevres.AbstractEvaluationController;
import cl.streamlink.contact.web.dto.ResourceDTO;
import cl.streamlink.contact.web.dto.ResourceResponseDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ws/resources")
@CrossOrigin("*")
public class ResourceEvaluationController extends AbstractEvaluationController
        <Resource, ResourceRepository, ResourceDTO, ResourceResponseDTO, ResourceApiMapper, ResourceService> {
}
