package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.Resource;
import cl.streamlink.contact.domain.TimeLine;
import cl.streamlink.contact.exception.ContactApiException;
import cl.streamlink.contact.mapper.ApiMapper;
import cl.streamlink.contact.repository.ResourceRepository;
import cl.streamlink.contact.repository.TimeLineRepository;
import cl.streamlink.contact.security.SecurityUtils;
import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.web.dto.TimeLineDTO;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TimeLineService {


    private final Logger logger = LoggerFactory.getLogger(TimeLineService.class);

    @Inject
    private TimeLineRepository timeLineRepository;

    @Inject
    private ResourceRepository resourceRepository;

    @Inject
    private ApiMapper mapper;


    public TimeLineDTO getLine(String lineTempsReference) throws ContactApiException {

        return mapper.fromBeanToDTO(timeLineRepository.findOneByReference(
                lineTempsReference).orElseThrow(() ->
                ContactApiException.resourceNotFoundExceptionBuilder("TimeLine"
                        , lineTempsReference)));
    }


    public JSONObject deleteLineTemps(String lineTempsReference) throws ContactApiException {


        timeLineRepository.deleteByReference(lineTempsReference);

        return MiscUtils.createSuccessfullyResult();
    }


    public TimeLineDTO createLineTemps(TimeLineDTO lineTempsDTO) {

        TimeLine timeLine = mapper.fromDTOToBean(lineTempsDTO);

        timeLine.setReference(MiscUtils.generateReference());
        return
                mapper.fromBeanToDTO(timeLineRepository.save(timeLine));
    }


    public TimeLineDTO updateLineTemps(TimeLineDTO lineTempsDTO, String lineTempsReference) throws ContactApiException {

        TimeLine ligneTemps =
                timeLineRepository.findOneByReference(lineTempsReference).orElseThrow(()
                        -> ContactApiException.resourceNotFoundExceptionBuilder("TimeLine",
                        lineTempsReference));

        mapper.updateBeanFromDto(lineTempsDTO, ligneTemps);
        return
                mapper.fromBeanToDTO(timeLineRepository.save(ligneTemps));
    }


    public List<TimeLineDTO> getDeTimeLines(String lineTempsReference) throws ContactApiException {

        if (lineTempsReference != null)
            return Collections.singletonList(mapper.fromBeanToDTO(
                    timeLineRepository.findOneByReference(lineTempsReference).orElseThrow(() -> ContactApiException
                            .resourceNotFoundExceptionBuilder("TimeLine", lineTempsReference))));

        else
            return timeLineRepository.findAll().stream().map(mapper::fromBeanToDTO)
                    .collect(Collectors.toList());
    }

    public TimeLine createForCurrentResource(TimeLine timeLine) throws ContactApiException {

        timeLine.setReference(MiscUtils.generateReference());

        Resource resource = resourceRepository.findOneByEmail(SecurityUtils.getCurrentUserLogin())
                .orElseThrow(() -> ContactApiException.resourceNotFoundExceptionBuilder("Resource", SecurityUtils.getCurrentUserLogin()));
        timeLine.setResource(resource);

        List<TimeLine> timeline2 = timeLineRepository.findAllByStartAndResourceReference(timeLine.getStart(), resource.getReference());
        if (timeline2.size() > 0) {
            for (int i = 0; i < timeline2.size(); i++) {
                timeLineRepository.delete(timeline2.get(i));
            }
        }
        return timeLineRepository.save(timeLine);
    }

    public List<TimeLine> getAllTimeLines() {
        return timeLineRepository.findAll();
    }

    public List<TimeLine> findAllByResourceReference(String resourceReference) {
        return timeLineRepository.findAllByResourceReference(resourceReference);
    }

    public List<JSONObject> getGrouped() {

        return timeLineRepository.getGrouped();
    }

    public List<TimeLine> getAllByDateAndResourceReference(LocalDate date, String resourceReference) {
        return timeLineRepository.findAllByStartAndResourceReference(date, resourceReference);
    }

    public List<TimeLine> getByMonthAndYear(int month, int year, long id) {
        return timeLineRepository.getByMonthAndYear(month, year, id);
    }

    public TimeLine validateTimeline(TimeLine timeLine) {
        timeLineRepository.validateTimeline(timeLine.getId());
        return timeLine;
    }

    public void validate(LocalDate d, Resource r) {
        timeLineRepository.validate(d, r);
    }

}
