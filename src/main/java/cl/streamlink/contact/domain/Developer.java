package cl.streamlink.contact.domain;


import cl.streamlink.contact.utils.MiscUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;


@Entity
@Table(indexes = {@Index(name = "index_developer_reference", columnList = "reference", unique = true)})
@EntityListeners(AuditingEntityListener.class)
public class Developer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String reference;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @Enumerated(EnumType.STRING)
    private Stage stage = Stage.ToTreat;

    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.UNDEFINED;

    @Embedded
    private Contact contact = new Contact();

    @Embedded
    private PersonalInformation personalInformation = new PersonalInformation();

    @Embedded
    private SkillsInformation skillsInformation = new SkillsInformation();

    @ManyToOne
    private User manager;

    @ManyToOne
    private User rh;

    @Lob
    private String note;

    private LocalDate availability;

    private String mobility;


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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Contact getContact() {
        if (contact == null)
            contact = new Contact();
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public PersonalInformation getPersonalInformation() {

        if (personalInformation == null)
            personalInformation = new PersonalInformation();
        return personalInformation;
    }

    public void setPersonalInformation(PersonalInformation personalInformation) {
        this.personalInformation = personalInformation;
    }

    public SkillsInformation getSkillsInformation() {
        if (skillsInformation == null)
            skillsInformation = new SkillsInformation();
        return skillsInformation;
    }

    public void setSkillsInformation(SkillsInformation skillsInformation) {
        this.skillsInformation = skillsInformation;
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

        if (MiscUtils.isEmpty(mobility))
            return "";
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

    @Override
    public boolean equals(Object object) {
        return Optional.ofNullable(object).filter(obj -> obj instanceof Developer).map(obj -> (Developer) obj).
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
