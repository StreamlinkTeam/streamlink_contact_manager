package cl.streamlink.contact.domain;

import cl.streamlink.contact.utils.enums.FamilySituation;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDate;

@Embeddable
public class PersonalInformation {

    private LocalDate birthDate;

    private String placeOfBirth;

    private String nationality;

    private String secondNationality;

    private String maidenName;

    private Integer cinNumber;

    private LocalDate cinEmissionDate;

    public void setSecondNationality(String secondNationality) {
        this.secondNationality = secondNationality;
    }

    public void setMaidenName(String maidenName) {
        this.maidenName = maidenName;
    }

    public void setCinNumber(Integer cinNumber) {
        this.cinNumber = cinNumber;
    }

    public void setCinEmissionDate(LocalDate cinEmissionDate) {
        this.cinEmissionDate = cinEmissionDate;
    }

    public void setCinEmissionPlace(String cinEmissionPlace) {
        this.cinEmissionPlace = cinEmissionPlace;
    }

    public void setPasseportNumber(String passeportNumber) {
        this.passeportNumber = passeportNumber;
    }

    public void setPasseportEmissionDate(LocalDate passeportEmissionDate) {
        this.passeportEmissionDate = passeportEmissionDate;
    }

    public void setPasseportEmissionPlace(String passeportEmissionPlace) {
        this.passeportEmissionPlace = passeportEmissionPlace;
    }

    public void setResidencePermit(String residencePermit) {
        this.residencePermit = residencePermit;
    }

    public void setResidencePermitEmissionDate(LocalDate residencePermitEmissionDate) {
        this.residencePermitEmissionDate = residencePermitEmissionDate;
    }

    public void setCnssNumber(String cnssNumber) {
        this.cnssNumber = cnssNumber;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public void setRhesusFactor(String rhesusFactor) {
        this.rhesusFactor = rhesusFactor;
    }

    public String getSecondNationality() {
        return secondNationality;
    }

    public String getMaidenName() {
        return maidenName;
    }

    public Integer getCinNumber() {
        return cinNumber;
    }

    public LocalDate getCinEmissionDate() {
        return cinEmissionDate;
    }

    public String getCinEmissionPlace() {
        return cinEmissionPlace;
    }

    public String getPasseportNumber() {
        return passeportNumber;
    }

    public LocalDate getPasseportEmissionDate() {
        return passeportEmissionDate;
    }

    public String getPasseportEmissionPlace() {
        return passeportEmissionPlace;
    }

    public String getResidencePermit() {
        return residencePermit;
    }

    public LocalDate getResidencePermitEmissionDate() {
        return residencePermitEmissionDate;
    }

    public String getCnssNumber() {
        return cnssNumber;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getRhesusFactor() {
        return rhesusFactor;
    }

    private String cinEmissionPlace;

    private String passeportNumber;

    private LocalDate passeportEmissionDate;

    private String passeportEmissionPlace;

    private String residencePermit;

    private LocalDate residencePermitEmissionDate;

    private String cnssNumber;

    private String bloodGroup;

    private String rhesusFactor;

    private String socialSecurityNumber;

    private BigDecimal tjm;

    private String role;

    @Enumerated(EnumType.STRING)
    private FamilySituation familySituation;

    private String originCv;

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public FamilySituation getFamilySituation() {
        return familySituation;
    }

    public void setFamilySituation(FamilySituation familySituation) {
        this.familySituation = familySituation;
    }

    public BigDecimal getTjm() {
        return tjm;
    }

    public void setTjm(BigDecimal tjm) {
        this.tjm = tjm;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getOriginCv() {
        return originCv;
    }

    public void setOriginCv(String originCv) {
        this.originCv = originCv;
    }
}
