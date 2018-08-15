package cl.streamlink.contact.web.dto;

public class SocietyLegalInformationDTO {

    private String societyReference;

    private String TVA;

    private String Siret;

    private String LegalStatus;

    private String RCS;

    private String APECode;

    public String getSocietyReference() {
        return societyReference;
    }

    public void setSocietyReference(String societyReference) {
        this.societyReference = societyReference;
    }

    public String getTVA() {
        return TVA;
    }

    public void setTVA(String TVA) {
        this.TVA = TVA;
    }

    public String getSiret() {
        return Siret;
    }

    public void setSiret(String siret) {
        Siret = siret;
    }

    public String getLegalStatus() {
        return LegalStatus;
    }

    public void setLegalStatus(String legalStatus) {
        LegalStatus = legalStatus;
    }

    public String getRCS() {
        return RCS;
    }

    public void setRCS(String RCS) {
        this.RCS = RCS;
    }

    public String getAPECode() {
        return APECode;
    }

    public void setAPECode(String APECode) {
        this.APECode = APECode;
    }
}
