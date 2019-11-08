package cl.streamlink.contact.domain;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class Commande{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reference;
    private String etat;
    private java.util.Date date;
    private String typeReglement;
    private String conditionReglement;
    private String typePayement;
    private double tva;
    private long showTva;
    private String langue;
    private long accordClient;
    private String refClient;
    private long showResource;
    private long showTjm;
    private long showRib;
    private long showOuvres;
    private long showFactor;
    private long showNumber;
    private long showProject;
    private long showFrais;
    private long groupmission;
    private double montantht;
    private double montantR;
    private String rib;
    private String crmsociete;

    @ManyToOne
    private Positioning project;
    @ManyToOne
    private User user;

    private int duree;

    public Commande() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public long getShowTva() {
        return showTva;
    }

    public void setShowTva(long showTva) {
        this.showTva = showTva;
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

    public long getShowResource() {
        return showResource;
    }

    public void setShowResource(long showResource) {
        this.showResource = showResource;
    }

    public long getShowTjm() {
        return showTjm;
    }

    public void setShowTjm(long showTjm) {
        this.showTjm = showTjm;
    }

    public long getShowRib() {
        return showRib;
    }

    public void setShowRib(long showRib) {
        this.showRib = showRib;
    }

    public long getShowOuvres() {
        return showOuvres;
    }

    public void setShowOuvres(long showOuvres) {
        this.showOuvres = showOuvres;
    }

    public long getShowFactor() {
        return showFactor;
    }

    public void setShowFactor(long showFactor) {
        this.showFactor = showFactor;
    }

    public long getShowNumber() {
        return showNumber;
    }

    public void setShowNumber(long showNumber) {
        this.showNumber = showNumber;
    }

    public long getShowProject() {
        return showProject;
    }

    public void setShowProject(long showProject) {
        this.showProject = showProject;
    }

    public long getShowFrais() {
        return showFrais;
    }

    public void setShowFrais(long showFrais) {
        this.showFrais = showFrais;
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

    public Positioning getProject() {
        return project;
    }

    public void setProject(Positioning project) {
        this.project = project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public double getMontantR() {
        return montantR;
    }

    public void setMontantR(double montantR) {
        this.montantR = montantR;
    }
}