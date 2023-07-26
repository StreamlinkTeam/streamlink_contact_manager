package cl.streamlink.contact.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.streamlink.contact.domain.ContractHistory;

public interface ContractHistoryRepository extends JpaRepository<ContractHistory, Long> {

    List<ContractHistory> findByDeveloperReference(String developerReference);
}
