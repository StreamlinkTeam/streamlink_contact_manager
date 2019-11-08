package cl.streamlink.contact.web.dto;

import java.util.Date;

public class CommandeDTO {

    private String reference;
    private String etat;
    private java.util.Date date;
    private String typeReglement;
    private String conditionReglement;
    private String typePayement;
    private double tva;
    private String langue;
    private long accordClient;
    private String refClient;
    private long groupmission;
    private double montantht;
    private String rib;
    private String crmsociete;
    private long projectId;
    private long userId;
    private int duree;

    public CommandeDTO() {
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTypeReglement() {
        return typeReglement;
    }

    public void setTypeReglement(String typeReglement) {
        this.typeReglement = typeReglement;
    }

    public String getConditionReglement() {
        return conditionReglement;
    }

    public void setConditionReglement(String conditionReglement) {
        this.conditionReglement = conditionReglement;
    }

    public String getTypePayement() {
        return typePayement;
    }

    public void setTypePayement(String typePayement) {
        this.typePayement = typePayement;
    }

    public double getTva() {
        return tva;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public long getAccordClient() {
        return accordClient;
    }

    public void setAccordClient(long accordClient) {
        this.accordClient = accordClient;
    }

    public String getRefClient() {
        return refClient;
    }

    public void setRefClient(String refClient) {
        this.refClient = refClient;
    }

    public long getGroupmission() {
        return groupmission;
    }

    public void setGroupmission(long groupmission) {
        this.groupmission = groupmission;
    }

    public double getMontantht() {
        return montantht;
    }

    public void setMontantht(double montantht) {
        this.montantht = montantht;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public String getCrmsociete() {
        return crmsociete;
    }

    public void setCrmsociete(String crmsociete) {
        this.crmsociete = crmsociete;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getUserId() {
        return userId;
    }


    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
}
