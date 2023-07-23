package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.Resource;
import cl.streamlink.contact.utils.enums.Experience;
import cl.streamlink.contact.utils.enums.Formation;
import cl.streamlink.contact.utils.enums.ResourceStage;
import cl.streamlink.contact.utils.enums.ResourceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    Optional<Resource> findOneByReference(String reference);

    boolean existsByReference(String reference);

    Page<Resource> findByResourceTypeIn(List<ResourceType> types,
            Pageable pageable);

    List<Resource> findByFirstnameContaining(String value);

}