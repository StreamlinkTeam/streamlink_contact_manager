package cl.streamlink.contact.domain;


import cl.streamlink.contact.utils.enums.ListeTempsEtat;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(indexes = {@Index(name = "index_time_list_reference", columnList = "reference", unique = true)})
@EntityListeners(AuditingEntityListener.class)

public class ListeTemps {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reference;


    private String note;


    private Date date;


    private boolean cloture;

    @Enumerated(EnumType.STRING)
    private ListeTempsEtat etat;


    @ManyToOne
    private Resource resource;
    @ManyToOne
    private Society society;

    public Society getSociety() {
        return society;
    }

    public void setSociety(Society society) {
        this.society = society;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public ListeTempsEtat getEtat() {
        return etat;
    }

    public void setEtat(ListeTempsEtat etat) {
        this.etat = etat;
    }


}
