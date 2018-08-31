package cl.streamlink.contact.web.dto.hireability;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactInfo {

    @JsonAlias(value = "PersonName")
    private PersonName personName;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "ContactMethod")
    private ContactMethod[] contactMethods;

    public PersonName getPersonName() {
        return personName;
    }

    public void setPersonName(PersonName personName) {
        this.personName = personName;
    }

    public ContactMethod[] getContactMethods() {
        return contactMethods;
    }

    public void setContactMethods(ContactMethod[] contactMethods) {
        this.contactMethods = contactMethods;
    }
}
