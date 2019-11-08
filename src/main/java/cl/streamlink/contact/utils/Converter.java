package cl.streamlink.contact.utils;

import cl.streamlink.contact.domain.Commande;
import cl.streamlink.contact.web.dto.CommandeDTO;

public class Converter {

    public static Commande fromCommandeDTO(CommandeDTO commandeDTO) {
        Commande commande = new Commande();

        commande.setAccordClient(commandeDTO.getAccordClient());
        commande.setEtat(commandeDTO.getEtat());
        commande.setDate(commandeDTO.getDate());
        commande.setTypePayement(commandeDTO.getTypePayement());
        commande.setTva(commandeDTO.getTva());
        commande.setConditionReglement(commandeDTO.getConditionReglement());
        commande.setMontantht(commandeDTO.getMontantht());
        commande.setRefClient(commandeDTO.getRefClient());
        commande.setLangue(commandeDTO.getLangue());
        commande.setRib(commandeDTO.getRib());
        commande.setDuree(commandeDTO.getDuree());

        return commande;
    }
}
