package cl.streamlink.contact.web.dto;

import javax.validation.constraints.NotEmpty;

public class LigneTempsDTO {

    private String reference;
    @NotEmpty
    private String listeTempsReference;
    @NotEmpty
    private String missionProjectReference;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getListeTempsReference() {
        return listeTempsReference;
    }

    public void setListeTempsReference(String listeTempsReference) {
        this.listeTempsReference = listeTempsReference;
    }

    public String getMissionProjectReference() {
        return missionProjectReference;
    }

    public void setMissionProjectReference(String missionProjectReference) {
        this.missionProjectReference = missionProjectReference;
    }

    @Override
    public String toString() {
        return "LigneTempsDTO [reference=" + reference + ", listeTempsReference=" + listeTempsReference
                + ", missionProjectReference=" + missionProjectReference + "]";
    }

}
