package cl.streamlink.contact.service;

import cl.streamlink.contact.domain.Bill;
import cl.streamlink.contact.domain.Commande;
import cl.streamlink.contact.domain.Positioning;
import cl.streamlink.contact.domain.Project;
import cl.streamlink.contact.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CommandeService {

    @Autowired
    CommandeRepository commandeRepository;

    public List<Commande> getAll(){
        return commandeRepository.findAll();
    }

    public List<Commande> getByProject(Positioning project) {
        return commandeRepository.findAllByProject(project);
    }

    public Commande save(Commande commande) {
        return commandeRepository.save(commande);
    }

    public void delete(Commande commande) {
        commandeRepository.delete(commande);
    }

    public Commande getById(long id){
        return commandeRepository.findOneById(id);
    }

    public void updateMontant(Long id, double unitPrice) {
        commandeRepository.updateMontant(id, unitPrice);
    }
}
