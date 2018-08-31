package cl.streamlink.contact.web.dto.hireability;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Resume {

    @JsonAlias(value = "StructuredXMLResume")
    private StructuredXMLResume structuredXMLResume;

    public StructuredXMLResume getStructuredXMLResume() {
        return structuredXMLResume;
    }

    public void setStructuredXMLResume(StructuredXMLResume structuredXMLResume) {
        this.structuredXMLResume = structuredXMLResume;
    }
}
