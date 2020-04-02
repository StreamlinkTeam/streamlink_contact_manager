package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.Commande;
import cl.streamlink.contact.domain.Resource;
import cl.streamlink.contact.domain.TimeLine;
import cl.streamlink.contact.domain.User;
import cl.streamlink.contact.service.*;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.ProductionDTO;
import cl.streamlink.contact.web.dto.TimeMonthDTO;
import javafx.animation.Timeline;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
                countDays(e.getProject().getResource().getId())
                //MiscUtils.daysDiff(e.getDate(), new Date())
        )).collect(Collectors.toList());
        return productions;
    }

    private double countDays(long id) {
        Date dateToConvert = new Date();
        LocalDate today = dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        Resource resource = resourceService.getById(id).get();

        List<TimeLine> times = timeLineService.getAllByResource(resource);
        return times.stream().filter(e -> e.getStart().isBefore(today)).mapToDouble(o -> o.getTimeWork()).sum();
    }


}
