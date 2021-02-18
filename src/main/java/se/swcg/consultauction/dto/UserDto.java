package se.swcg.consultauction.dto;

import se.swcg.consultauction.entity.Contact;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Objects;

public class UserDto {

    private String userId;
    private String companyName;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,32}$",
            message = "At least one digit, one lower case, one upper case, one special character(!@#$%^&+=)")
    private String password;
    private String role;
    private LocalDate dateOfSignUp;
    private LocalDate lastActive;
    private boolean active;
    private String image;
    private Contact contact;

    public UserDto(String userId, String companyName, String firstName, String lastName, String email,  String password, String role, LocalDate dateOfSignUp, LocalDate lastActive, boolean active, String image, Contact contact) {
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

    public UserDto(String companyName, String firstName, String lastName, String email, String password, String role, LocalDate dateOfSignUp, LocalDate lastActive, boolean active, String image, Contact contact) {
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

    public UserDto() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        UserDto userDto = (UserDto) o;
        return active == userDto.active &&
                Objects.equals(userId, userDto.userId) &&
                Objects.equals(companyName, userDto.companyName) &&
                Objects.equals(firstName, userDto.firstName) &&
                Objects.equals(lastName, userDto.lastName) &&
                Objects.equals(email, userDto.email) &&
                Objects.equals(password, userDto.password) &&
                Objects.equals(role, userDto.role) &&
                Objects.equals(dateOfSignUp, userDto.dateOfSignUp) &&
                Objects.equals(lastActive, userDto.lastActive) &&
                Objects.equals(image, userDto.image) &&
                Objects.equals(contact, userDto.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, companyName, firstName, lastName, email, password, role, dateOfSignUp, lastActive, active, image, contact);
    }

    @Override
    public String toString() {
        return "UserDto{" +
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
                ", address=" + contact +
                '}';
    }
}
