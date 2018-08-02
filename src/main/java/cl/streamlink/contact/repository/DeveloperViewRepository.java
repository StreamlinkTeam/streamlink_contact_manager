package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.Developer;
import cl.streamlink.contact.web.dto.DeveloperResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "developer", path = "developer")
public interface DeveloperViewRepository extends PagingAndSortingRepository<Developer, Long> {


    @RestResource(path = "filter", rel = "filter")
    Page<Developer> findByFirstnameContainingOrLastnameContainingOrSkillsInformationTitleContainingOrSkillsInformationLanguagesContaining
            (@Param("value") String param1, @Param("value") String param2, @Param("value") String param3, @Param("value") String param4, Pageable pageable);
}
