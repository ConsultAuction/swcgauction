package se.swcg.consultauction.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String userId;
    private String companyName;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private String role;
    private LocalDate dateOfSignUp;
    private LocalDate lastActive;
    private boolean active;
    private String image;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Contact contact;

    public User(String userId, String companyName, String firstName, String lastName, String email, String password, String role, LocalDate dateOfSignUp, LocalDate lastActive, boolean active, String image, Contact contact) {
        this.userId = userId;
        this.companyName = companyName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.dateOfSignUp = dateOfSignUp;
        this.lastActive = lastActive;
        this.active = active;
        this.image = image;
        this.contact = contact;
    }

    public User(String companyName, String firstName, String lastName, String email, String password, String role, LocalDate dateOfSignUp, LocalDate lastActive, boolean active, String image, Contact contact) {
        this.companyName = companyName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.dateOfSignUp = dateOfSignUp;
        this.lastActive = lastActive;
        this.active = active;
        this.image = image;
        this.contact = contact;
    }

    public User() {
    }

    public String getUserId() {
        return userId;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return active == user.active &&
                Objects.equals(userId, user.userId) &&
                Objects.equals(companyName, user.companyName) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(role, user.role) &&
                Objects.equals(dateOfSignUp, user.dateOfSignUp) &&
                Objects.equals(lastActive, user.lastActive) &&
                Objects.equals(image, user.image) &&
                Objects.equals(contact, user.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, companyName, firstName, lastName, email, password, role, dateOfSignUp, lastActive, active, image, contact);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", dateOfSignUp=" + dateOfSignUp +
                ", lastActive=" + lastActive +
                ", active=" + active +
                ", image='" + image + '\'' +
                ", contact=" + contact +
                '}';
    }
}