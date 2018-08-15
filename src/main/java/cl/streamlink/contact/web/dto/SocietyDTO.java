package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.domain.SocietyActivityArea;
import cl.streamlink.contact.domain.SocietyStage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SocietyDTO {

    private String reference;

    private String label;

    private Integer staffNumber;

    private String SupplierNumber;

    private SocietyStage stage;

    private SocietyActivityArea activityArea;

    private String managerReference;

    private List<String> services = new ArrayList<String>();

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

    public String getManagerReference() {
        return managerReference;
    }

    public void setManagerReference(String managerReference) {
        this.managerReference = managerReference;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
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
