package integration.pages.user;

import integration.ApiBase;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.LinkedHashMap;

public class UserApi extends ApiBase {


    Response response;

    @Step("Registration user : {email}, {password}, {confirmPassword}, {role}")
    public String registration(String email, String password, String confirmPassword, String role, int code) {
        String endPoint = "/api/auth/register";
        LinkedHashMap<String, String> body = new LinkedHashMap<>();
        body.put("email", email);
        body.put("password", password);
        body.put("confirmPassword", confirmPassword);
        body.put("role", role);

        response = postRequest(endPoint, code, body);
        int statusCode = response.statusCode();

        if (statusCode == code) {
            return "User registered successfully";
        } else {
            String errorMessage = response.jsonPath().getString("message");
            return "Failed to register user: " + errorMessage;
        }
    }

    @Step("Login by Email and Password : {email},{password}")
    public String login(String email, String password, int code) {
        String endPoint = "/api/auth/login";
        LinkedHashMap<String, String> body = new LinkedHashMap<>();
        body.put("email", email);
        body.put("password", password);

        response = postRequest(endPoint, code, body);
        int statusCode = response.statusCode();

        if (statusCode == code) {
            String refreshToken = response.jsonPath().getString("refreshToken");
            return refreshToken;
        } else {
            String errorMessage = response.jsonPath().getString("message");
            return "Failed to login: " + errorMessage;
        }
    }
}