package integration.pages.profile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import integration.ApiBase;
import integration.schemas.UserRes;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.testng.AssertJUnit.assertNotNull;

public class AddDataUser extends ApiBase {

    public AddDataUser(String token) {
        super(token);
    }

    @Step("Update user with {id}")
    public String addUserProfile(String userId, UserRes userRes, int code) throws JsonProcessingException {
        String endpoint = "/api/users/{id}";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(userRes);

        Response response = putRequest(endpoint, code, jsonRequest, "id", userId);
        validateResponse(response);
        return handleResponse(response);
    }

    private void validateResponse(Response response) {
        if (response.getStatusCode() == 200) {
            JsonPath jsonPath = response.jsonPath();
            assertNotNull("Id is missing", jsonPath.get("id"));
            assertNotNull("Name is missing", jsonPath.get("name"));
            assertNotNull("Surname is missing", jsonPath.get("surname"));
            assertNotNull("Phone is missing", jsonPath.get("phone"));
            assertNotNull("Email is missing", jsonPath.get("email"));
            assertNotNull("Gender is missing", jsonPath.get("gender"));
            assertNotNull("AvatarUrl is missing", jsonPath.get("avatarUrl"));
        }
    }

    private String handleResponse(Response response) {
        switch (response.getStatusCode()) {
            case 200:
                return response.asString();
            case 400:
                return "Bad Request: " + response.jsonPath().getString("message");
            case 401:
                return "Unauthorized: " + response.jsonPath().getString("message");
            default:
                return "Unexpected status code: " + response.getStatusCode() + ". Message: " + response.asString();
        }
    }
}
