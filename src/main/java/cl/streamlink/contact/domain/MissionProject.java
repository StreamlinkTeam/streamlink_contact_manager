package cl.streamlink.contact.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Entity
@Table(indexes = {@Index(name = "index_mission_project_reference", columnList = "reference", unique = true)})
@EntityListeners(AuditingEntityListener.class)
public class MissionProject {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reference;
}
