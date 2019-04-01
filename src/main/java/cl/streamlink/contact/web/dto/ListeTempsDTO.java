package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.utils.enums.ListeTempsEtat;

import java.util.Date;

public class ListeTempsDTO {

    private String reference;

    private ListeTempsEtat etat;

    private String commentaire;


    private Date date;


    private boolean cloture;


    private String societyReference;

    private String resourceReference;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public ListeTempsEtat getEtat() {
        return etat;
    }

    public void setEtat(ListeTempsEtat etat) {
        this.etat = etat;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isCloture() {
        return cloture;
    }

    public void setCloture(boolean cloture) {
        this.cloture = cloture;
    }

    public String getSocietyReference() {
        return societyReference;
    }

    public void setSocietyReference(String societyReference) {
        this.societyReference = societyReference;
    }

    public String getResourceReference() {
        return resourceReference;
    }

    public void setResourceReference(String resourceReference) {
        this.resourceReference = resourceReference;
    }


}
