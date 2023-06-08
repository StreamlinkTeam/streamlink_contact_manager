package cl.streamlink.contact.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "emergencyContact")
public class EmergencyContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstContactFirstName;

    @NotNull
    private String firstContactLastName;

    @NotNull
    private String firstContactPhoneNumber;

    @NotNull
    private String firstContactRelativesLink;

    private String secondContactFirstName;

    private String secondContactLastName;

    private String secondContactPhoneNumber;

    private String secondContactRelativesLink;

    public EmergencyContact() {
    }

    public EmergencyContact(@NotNull String firstContactFirstName, @NotNull String firstContactLastName,
            @NotNull String firstContactPhoneNumber, @NotNull String firstContactRelativesLink,
            String secondContactFirstName, String secondContactLastName, String secondContactPhoneNumber,
            String secondContactRelativesLink) {
        this.firstContactFirstName = firstContactFirstName;
        this.firstContactLastName = firstContactLastName;
        this.firstContactPhoneNumber = firstContactPhoneNumber;
        this.firstContactRelativesLink = firstContactRelativesLink;
        this.secondContactFirstName = secondContactFirstName;
        this.secondContactLastName = secondContactLastName;
        this.secondContactPhoneNumber = secondContactPhoneNumber;
        this.secondContactRelativesLink = secondContactRelativesLink;
    }

    public Long getId() {
        return id;
    }

    public String getFirstContactFirstName() {
        return firstContactFirstName;
    }

    public String getFirstContactLastName() {
        return firstContactLastName;
    }

    public String getFirstContactPhoneNumber() {
        return firstContactPhoneNumber;
    }

    public String getFirstContactRelativesLink() {
        return firstContactRelativesLink;
    }

    public String getSecondContactFirstName() {
        return secondContactFirstName;
    }

    public String getSecondContactLastName() {
        return secondContactLastName;
    }

    public String getSecondContactPhoneNumber() {
        return secondContactPhoneNumber;
    }

    public String getSecondContactRelativesLink() {
        return secondContactRelativesLink;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstContactFirstName(String firstContactFirstName) {
        this.firstContactFirstName = firstContactFirstName;
    }

    public void setFirstContactLastName(String firstContactLastName) {
        this.firstContactLastName = firstContactLastName;
    }

    public void setFirstContactPhoneNumber(String firstContactPhoneNumber) {
        this.firstContactPhoneNumber = firstContactPhoneNumber;
    }

    public void setFirstContactRelativesLink(String firstContactRelativesLink) {
        this.firstContactRelativesLink = firstContactRelativesLink;
    }

    public void setSecondContactFirstName(String secondContactFirstName) {
        this.secondContactFirstName = secondContactFirstName;
    }

    public void setSecondContactLastName(String secondContactLastName) {
        this.secondContactLastName = secondContactLastName;
    }

    public void setSecondContactPhoneNumber(String secondContactPhoneNumber) {
        this.secondContactPhoneNumber = secondContactPhoneNumber;
    }

    public void setSecondContactRelativesLink(String secondContactRelativesLink) {
        this.secondContactRelativesLink = secondContactRelativesLink;
    }

}
