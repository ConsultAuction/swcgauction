package se.swcg.consultauction.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotBlank
    @Size(max = 50)
    private String firstName;
    @NotBlank
    @Size(max = 50)
    private String lastName;
    @NotBlank
    @Column(unique = true)
    @Size(max = 100)
    private String email;
    private boolean active;
    private LocalDate dateOfSignUp;
    private LocalDate lastActive;
    private boolean available;
    @Size(max = 50)
    private String password;
    @Size(max = 10)
    private String role;
    @Size(max = 10)
    private String phoneNumber;
    @Size(max = 200)
    private String image;
    @NotNull
    private int minPrice;
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Address address;
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Qualifications qualifications;

    public User() {}

    public User(String firstName, String lastName, String email, boolean active, LocalDate dateOfSignUp, LocalDate lastActive, boolean available, String password, String role, String phoneNumber, String image, int minPrice, Address address, Qualifications qualifications) {
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

    public User(String userId, String firstName, String lastName, String email, boolean active, LocalDate dateOfSignUp, LocalDate lastActive, boolean available, String password, String role, String phoneNumber, String image, int minPrice, Address address, Qualifications qualifications) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return active == user.active &&
                available == user.available &&
                minPrice == user.minPrice &&
                Objects.equals(userId, user.userId) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(dateOfSignUp, user.dateOfSignUp) &&
                Objects.equals(lastActive, user.lastActive) &&
                Objects.equals(password, user.password) &&
                Objects.equals(role, user.role) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(image, user.image) &&
                Objects.equals(address, user.address) &&
                Objects.equals(qualifications, user.qualifications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, email, active, dateOfSignUp, lastActive, available, password, role, phoneNumber, image, minPrice, address, qualifications);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", dateOfSignUp=" + dateOfSignUp +
                ", lastActive=" + lastActive +
                ", available=" + available +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", image='" + image + '\'' +
                ", minPrice=" + minPrice +
                ", address=" + address +
                ", qualifications=" + qualifications +
                '}';
    }
}