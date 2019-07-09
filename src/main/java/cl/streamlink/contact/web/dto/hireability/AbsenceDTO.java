package cl.streamlink.contact.web.dto.hireability;


import java.util.Date;


public class AbsenceDTO {


    private String Reference;

    private String description;
    private String type;


    private Date dateAbsence;

    private float duration ;

    private float sum ;
    private String absenceListReference;

    public String getReference() {
        return Reference;
    }

    public void setReference(String reference) {
        Reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateAbsence() {
        return dateAbsence;
    }

    public void setDateAbsence(Date dateAbsence) {
        this.dateAbsence = dateAbsence;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public String getAbsenceListReference() {
        return absenceListReference;
    }

    public void setAbsenceListReference(String absenceListReference) {
        this.absenceListReference = absenceListReference;
    }
}
