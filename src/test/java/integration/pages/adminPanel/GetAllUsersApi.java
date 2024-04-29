package integration.pages.adminPanel;

import integration.ApiBase;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class GetAllUsersApi extends ApiBase {

    Response response;

    public GetAllUsersApi(String token) {
        super(token);
    }

    @Step("Get all users")
    public String getAllUsersList(int expectedStatusCode, int skip, int limit) {
        String endpoint = "/api/users";
        response = getAllPosts(skip, limit, expectedStatusCode, endpoint);
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
