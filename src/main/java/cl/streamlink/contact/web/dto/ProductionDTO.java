package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.domain.*;
import javafx.geometry.Pos;

public class ProductionDTO {

    private Positioning project;
    private String Client;
    private Resource user;
    private Commande commande;
    private double ca;
    private double caht;
    private double prod;

    public ProductionDTO() {
    }

    public ProductionDTO(Positioning project, String client, Commande commande,  Resource user,  double ca, double caht, double prod) {
        this.project = project;
        Client = client;
        this.user = user;
        this.commande = commande;
        this.ca = ca;
        this.caht = caht;
        this.prod = prod;
    }

    public Positioning getProject() {
        return project;
    }

    public void setProject(Positioning project) {
        this.project = project;
    }

    public String getClient() {
        return Client;
    }

    public void setClient(String client) {
        Client = client;
    }

    public Resource getUser() {
        return user;
    }

    public void setUser(Resource user) {
        this.user = user;
    }

    public double getCa() {
        return ca;
    }

    public void setCa(double ca) {
        this.ca = ca;
    }

    public double getCaht() {
        return caht;
    }

    public void setCaht(double caht) {
        this.caht = caht;
    }

    public double getProd() {
        return prod;
    }

    public void setProd(double prod) {
        this.prod = prod;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
}
