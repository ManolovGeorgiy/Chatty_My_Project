package integration.pages.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import integration.ApiBase;
import integration.schemas.PasswordUpdateReq;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class UpdatePassword extends ApiBase {
    public UpdatePassword(String token) {
        super(token);
    }

    Response response;
    @Step("Change user password :{id}")
    public String changePassword(String userId, PasswordUpdateReq passwordUpdateReq, int code) throws JsonProcessingException {
        String endpoint = "/api/user/password/update";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(passwordUpdateReq);
        response = putRequest(endpoint, code, jsonRequest,"id",userId);
        switch (response.getStatusCode()) {
            case 200:
                return response.asString();
            case 400:
                return "Bad Request: " + response.jsonPath().getString("message");
            case 401:
                return "Unauthorized: " + response.jsonPath().getString("message");
            default:
                return "Unexpected status code: " + response.getStatusCode() + " - " + response.asString();
        }
    }
}
