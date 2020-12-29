package se.swcg.consultauction.dto;

import se.swcg.consultauction.entity.Address;

import java.util.Date;

public class ClientForm {

    private String clientId;

    private String companyName;

    private String firstName;

    private String lastName;

    private String email;

    private boolean active;

    private Date dateForSignUp;

    private Date lastActive;

    private String phoneNumber;

    private String password;

    private String role;

    private String image;

    private Address address;

    /*private Projects projects;*/


    public ClientForm(String clientId, String companyName, String firstName, String lastName,
                      String email, boolean active, Date dateForSignUp, Date lastActive,
                      String phoneNumber, String password, String role, String image, Address address) {
        this.clientId = clientId;
        this.companyName = companyName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
        this.dateForSignUp = dateForSignUp;
        this.lastActive = lastActive;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
        this.image = image;
        this.address = address;
    }

    public ClientForm(String companyName, String firstName, String lastName,
                      String email, boolean active, Date dateForSignUp, Date lastActive,
                      String phoneNumber, String password, String role, String image, Address address) {
        this.companyName = companyName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
        this.dateForSignUp = dateForSignUp;
        this.lastActive = lastActive;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
        this.image = image;
        this.address = address;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public Date getDateForSignUp() {
        return dateForSignUp;
    }

    public void setDateForSignUp(Date dateForSignUp) {
        this.dateForSignUp = dateForSignUp;
    }

    public Date getLastActive() {
        return lastActive;
    }

    public void setLastActive(Date lastActive) {
        this.lastActive = lastActive;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
