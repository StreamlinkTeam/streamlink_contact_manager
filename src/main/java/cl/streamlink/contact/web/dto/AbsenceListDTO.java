package cl.streamlink.contact.web.dto;

import java.util.Date;

public class AbsenceListDTO {

    private String reference;

    private String comment;
    private String subject;
    private String state;

    private String developerReference;

    private Date absenceListDate ;
    private String managerReference;
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getResourceReference() {
        return developerReference;
    }

    public void setResourceReference(String developerReference) {
        this.developerReference = developerReference;
    }

    public Date getAbsenceListDate() {
        return absenceListDate;
    }

    public void setAbsenceListDate(Date absenceListDate) {
        this.absenceListDate = absenceListDate;
    }

    public String getManagerReference() {
        return managerReference;
    }

    public void setManagerReference(String managerReference) {
        this.managerReference = managerReference;
    }
}
