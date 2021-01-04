package se.swcg.consultauction.dto;

import java.time.LocalDate;

public class AdminForm {

    private String adminId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private boolean active;
    private LocalDate lastActive;

    public AdminForm(String adminId, String firstName, String lastName, String email, String password, String role, boolean active, LocalDate lastActive) {
        this(firstName, lastName, email, password, role, active, lastActive);
        this.adminId = adminId;
    }

    public AdminForm(String firstName, String lastName, String email, String password, String role, boolean active, LocalDate lastActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
        this.lastActive = lastActive;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getLastActive() {
        return lastActive;
    }

    public void setLastActive(LocalDate lastActive) {
        this.lastActive = lastActive;
    }
}