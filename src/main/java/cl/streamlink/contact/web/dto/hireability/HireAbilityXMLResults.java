package cl.streamlink.contact.web.dto.hireability;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HireAbilityXMLResults {

    @JsonAlias(value = "Resume")
    private Resume resume;

    @JsonAlias(value = "ProcessingErrors")
    private ProcessingErrors processingErrors;

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public ProcessingErrors getProcessingErrors() {
        return processingErrors;
    }

    public void setProcessingErrors(ProcessingErrors processingErrors) {
        this.processingErrors = processingErrors;
    }
}
