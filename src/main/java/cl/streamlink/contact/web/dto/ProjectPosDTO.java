package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.utils.enums.Currency;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

public class ProjectPosDTO extends PositioningDTO {

    private String place;
    private String comment;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private LocalDate presentationDate;


    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public LocalDate getPresentationDate() {
        return presentationDate;
    }

    public void setPresentationDate(LocalDate presentationDate) {
        this.presentationDate = presentationDate;
    }
}
