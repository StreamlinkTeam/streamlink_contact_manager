package cl.streamlink.contact.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "diplomes")
public class Diplomes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String baccalaureate;

    @NotNull
    private LocalDate baccalaureateDate;

    @NotNull
    private String highSchool;

    private String highSchoolMention;

    @NotNull
    private String firstHigherEducationDiploma;

    @NotNull
    private String firstHEDUniversity;

    private String firstHEDMention;

    private LocalDate firstHEDDate;

    @NotNull
    private String secondHigherEducationDiploma;

    @NotNull
    private String secondHEDUniversity;

    private String secondHEDMention;

    private LocalDate secondHEDDate;

    @NotNull
    private String thirdHigherEducationDiploma;

    @NotNull
    private String thirdHEDUniversity;

    private String thirdHEDMention;

    private LocalDate thirdHEDDate;

    public Diplomes() {
    }

    public Diplomes(String baccalaureate, @NotNull LocalDate baccalaureateDate, @NotNull String highSchool,
            String highSchoolMention, @NotNull String firstHigherEducationDiploma, @NotNull String firstHEDUniversity,
            String firstHEDMention, LocalDate firstHEDDate, @NotNull String secondHigherEducationDiploma,
            @NotNull String secondHEDUniversity, String secondHEDMention, LocalDate secondHEDDate,
            @NotNull String thirdHigherEducationDiploma, @NotNull String thirdHEDUniversity, String thirdHEDMention,
            LocalDate thirdHEDDate) {
        this.baccalaureate = baccalaureate;
        this.baccalaureateDate = baccalaureateDate;
        this.highSchool = highSchool;
        this.highSchoolMention = highSchoolMention;
        this.firstHigherEducationDiploma = firstHigherEducationDiploma;
        this.firstHEDUniversity = firstHEDUniversity;
        this.firstHEDMention = firstHEDMention;
        this.firstHEDDate = firstHEDDate;
        this.secondHigherEducationDiploma = secondHigherEducationDiploma;
        this.secondHEDUniversity = secondHEDUniversity;
        this.secondHEDMention = secondHEDMention;
        this.secondHEDDate = secondHEDDate;
        this.thirdHigherEducationDiploma = thirdHigherEducationDiploma;
        this.thirdHEDUniversity = thirdHEDUniversity;
        this.thirdHEDMention = thirdHEDMention;
        this.thirdHEDDate = thirdHEDDate;
    }

    public Long getId() {
        return id;
    }

    public String getBaccalaureate() {
        return baccalaureate;
    }

    public LocalDate getBaccalaureateDate() {
        return baccalaureateDate;
    }

    public String getHighSchool() {
        return highSchool;
    }

    public String getHighSchoolMention() {
        return highSchoolMention;
    }

    public String getFirstHigherEducationDiploma() {
        return firstHigherEducationDiploma;
    }

    public String getFirstHEDUniversity() {
        return firstHEDUniversity;
    }

    public String getFirstHEDMention() {
        return firstHEDMention;
    }

    public LocalDate getFirstHEDDate() {
        return firstHEDDate;
    }

    public String getSecondHigherEducationDiploma() {
        return secondHigherEducationDiploma;
    }

    public String getSecondHEDUniversity() {
        return secondHEDUniversity;
    }

    public String getSecondHEDMention() {
        return secondHEDMention;
    }

    public LocalDate getSecondHEDDate() {
        return secondHEDDate;
    }

    public String getThirdHigherEducationDiploma() {
        return thirdHigherEducationDiploma;
    }

    public String getThirdHEDUniversity() {
        return thirdHEDUniversity;
    }

    public String getThirdHEDMention() {
        return thirdHEDMention;
    }

    public LocalDate getThirdHEDDate() {
        return thirdHEDDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBaccalaureate(String baccalaureate) {
        this.baccalaureate = baccalaureate;
    }

    public void setBaccalaureateDate(LocalDate baccalaureateDate) {
        this.baccalaureateDate = baccalaureateDate;
    }

    public void setHighSchool(String highSchool) {
        this.highSchool = highSchool;
    }

    public void setHighSchoolMention(String highSchoolMention) {
        this.highSchoolMention = highSchoolMention;
    }

    public void setFirstHigherEducationDiploma(String firstHigherEducationDiploma) {
        this.firstHigherEducationDiploma = firstHigherEducationDiploma;
    }

    public void setFirstHEDUniversity(String firstHEDUniversity) {
        this.firstHEDUniversity = firstHEDUniversity;
    }

    public void setFirstHEDMention(String firstHEDMention) {
        this.firstHEDMention = firstHEDMention;
    }

    public void setFirstHEDDate(LocalDate firstHEDDate) {
        this.firstHEDDate = firstHEDDate;
    }

    public void setSecondHigherEducationDiploma(String secondHigherEducationDiploma) {
        this.secondHigherEducationDiploma = secondHigherEducationDiploma;
    }

    public void setSecondHEDUniversity(String secondHEDUniversity) {
        this.secondHEDUniversity = secondHEDUniversity;
    }

    public void setSecondHEDMention(String secondHEDMention) {
        this.secondHEDMention = secondHEDMention;
    }

    public void setSecondHEDDate(LocalDate secondHEDDate) {
        this.secondHEDDate = secondHEDDate;
    }

    public void setThirdHigherEducationDiploma(String thirdHigherEducationDiploma) {
        this.thirdHigherEducationDiploma = thirdHigherEducationDiploma;
    }

    public void setThirdHEDUniversity(String thirdHEDUniversity) {
        this.thirdHEDUniversity = thirdHEDUniversity;
    }

    public void setThirdHEDMention(String thirdHEDMention) {
        this.thirdHEDMention = thirdHEDMention;
    }

    public void setThirdHEDDate(LocalDate thirdHEDDate) {
        this.thirdHEDDate = thirdHEDDate;
    }

}
