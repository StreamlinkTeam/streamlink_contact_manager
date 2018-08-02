package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.domain.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class DeveloperDTO {

    private String reference;

    @NotNull
    @Size(min = 2, max = 255)
    private String firstname;

    @NotNull
    @Size(min = 2, max = 255)
    private String lastname;

    private Stage stage;

    private Gender gender;

    private String managerReference;

    private String rhReference;

    @Size(min = 2, max = 255)
    private String note;

    private LocalDate availability;

    private List<String> mobility;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

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

    public String getManagerReference() {
        return managerReference;
    }

    public void setManagerReference(String managerReference) {
        this.managerReference = managerReference;
    }

    public String getRhReference() {
        return rhReference;
    }

    public void setRhReference(String rhReference) {
        this.rhReference = rhReference;
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

    public List<String> getMobility() {
        return mobility;
    }

    public void setMobility(List<String> mobility) {
        this.mobility = mobility;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
