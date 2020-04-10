package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.domain.AbstractDevResProfile;
import cl.streamlink.contact.utils.MiscUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDevResProfileDTO<T extends AbstractDevResProfile> extends AbstractProfileDTO<T> {

    // @NotNull
    // @Size(min = 2, max = 255)
    private String firstname;

    // @NotNull
    // @Size(min = 2, max = 255)
    private String lastname;

    private String rhReference;


    private LocalDate availability;

    private List<String> mobility;

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

    public String getRhReference() {
        return rhReference;
    }

    public void setRhReference(String rhReference) {
        this.rhReference = rhReference;
    }

    public LocalDate getAvailability() {
        return availability;
    }

    public void setAvailability(LocalDate availability) {
        this.availability = availability;
    }

    public List<String> getMobility() {
        if (MiscUtils.isEmpty(mobility))
            return new ArrayList<>();
        return mobility;
    }

    public void setMobility(List<String> mobility) {
        this.mobility = mobility;
    }

    public abstract boolean isResource();
}
