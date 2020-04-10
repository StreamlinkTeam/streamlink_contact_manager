package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.Commande;
import cl.streamlink.contact.domain.TimeLine;
import cl.streamlink.contact.service.*;
import cl.streamlink.contact.web.dto.ProductionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ws/production")
public class ProductionController {

    @Autowired
    TimeLineService timeLineService;

    @Autowired
    CommandeService commandeService;

    @Autowired
    NeedService needService;

    @Autowired
    UserService userService;

    @Autowired
    ResourceService resourceService;

    @GetMapping(value = "/all")
    public List<ProductionDTO> getAll() {
        List<ProductionDTO> productions;
        List<Commande> commandes = commandeService.getAll();
        productions = commandes.stream().map(e ->  new ProductionDTO(
                e.getProject() ,
                e.getRefClient(),
                e,
                e.getProject().getResource(),
                0,
                e.getMontantht(),
                countDays(e.getProject().getResource().getReference())
                //MiscUtils.daysDiff(e.getDate(), new Date())
        )).collect(Collectors.toList());
        return productions;
    }

    private double countDays(String developerReference) {

        Date dateToConvert = new Date();
        LocalDate today = dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();


        List<TimeLine> times = timeLineService.findAllByResourceReference(developerReference);
        return times.stream().filter(e -> e.getStart().isBefore(today)).mapToDouble(TimeLine::getTimeWork).sum();
    }


}
