package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.domain.Experience;
import cl.streamlink.contact.domain.Formation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Chemakh on 11/07/2018.
 */
public class SkillsInformationDTO {

    private String developerReference;

    private String title;

    private Set<LanguageDTO> languages = new HashSet<>();

    private List<String> qualifications = new ArrayList<String>();

    private Experience experience;

    private Formation formation;

    public String getDeveloperReference() {
        return developerReference;
    }

    public void setDeveloperReference(String developerReference) {
        this.developerReference = developerReference;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<LanguageDTO> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<LanguageDTO> languages) {
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
