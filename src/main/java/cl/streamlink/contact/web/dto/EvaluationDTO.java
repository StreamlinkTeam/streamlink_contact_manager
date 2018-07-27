package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.domain.EvaluationNote;

import java.time.LocalDateTime;

/**
 * Created by chemakh on 13/07/2018.
 */
public class EvaluationDTO {

    private String reference;

    private String developerReference;

    private String responsableName;

    private String responsableReference;

    private EvaluationNote relational;

    private EvaluationNote technical;

    private String note;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDeveloperReference() {
        return developerReference;
    }

    public void setDeveloperReference(String developerReference) {
        this.developerReference = developerReference;
    }

    public String getResponsableReference() {
        return responsableReference;
    }

    public void setResponsableReference(String responsableReference) {
        this.responsableReference = responsableReference;
    }

    public EvaluationNote getRelational() {
        return relational;
    }

    public void setRelational(EvaluationNote relational) {
        this.relational = relational;
    }

    public EvaluationNote getTechnical() {
        return technical;
    }

    public void setTechnical(EvaluationNote technical) {
        this.technical = technical;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getResponsableName() {
        return responsableName;
    }

    public void setResponsableName(String responsableName) {
        this.responsableName = responsableName;
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
