package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.domain.SocietyActivityArea;
import cl.streamlink.contact.domain.SocietyStage;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.time.LocalDateTime;

public class SocietyResponseDTO {

    private String reference;

    private String label;

    private Integer staffNumber;

    private String SupplierNumber;

    private SocietyStage stage;

    private SocietyActivityArea activityArea;

    @JsonUnwrapped
    private ContactDTO contact ;

    @JsonUnwrapped
    private SocietyLegalInformationDTO legalInformation ;

    private String note;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

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

    public Integer getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(Integer staffNumber) {
        this.staffNumber = staffNumber;
    }

    public String getSupplierNumber() {
        return SupplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        SupplierNumber = supplierNumber;
    }

    public SocietyStage getStage() {
        return stage;
    }

    public void setStage(SocietyStage stage) {
        this.stage = stage;
    }

    public SocietyActivityArea getActivityArea() {
        return activityArea;
    }

    public void setActivityArea(SocietyActivityArea activityArea) {
        this.activityArea = activityArea;
    }

    public ContactDTO getContact() {
        return contact;
    }

    public void setContact(ContactDTO contact) {
        this.contact = contact;
    }

    public SocietyLegalInformationDTO getLegalInformation() {
        return legalInformation;
    }

    public void setLegalInformation(SocietyLegalInformationDTO legalInformation) {
        this.legalInformation = legalInformation;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
}
