package cl.streamlink.contact.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    @NotNull
    private String street;

    @NotNull
    private String city;

    @NotNull
    private Integer zipCode;

    @NotNull
    private String country;

    private String phoneNumber;

    @NotNull
    private String mobilePhoneNumber;

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public Address() {
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public Address(String address, @NotNull String street, @NotNull String city, @NotNull Integer zipCode,
            @NotNull String country, String phoneNumber, @NotNull String mobilePhoneNumber) {
        this.address = address;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

}
