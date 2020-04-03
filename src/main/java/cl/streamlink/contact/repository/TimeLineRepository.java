package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.Resource;
import cl.streamlink.contact.domain.Time;
import cl.streamlink.contact.domain.TimeLine;
import cl.streamlink.contact.web.dto.TimeMonthDTO;
import net.minidev.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface TimeLineRepository extends JpaRepository<TimeLine, Long> {

    Optional<TimeLine> findOneByReference(String reference);


    @Transactional
    long deleteByReference(String timeLineReference);

    List<TimeLine> findAllByResource(Resource resource);

    @Query(value = "select resource_id as id, project, extract(year from start) as year, extract(month from start) as month, sum(time_work) as total from time_line group by resource_id, project, extract(year from start), extract(month from start) order by year, month", nativeQuery = true)
    List<JSONObject> getGrouped();

    TimeLine findByStartAndResource(LocalDate date, Resource resource);

    List<TimeLine> findAllByStartAndResource(LocalDate date, Resource resource);

    @Query(value = "select * from time_line where MONTH(start) = ?1 AND YEAR(start) = ?2 AND  resource_id = ?3", nativeQuery = true)
    List<TimeLine> getByMonthAndYear(int month, int year, long id);

    @Modifying
    @Query(value = "update TimeLine t set t.status = 1 where t.id = ?1")
    @Transactional
    void validateTimeline(long id);


    @Modifying
    @Query(value = "update TimeLine t set t.timeWork = 0 where t.resource = ?2 AND t.start = ?1")
    @Transactional
    void validate(LocalDate date, Resource resource);
}
