package integration.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResForAdmin {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("id")
    private String id;

    @JsonProperty("email")
    private String email;
    @JsonProperty("surname")
    private String surname;

    @JsonProperty("phone")
    private String phone;


    @JsonProperty("role > Array [User,adminPanel]")
    private String role;

    @JsonProperty("gender > Array [Male,Female]")
    private String gender;

    @JsonProperty("birthDate ($date-time)")
    private  String birthDate;

    @JsonProperty("avatarUrl")
    private  String avatarUrl;

    @JsonProperty("backgroundUrl")
    private String backgroundUrl;

    @JsonProperty("blocked")
    private Boolean blocked;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

}
