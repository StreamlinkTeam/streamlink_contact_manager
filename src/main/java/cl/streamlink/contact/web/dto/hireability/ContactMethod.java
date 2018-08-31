package cl.streamlink.contact.web.dto.hireability;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactMethod {

    @JsonAlias(value = "InternetEmailAddress")
    private String internetEmailAddress;

    @JsonAlias(value = "Mobile")
    private Phone mobile;

    @JsonAlias(value = "Telephone")
    private Phone telephone;

    public String getInternetEmailAddress() {
        return internetEmailAddress;
    }

    public void setInternetEmailAddress(String internetEmailAddress) {
        this.internetEmailAddress = internetEmailAddress;
    }

    public Phone getMobile() {
        return mobile;
    }

    public void setMobile(Phone mobile) {
        this.mobile = mobile;
    }

    public Phone getTelephone() {
        return telephone;
    }

    public void setTelephone(Phone telephone) {
        this.telephone = telephone;
    }
}
