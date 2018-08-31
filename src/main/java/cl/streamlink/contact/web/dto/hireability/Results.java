package cl.streamlink.contact.web.dto.hireability;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Results {

    @JsonAlias(value = "HireAbilityXMLResults")
    private HireAbilityXMLResults hireAbilityXMLResults;

    public HireAbilityXMLResults getHireAbilityXMLResults() {
        return hireAbilityXMLResults;
    }

    public void setHireAbilityXMLResults(HireAbilityXMLResults hireAbilityXMLResults) {
        this.hireAbilityXMLResults = hireAbilityXMLResults;
    }
}
