package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.utils.enums.TimeLineType;

import javax.validation.constraints.NotEmpty;

public class TimeLineDTO {

    private String reference;
    @NotEmpty
    private String timeListReference;
    @NotEmpty
    private String positioningReference;

    private TimeLineType Type;

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

    public String getPositioningReference() {
        return positioningReference;
    }

    public void setPositioningReference(String positioningReference) {
        this.positioningReference = positioningReference;
    }

    public TimeLineType getType() {
        return Type;
    }

    public void setType(TimeLineType type) {
        Type = type;
    }

    @Override
    public String toString() {
        return "TimeLineDTO{" +
                "reference='" + reference + '\'' +
                ", timeListReference='" + timeListReference + '\'' +
                ", positioningReference='" + positioningReference + '\'' +
                ", Type='" + Type + '\'' +
                '}';
    }
}
