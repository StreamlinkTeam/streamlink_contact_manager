package cl.streamlink.contact.web.dto;

import javax.validation.constraints.NotEmpty;

public class TimeLineDTO {

    private String reference;
    @NotEmpty
    private String timeListReference;
    @NotEmpty
    private String projectReference;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getTimeListReference() {
        return timeListReference;
    }

    public void setTimeListReference(String timeListReference) {
        this.timeListReference = timeListReference;
    }

    public String getProjectReference() {
        return projectReference;
    }

    public void setProjectReference(String projectReference) {
        this.projectReference = projectReference;
    }

    @Override
    public String toString() {
        return "TimeLineDTO [reference=" + reference + ", timeListReference=" + timeListReference
                + ", projectReference=" + projectReference + "]";
    }

}
