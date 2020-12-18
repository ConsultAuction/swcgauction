package se.swcg.consultauction.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Address {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String addressId;
    private String address;
    private String country;
    private String city;
    private String zipCode;

    public Address(String addressId, String address, String country, String city, String zipCode) {
        this(address, country, city, zipCode);
        this.addressId = addressId;
    }

    public Address(String address, String country, String city, String zipCode) {
        this.address = address;
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
    }

    public Address() {
    }

    public String getAddressId() {
        return addressId;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(addressId, address1.addressId) &&
                Objects.equals(address, address1.address) &&
                Objects.equals(country, address1.country) &&
                Objects.equals(city, address1.city) &&
                Objects.equals(zipCode, address1.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, address, country, city, zipCode);
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId='" + addressId + '\'' +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
