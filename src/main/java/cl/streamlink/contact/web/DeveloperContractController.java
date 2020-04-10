package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.Developer;
import cl.streamlink.contact.mapper.DeveloperApiMapper;
import cl.streamlink.contact.repository.DeveloperRepository;
import cl.streamlink.contact.service.DeveloperService;
import cl.streamlink.contact.web.abstractdevres.AbstractContractController;
import cl.streamlink.contact.web.dto.DeveloperDTO;
import cl.streamlink.contact.web.dto.DeveloperResponseDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ws/developers")
@CrossOrigin("*")
public class DeveloperContractController extends AbstractContractController
        <Developer, DeveloperRepository, DeveloperDTO, DeveloperResponseDTO, DeveloperApiMapper, DeveloperService> {
}
