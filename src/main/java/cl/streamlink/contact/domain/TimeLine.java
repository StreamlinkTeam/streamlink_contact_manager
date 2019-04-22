package cl.streamlink.contact.domain;

import cl.streamlink.contact.utils.enums.TimeLineType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(indexes = {@Index(name = "index_time_line_reference", columnList = "reference", unique = true)})
@EntityListeners(AuditingEntityListener.class)
public class TimeLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reference;


    @Enumerated(EnumType.STRING)
    private TimeLineType type;

    @ManyToOne
    private TimeList listTemps;

    @ManyToOne
    private Positioning positioning;

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

    public TimeLineType getType() {
        return type;
    }

    public void setType(TimeLineType type) {
        this.type = type;
    }

    public Positioning getPositioning() {
        return positioning;
    }

    public void setPositioning(Positioning positioning) {
        this.positioning = positioning;
    }

    public TimeList getListTemps() {
        return listTemps;
    }

    public void setListTemps(TimeList listTemps) {
        this.listTemps = listTemps;
    }
}
