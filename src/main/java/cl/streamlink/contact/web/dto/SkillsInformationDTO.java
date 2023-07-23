package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.utils.enums.Experience;
import cl.streamlink.contact.utils.enums.Formation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * Created by Chemakh on 11/07/2018.
 */
public class SkillsInformationDTO {

    private String developerReference;

    private String title;

    private String languages;

    private List<String> qualifications = new ArrayList<String>();

    private Experience experience;

    private Formation formation;

    private String baccalaureate;

    private LocalDate baccalaureateDate;

    private String highSchool;

    private String highSchoolMention;

    public String getBaccalaureate() {
        return baccalaureate;
    }

    public void setBaccalaureate(String baccalaureate) {
        this.baccalaureate = baccalaureate;
    }

    public LocalDate getBaccalaureateDate() {
        return baccalaureateDate;
    }

    public void setBaccalaureateDate(LocalDate baccalaureateDate) {
        this.baccalaureateDate = baccalaureateDate;
    }

    public String getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(String highSchool) {
        this.highSchool = highSchool;
    }

    public String getHighSchoolMention() {
        return highSchoolMention;
    }

    public void setHighSchoolMention(String highSchoolMention) {
        this.highSchoolMention = highSchoolMention;
    }

    public String getFirstHigherEducationDiploma() {
        return firstHigherEducationDiploma;
    }

    public void setFirstHigherEducationDiploma(String firstHigherEducationDiploma) {
        this.firstHigherEducationDiploma = firstHigherEducationDiploma;
    }

    public String getFirstHEDUniversity() {
        return firstHEDUniversity;
    }

    public void setFirstHEDUniversity(String firstHEDUniversity) {
        this.firstHEDUniversity = firstHEDUniversity;
    }

    public String getFirstHEDMention() {
        return firstHEDMention;
    }

    public void setFirstHEDMention(String firstHEDMention) {
        this.firstHEDMention = firstHEDMention;
    }

    public LocalDate getFirstHEDDate() {
        return firstHEDDate;
    }

    public void setFirstHEDDate(LocalDate firstHEDDate) {
        this.firstHEDDate = firstHEDDate;
    }

    public String getSecondHigherEducationDiploma() {
        return secondHigherEducationDiploma;
    }

    public void setSecondHigherEducationDiploma(String secondHigherEducationDiploma) {
        this.secondHigherEducationDiploma = secondHigherEducationDiploma;
    }

    public String getSecondHEDUniversity() {
        return secondHEDUniversity;
    }

    public void setSecondHEDUniversity(String secondHEDUniversity) {
        this.secondHEDUniversity = secondHEDUniversity;
    }

    public String getSecondHEDMention() {
        return secondHEDMention;
    }

    public void setSecondHEDMention(String secondHEDMention) {
        this.secondHEDMention = secondHEDMention;
    }

    public LocalDate getSecondHEDDate() {
        return secondHEDDate;
    }

    public void setSecondHEDDate(LocalDate secondHEDDate) {
        this.secondHEDDate = secondHEDDate;
    }

    public String getThirdHigherEducationDiploma() {
        return thirdHigherEducationDiploma;
    }

    public void setThirdHigherEducationDiploma(String thirdHigherEducationDiploma) {
        this.thirdHigherEducationDiploma = thirdHigherEducationDiploma;
    }

    public String getThirdHEDUniversity() {
        return thirdHEDUniversity;
    }

    public void setThirdHEDUniversity(String thirdHEDUniversity) {
        this.thirdHEDUniversity = thirdHEDUniversity;
    }

    public String getThirdHEDMention() {
        return thirdHEDMention;
    }

    public void setThirdHEDMention(String thirdHEDMention) {
        this.thirdHEDMention = thirdHEDMention;
    }

    public LocalDate getThirdHEDDate() {
        return thirdHEDDate;
    }

    public void setThirdHEDDate(LocalDate thirdHEDDate) {
        this.thirdHEDDate = thirdHEDDate;
    }

    private String firstHigherEducationDiploma;

    private String firstHEDUniversity;

    private String firstHEDMention;

    private LocalDate firstHEDDate;

    private String secondHigherEducationDiploma;

    private String secondHEDUniversity;

    private String secondHEDMention;

    private LocalDate secondHEDDate;

    private String thirdHigherEducationDiploma;

    private String thirdHEDUniversity;

    private String thirdHEDMention;

    private LocalDate thirdHEDDate;

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

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
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
