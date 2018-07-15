package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.domain.Stage;
import cl.streamlink.contact.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Radouen on 12/07/2018.
 */
public class DeveloperResponseDTO {

    private String reference;

    private String firstname;

    private String lastname;

    private Stage stage;

    private User manager;

    private User rh;

    private String note;

    private LocalDate availability;

    private String mobility;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private PersonalInformationDTO personalInformation;

    private SkillsInformationDTO skillsInformation;

    private ContactDTO contact;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public User getRh() {
        return rh;
    }

    public void setRh(User rh) {
        this.rh = rh;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getAvailability() {
        return availability;
    }

    public void setAvailability(LocalDate availability) {
        this.availability = availability;
    }

    public String getMobility() {
        return mobility;
    }

    public void setMobility(String mobility) {
        this.mobility = mobility;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public PersonalInformationDTO getPersonalInformation() {
        return personalInformation;
    }

    public void setPersonalInformation(PersonalInformationDTO personalInformation) {
        this.personalInformation = personalInformation;
    }

    public SkillsInformationDTO getSkillsInformation() {
        return skillsInformation;
    }

    public void setSkillsInformation(SkillsInformationDTO skillsInformation) {
        this.skillsInformation = skillsInformation;
    }

    public ContactDTO getContact() {
        return contact;
    }

    public void setContact(ContactDTO contact) {
        this.contact = contact;
    }


}
