package cl.streamlink.contact.web.dto;

import java.util.Date;

public class AbsenceManageDTO {

    private String reference;
    private float acquired;
    private float consumed;
    private float requested;
    private float actual_Balance;
    private float provisional_Balance;
    private Date createdDate;
    private Date modifiedDate;
    private String developerReference;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public float getAcquired() {
        return acquired;
    }

    public void setAcquired(float acquired) {
        this.acquired = acquired;
    }

    public float getConsumed() {
        return consumed;
    }

    public void setConsumed(float consumed) {
        this.consumed = consumed;
    }

    public float getRequested() {
        return requested;
    }

    public void setRequested(float requested) {
        this.requested = requested;
    }

    public float getActual_Balance() {
        return actual_Balance;
    }

    public void setActual_Balance(float actual_Balance) {
        this.actual_Balance = actual_Balance;
    }

    public float getProvisional_Balance() {
        return provisional_Balance;
    }

    public void setProvisional_Balance(float provisional_Balance) {
        this.provisional_Balance = provisional_Balance;
    }

    public String getResourceReference() {
        return developerReference;
    }

    public void setResourceReference(String developerReference) {
        this.developerReference = developerReference;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
