package se.swcg.consultauction.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UpdatePassword {

    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,32}$",
            message = "At least one digit, one lower case, one upper case, one special character(!@#$%^&+=)")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
