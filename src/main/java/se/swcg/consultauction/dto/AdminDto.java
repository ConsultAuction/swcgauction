package se.swcg.consultauction.dto;

import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class AdminDto {

    private String adminId;

    @NotBlank(message = "Please enter first name")
    @Size(min = 2, max = 45)
    private String firstName;

    @NotBlank(message = "Please enter last name")
    @Size(min = 2, max = 100)
    private String lastName;

    @NotBlank(message = "Please enter email")
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message="Not a valid email address")
    private String email;

    @NotBlank
    /*
    *^                 # start-of-string
    *(?=.*[0-9])       # a digit must occur at least once
    *(?=.*[a-z])       # a lower case letter must occur at least once
    *(?=.*[A-Z])       # an upper case letter must occur at least once
    *(?=.*[!@#$%^&+=]) # a special character must occur at least once
    *(?=\S+$)          # no whitespace allowed in the entire string
    *.{8,32}           # at least eight places and max thirty-two
    *$                 # end-of-string
    * */
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,32}$",
    message = "At least one digit, one lower case, one upper case, one special character(!@#$%^&+=)")
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
