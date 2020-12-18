package se.swcg.consultauction.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class AdminUser {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String adminId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private boolean active;
    private LocalDate lastActive;

    public AdminUser(String adminId, String firstName, String lastName, String email, String password, String role, boolean active, LocalDate lastActive) {
        this(firstName, lastName, email, password, role, active, lastActive);
        this.adminId = adminId;
    }

    public AdminUser(String firstName, String lastName, String email, String password, String role, boolean active, LocalDate lastActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
        this.lastActive = lastActive;
    }

    public AdminUser() {
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
        AdminUser adminUser = (AdminUser) o;
        return active == adminUser.active &&
                Objects.equals(adminId, adminUser.adminId) &&
                Objects.equals(firstName, adminUser.firstName) &&
                Objects.equals(lastName, adminUser.lastName) &&
                Objects.equals(email, adminUser.email) &&
                Objects.equals(password, adminUser.password) &&
                Objects.equals(role, adminUser.role) &&
                Objects.equals(lastActive, adminUser.lastActive);
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
