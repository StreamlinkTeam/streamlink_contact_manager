package cl.streamlink.contact.web;

import cl.streamlink.contact.domain.*;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.service.*;
import cl.streamlink.contact.web.dto.TimeLineDTO;
import cl.streamlink.contact.web.dto.TimeMonthDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ws/time_line")
public class TimeLineController {

    @Autowired
    BillService billService;

    @Inject
    private TimeLineService timeLineService;

    @Autowired
    UserService userService;

    @Autowired
    DeveloperService developerService;

    @Autowired
    ResourceService resourceService;

    @GetMapping

    @ResponseStatus(HttpStatus.OK)

    @ApiOperation(value = "Get lineTemps Details Service")

    @ApiResponses(value = {

            // @ApiResponse(code = 200, message = "Operation Executed Successfully", response = TimeLine.class),

            @ApiResponse(code = 404, message = "lineTemps with Ref not Found")})

    public TimeLineDTO getLineTemps(@RequestParam(value = "ligneTempsReference") String lineTempsReference) throws ContactApiException {
        return timeLineService.getLine(lineTempsReference);
    }


    @RequestMapping(value = "", method = RequestMethod.DELETE, produces =
            MediaType.APPLICATION_JSON_VALUE)

    @ResponseStatus(HttpStatus.OK)

    @ApiOperation(value = "Delete ligne Temps Service")

    @ApiResponses(value = {

            @ApiResponse(code = 200, message = "Operation Executed Successfully",
                    response = TimeLine.class),

            @ApiResponse(code = 404, message = "ligneTemps with Ref not Found")})
    public JSONObject deleteLigneTemps(@RequestParam("ligneTempsReference") String
                                               ligneTempsReference) throws ContactApiException {

        return timeLineService.deleteLineTemps(ligneTempsReference);
    }


    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create TimeLine Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response = TimeLine.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })

    public TimeLineDTO createLineTemps(@RequestBody @Valid TimeLineDTO lineTemps) {

        return timeLineService.createLineTemps(lineTemps);
    }

   @RequestMapping(value = "",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Create ligne temps Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response =  TimeLine.class),
            @ApiResponse(code = 400, message = "Validation Error, Database conflict")
    })
    public TimeLineDTO updateProject(@Valid @RequestBody TimeLineDTO lineTemps, @RequestParam(value = "ligneTempsReference") String lineTempsReference) throws ContactApiException {

        return timeLineService.updateLineTemps(lineTemps,lineTempsReference);

    }


    @RequestMapping(value = "tous",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get timeLines Details Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Executed Successfully", response =TimeLine.class),
            @ApiResponse(code = 404, message = "Developer with Ref not Found")
    })
    public List<TimeLine> getDevelopers() {

        return timeLineService.getAllTimeLines().stream().filter(e -> e.getStatus() == 1).collect(Collectors.toList());
    }

    @GetMapping("date")
    public List<TimeLine> getByDate(@RequestParam String start) {

        Date startd = new Date();

        LocalDate startDate = startd.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        User user = userService.getCurrentUser();

        return timeLineService.getAllByDateAndResource(startDate, resourceService.getResourceByEmail(user.getEmail()));
    }

    @GetMapping("all")
    public List<TimeLine> getTimelines() {
        User user = userService.getCurrentUser();
        return timeLineService.getAllTimeLines().stream().filter(e -> e.getResource().getContact().getEmail1().equalsIgnoreCase(user.getEmail())).collect(Collectors.toList());
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public TimeLine saveTimeLine(@RequestBody TimeLine timeLine) {
        User user = userService.getCurrentUser();
        timeLine.setResource(resourceService.getResourceByEmail(user.getEmail()));
        return timeLineService.save(timeLine);
    }

    @RequestMapping(value = "timelines", method = RequestMethod.GET)
    public  List<TimeLine> getAllTimeLines() {
        return timeLineService.getAllTimeLines();
    }

    @GetMapping(value = "timeline")
    public List<TimeLine> getByDeveloper(@RequestParam long id){
        List<TimeLine> timelines = this.getAllTimeLines().stream().filter(e -> e.getResource().getId() == id)
                .collect(Collectors.toList());
        /*Optional<Resource> resource = resourceService.getById(id);
        System.err.println("Resource :: "+resource.get().getFirstname());
        return timeLineService.getAllByResource(resource.get());*/
        return timelines;
    }

    @GetMapping(value = "days")
    public long getNumberOfDays(@RequestParam long id, @RequestParam String start){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date startd;
        try {
            startd = formatter.parse(start);
        } catch (ParseException e) {
            return 0;
        }

        LocalDate startDate = startd.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        Date end = new Date();
        LocalDate endDate = end.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        long total = this.getAllTimeLines().stream().filter(e -> e.getResource().getId() == id)
                .filter(e -> e.getStart().isBefore(endDate) && e.getStart().isAfter(startDate))
                .count();
        return total;
    }

    @GetMapping(value = "totaldays")
    public long getNumberOfTotalDays(@RequestParam long id, @RequestParam String start, @RequestParam String end){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date startd;
        Date endd;
        try {
            startd = formatter.parse(start);
            endd = formatter.parse(end);
        } catch (ParseException e) {
            return 0;
        }

        LocalDate startDate = startd.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        LocalDate endDate = endd.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        long total = this.getAllTimeLines().stream().filter(e -> e.getResource().getId() == id)
                .filter(e -> e.getStart().isBefore(endDate) && e.getStart().isAfter(startDate))
                .count();
        return total;
    }

    @GetMapping(value="/grouped")
    public List<JSONObject> getAllGrouped(){
        JSONObject o;
        List<JSONObject> list = timeLineService.getGrouped();

        User user;
        List<Bill> bills = billService.getAllBills();

        for(int i=0; i<list.size();i++) {
            o = list.get(i);
            userService.getById(Long.parseLong(o.get("id").toString()));
        }

        return list;
    }

    @PostMapping("validate")
    public boolean validateTimeline(@RequestBody JSONObject timeline) {
        int month = Integer.valueOf(timeline.get("month").toString());
        int year = Integer.valueOf(timeline.get("year").toString());
        long id = Long.parseLong(timeline.get("id").toString());


        List<TimeLine> timeLines = timeLineService.getByMonthAndYear(month, year, id);
        System.err.println("m :: "+month+" :: "+year+" :: "+id+" :: "+timeLines.size());
        for(int i = 0; i< timeLines.size(); i++) {
            timeLineService.validateTimeline(timeLines.get(i));
        }

        return true;
    }
}

