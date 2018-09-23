package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.utils.enums.FamilySituation;

import java.time.LocalDate;

/**
 * Created by Chemakh on 11/07/2018.
 */
public class PersonalInformationDTO {

    private String developerReference;

    private LocalDate birthDate;

    private String placeOfBirth;

    private String nationality;

    private String socialSecurityNumber;

    private FamilySituation familySituation;

    public String getDeveloperReference() {
        return developerReference;
    }

    public void setDeveloperReference(String developerReference) {
        this.developerReference = developerReference;
    }

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
}
