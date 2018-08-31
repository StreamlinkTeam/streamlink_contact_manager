package cl.streamlink.contact.web.dto.hireability;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Phone {

    @JsonAlias(value = "InternationalCountryCode")
    private String internationalCountryCode;
    @JsonAlias(value = "AreaCityCode")
    private String areaCityCode;
    @JsonAlias(value = "SubscriberNumber")
    private String subscriberNumber;
    @JsonAlias(value = "Extension")
    private String extension;

    public String getInternationalCountryCode() {
        return internationalCountryCode;
    }

    public void setInternationalCountryCode(String internationalCountryCode) {
        this.internationalCountryCode = internationalCountryCode;
    }

    public String getAreaCityCode() {
        return areaCityCode;
    }

    public void setAreaCityCode(String areaCityCode) {
        this.areaCityCode = areaCityCode;
    }

    public String getSubscriberNumber() {
        return subscriberNumber;
    }

    public void setSubscriberNumber(String subscriberNumber) {
        this.subscriberNumber = subscriberNumber;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
