package cl.streamlink.contact.web.dto.hireability;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessingErrors {

    @JsonAlias(value = "Error")
    private HireabilityError error;

    public HireabilityError getError() {
        return error;
    }

    public void setError(HireabilityError error) {
        this.error = error;
    }
}
