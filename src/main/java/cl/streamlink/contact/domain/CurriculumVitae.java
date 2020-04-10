package cl.streamlink.contact.domain;

import javax.persistence.*;

/**
 * Created by Utilisateur on 27/07/2018.
 */

@Entity
@Table(indexes = {@Index(name = "index_cv_reference", columnList = "reference", unique = true)})
public class CurriculumVitae {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reference;

    private String label;

    private String name;

    @Column(updatable = false, nullable = false)
    private String developerReference;

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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeveloperReference() {
        return developerReference;
    }

    public void setDeveloperReference(String developerReference) {
        this.developerReference = developerReference;
    }
}
