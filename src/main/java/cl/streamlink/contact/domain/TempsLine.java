package cl.streamlink.contact.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(indexes = {@Index(name = "index_temps_line_reference", columnList = "reference", unique = true)})
@EntityListeners(AuditingEntityListener.class)
public class TempsLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reference;


    @ManyToOne
    private ListeTemps listTemps;

    @ManyToOne
    private MissionProject missionProject;

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

    public ListeTemps getListTemps() {
        return listTemps;
    }

    public void setListTemps(ListeTemps listTemps) {
        this.listTemps = listTemps;
    }

    public MissionProject getMissionProject() {
        return missionProject;
    }

    public void setMissionProject(MissionProject missionProject) {
        this.missionProject = missionProject;
    }


}
