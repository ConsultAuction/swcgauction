package se.swcg.consultauction.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class AdminDto {

    private String adminId;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String email;
    @NotBlank
    @Pattern(regexp = "^\\(?=.*[a-z]?=.*[A-Z](?=.*\\d)[a-zA-Z\\d]{8,}$")
    private String password;
    @NotBlank
    private String role;
    private boolean active;
    private LocalDate lastActive;

    public AdminDto(String adminId, String firstName, String lastName, String email, String password, String role, boolean active, LocalDate lastActive) {
        this.adminId = adminId;
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
