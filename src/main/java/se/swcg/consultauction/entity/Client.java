package se.swcg.consultauction.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Objects;

@Entity
public class Client {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String clientId;

    @NotBlank
    private String companyName;


    @NotBlank
    private String firstName;


    @NotBlank
    private String lastName;


    @Column(unique = true)
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message="{invalid.email}")
    private String email;


    private boolean active;

    private Date dateForSignUp;

    private Date lastActive;

    @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
            message="{invalid.phonenumber}")
    private String phoneNumber;

    @Pattern(regexp =  "^\\(?=.*[a-z]?=.*[A-Z](?=.*\\d)[a-zA-Z\\d]{8,}$")
    private String password;

    private String role;

    private String image;
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Address address;

    /*private Projects projects;*/



    public Client(String clientId, String companyName, String firstName, String lastName,
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

    public Client(String companyName, String firstName, String lastName,
                  String email, String phoneNumber, String password, Address address) {
        this.companyName = companyName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
    }

    public Client() {
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getClientId() {
        return clientId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return active == client.active && Objects.equals(clientId, client.clientId) &&
                Objects.equals(companyName, client.companyName) &&
                Objects.equals(firstName, client.firstName) &&
                Objects.equals(lastName, client.lastName) &&
                Objects.equals(email, client.email) &&
                Objects.equals(dateForSignUp, client.dateForSignUp) &&
                Objects.equals(lastActive, client.lastActive) && Objects.equals(phoneNumber, client.phoneNumber) &&
                Objects.equals(password, client.password) && Objects.equals(role, client.role) &&
                Objects.equals(image, client.image) && Objects.equals(address, client.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, companyName, firstName, lastName, email, active, dateForSignUp,
                lastActive, phoneNumber, password, role, image, address);
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId='" + clientId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", dateForSignUp=" + dateForSignUp +
                ", lastActive=" + lastActive +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", image='" + image + '\'' +
                ", address=" + address +
                '}';
    }
}
