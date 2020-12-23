package se.swcg.consultauction.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Admin {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String adminId;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private String role;
    private boolean active;
    private LocalDate lastActive;

    public Admin(String adminId, String firstName, String lastName, String email, String password, String role, boolean active, LocalDate lastActive) {
        this(firstName, lastName, email, password, role, active, lastActive);
        this.adminId = adminId;
    }

    public Admin(String firstName, String lastName, String email, String password, String role, boolean active, LocalDate lastActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
        this.lastActive = lastActive;
    }

    public Admin() {
    }

    public String getAdminId() {
        return adminId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDate getLastActive() {
        return lastActive;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setLastActive(LocalDate lastActive) {
        this.lastActive = lastActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return active == admin.active &&
                Objects.equals(adminId, admin.adminId) &&
                Objects.equals(firstName, admin.firstName) &&
                Objects.equals(lastName, admin.lastName) &&
                Objects.equals(email, admin.email) &&
                Objects.equals(password, admin.password) &&
                Objects.equals(role, admin.role) &&
                Objects.equals(lastActive, admin.lastActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminId, firstName, lastName, email, password, role, active, lastActive);
    }

    @Override
    public String toString() {
        return "AdminUser{" +
                "id='" + adminId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", active=" + active +
                ", lastActive=" + lastActive +
                '}';
    }
}
