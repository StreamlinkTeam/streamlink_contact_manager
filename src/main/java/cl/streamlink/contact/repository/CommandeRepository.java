package cl.streamlink.contact.repository;

import cl.streamlink.contact.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

    List<Commande> findAll();
    Commande findOneById(long id);

    List<Commande> findAllByUser(User user);
    List<Commande> findAllByProject(Positioning project);
    Commande save(Commande commande);

    @Modifying
    @Query(value = "update Commande c set c.montantR=?2 where c.id=?1")
    @Transactional
    void updateMontant(Long id, double unitPrice);



}
