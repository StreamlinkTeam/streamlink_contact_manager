package cl.streamlink.contact.web;


import cl.streamlink.contact.domain.*;
import cl.streamlink.contact.service.CommandeService;
import cl.streamlink.contact.service.NeedService;
import cl.streamlink.contact.service.PositioningService;
import cl.streamlink.contact.service.UserService;
import cl.streamlink.contact.utils.Converter;
import cl.streamlink.contact.web.dto.CommandeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ws/commande")
public class CommandeController {

    @Autowired
    CommandeService commandeService;

    @Autowired
    UserService userService;

    @Autowired
    NeedService needService;

    @Autowired
    PositioningService positioningService;

    @GetMapping(value = "/all")
    public List<Commande> getAll() {
        return commandeService.getAll();
    }

    @PostMapping(value = "/save")
    public Commande saveCommande(@RequestBody CommandeDTO commandeDto) {

        Commande commande = Converter.fromCommandeDTO(commandeDto);

        Optional<User> user = userService.getById(commandeDto.getUserId());
        Optional<Positioning> project = positioningService.getById(commandeDto.getProjectId());


        if(user.isPresent()) {
            commande.setUser(user.get());
        }
        if(project.isPresent()) {
            commande.setProject(project.get());
        }
        System.err.println("COMMANDE :: "+ commande.getProject().getReference());
        return commandeService.save(commande);
    }
}
