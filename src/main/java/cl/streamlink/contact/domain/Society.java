package cl.streamlink.contact.domain;

import cl.streamlink.contact.utils.MiscUtils;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(indexes = {@Index(name = "index_society_reference", columnList = "reference", unique = true)})
@EntityListeners(AuditingEntityListener.class)
public class Society {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String reference;

    @NotNull
    private String label;

    private Integer staffNumber;

    private String SupplierNumber;

    @Enumerated(EnumType.STRING)
    private SocietyStage stage;

    @Enumerated(EnumType.STRING)
    private SocietyActivityArea activityArea;

    @ManyToOne
    private User manager;

    @Embedded
    private Contact contact = new Contact();

    @Embedded
    private SocietyLegalInformation legalInformation = new SocietyLegalInformation();

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> services = new ArrayList<String>();

    @Lob
    private String note;

    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

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

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Contact getContact() {
        if (contact == null)
            contact = new Contact();
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public SocietyLegalInformation getLegalInformation() {
        if (legalInformation == null)
            legalInformation = new SocietyLegalInformation();
        return legalInformation;
    }

    public void setLegalInformation(SocietyLegalInformation legalInformation) {
        this.legalInformation = legalInformation;
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

    @Override
    public boolean equals(Object object) {
        return Optional.ofNullable(object).filter(obj -> obj instanceof Society).map(obj -> (Society) obj).
                filter(ag -> getId() == null || MiscUtils.equals(ag.getReference(), this.reference)).
                filter(ag -> getId() != null || MiscUtils.equals(ag, this)).
                isPresent();
    }

    @Override
    public int hashCode() {
        if (this.getReference() != null)
            return this.getReference().hashCode();
        else if (this.getId() != null)
            return this.getId().hashCode();
        else
            return super.hashCode();
    }
}
