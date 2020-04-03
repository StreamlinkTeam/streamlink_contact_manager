package cl.streamlink.contact.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(indexes = {@Index(name = "index_absenceManage_reference", columnList = "reference", unique = true)})
@EntityListeners(AuditingEntityListener.class)
public class AbsenceManage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reference;

    private float acquired;
    private float consumed;
    private float requested;
    private float actual_Balance;
    private float provisional_Balance;

    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @ManyToOne
    private Resource resource;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
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

    public AbsenceManage(String reference, float acquired, float consumed, float requested, float actual_Balance, float provisional_Balance, Resource resource) {
        this.reference = reference;
        this.acquired = acquired;
        this.consumed = consumed;
        this.requested = requested;
        this.actual_Balance = actual_Balance;
        this.provisional_Balance = provisional_Balance;
        this.resource = resource;
    }

    public AbsenceManage() {
    }
}
