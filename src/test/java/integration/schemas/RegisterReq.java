package integration.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterReq {
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;
    @JsonProperty("confirmPassword")
    private String confirmPassword;

    @JsonProperty("role")
    private String role;
}
