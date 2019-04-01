package cl.streamlink.contact.domain;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(indexes = {@Index(name = "index_temps_reference", columnList = "reference", unique = true)})
@EntityListeners(AuditingEntityListener.class)
public class Temps {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reference;


    private Date tempsDate;

    private float tempsDuree;


    @ManyToOne
    private TempsLine tempsLine;


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


    public Date getTempsDate() {
        return tempsDate;
    }


    public void setTempsDate(Date tempsDate) {
        this.tempsDate = tempsDate;
    }


    public float getTempsDuree() {
        return tempsDuree;
    }


    public void setTempsDuree(float tempsDuree) {
        this.tempsDuree = tempsDuree;
    }


    public TempsLine getTempsLine() {
        return tempsLine;
    }


    public void setTempsLine(TempsLine tempsLine) {
        this.tempsLine = tempsLine;
    }


}
