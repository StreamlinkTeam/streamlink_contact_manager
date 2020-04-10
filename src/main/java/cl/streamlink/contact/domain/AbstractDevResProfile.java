package cl.streamlink.contact.domain;

import cl.streamlink.contact.utils.MiscUtils;

import javax.persistence.Embedded;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@MappedSuperclass
public class AbstractDevResProfile extends AbstractProfile {

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @Embedded
    private PersonalInformation personalInformation = new PersonalInformation();

    @Embedded
    private SkillsInformation skillsInformation = new SkillsInformation();

    @ManyToOne
    private User rh;

    private LocalDate availability;

    private String mobility;

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

    public User getRh() {
        return rh;
    }

    public void setRh(User rh) {
        this.rh = rh;
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
}
