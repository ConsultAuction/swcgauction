package se.swcg.consultauction.dto;

import se.swcg.consultauction.entity.Address;
import se.swcg.consultauction.entity.Qualifications;

import java.time.LocalDate;

public class UserForm {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private boolean active;
    private LocalDate dateOfSignUp;
    private LocalDate lastActive;
    private boolean available;
    private String password;
    private String role;
    private String phoneNumber;
    private String image;
    private int minPrice;
    private Address address;
    private Qualifications qualifications;

    public UserForm(String userId, String firstName, String lastName, String email, boolean active, LocalDate dateOfSignUp,
                    LocalDate lastActive, boolean available, String password, String role, String phoneNumber,
                    String image, int minPrice, Address address, Qualifications qualifications) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
        this.dateOfSignUp = dateOfSignUp;
        this.lastActive = lastActive;
        this.available = available;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.image = image;
        this.minPrice = minPrice;
        this.address = address;
        this.qualifications = qualifications;
    }

    public UserForm(String firstName, String lastName, String email, boolean active, LocalDate dateOfSignUp,
                    LocalDate lastActive, boolean available, String password, String role, String phoneNumber,
                    String image, int minPrice, Address address, Qualifications qualifications) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
        this.dateOfSignUp = dateOfSignUp;
        this.lastActive = lastActive;
        this.available = available;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.image = image;
        this.minPrice = minPrice;
        this.address = address;
        this.qualifications = qualifications;
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getDateOfSignUp() {
        return dateOfSignUp;
    }

    public void setDateOfSignUp(LocalDate dateOfSignUp) {
        this.dateOfSignUp = dateOfSignUp;
    }

    public LocalDate getLastActive() {
        return lastActive;
    }

    public void setLastActive(LocalDate lastActive) {
        this.lastActive = lastActive;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Qualifications getQualifications() {
        return qualifications;
    }

    public void setQualifications(Qualifications qualifications) {
        this.qualifications = qualifications;
    }
}
