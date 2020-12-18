package se.swcg.consultauction.dto;

import javax.validation.constraints.NotBlank;

public class AddressDto {

    private String addressId;
    @NotBlank
    private String address;
    @NotBlank
    private String country;
    @NotBlank
    private String city;
    @NotBlank
    // Swedish postal regex: ^(s-|S-){0,1}[0-9]{3}\s?[0-9]{2}$
    private String zipCode;

    public AddressDto(String addressId, String address, String country, String city, String zipCode) {
        this.addressId = addressId;
        this.address = address;
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getAddressId() {
        return addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
