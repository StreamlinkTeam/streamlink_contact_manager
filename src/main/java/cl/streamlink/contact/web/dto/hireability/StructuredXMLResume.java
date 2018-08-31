package cl.streamlink.contact.web.dto.hireability;



import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StructuredXMLResume {

    @JsonAlias(value = "ContactInfo")
    private ContactInfo contactInfo;
    @JsonAlias(value = "ExecutiveSummary")
    private String executiveSummary;
    @JsonAlias(value = "Objective")
    private String objective;
    @JsonAlias(value = "Languages")
    private List<HireabilityLanguages> languages;
    @JsonAlias(value = "Qualifications")
    private List<Competency> qualifications;

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getExecutiveSummary() {
        return executiveSummary;
    }

    public void setExecutiveSummary(String executiveSummary) {
        this.executiveSummary = executiveSummary;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public List<HireabilityLanguages> getLanguages() {
        return languages;
    }

    public void setLanguages(List<HireabilityLanguages> languages) {
        this.languages = languages;
    }

    public List<Competency> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<Competency> qualifications) {
        this.qualifications = qualifications;
    }
}
