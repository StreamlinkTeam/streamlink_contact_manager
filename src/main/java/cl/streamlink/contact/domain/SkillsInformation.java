package cl.streamlink.contact.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Chemakh on 11/07/2018.
 */

@Embeddable
public class SkillsInformation {

    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Language> languages = new HashSet<>();

    @ElementCollection
    private List<String> qualifications = new ArrayList<String>();

    @Enumerated(EnumType.STRING)
    private Experience experience;

    @Enumerated(EnumType.STRING)
    private Formation formation;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    public List<String> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<String> qualifications) {
        this.qualifications = qualifications;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }
}
