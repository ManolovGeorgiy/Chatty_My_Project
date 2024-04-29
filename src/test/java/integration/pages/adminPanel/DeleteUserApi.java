package integration.pages.adminPanel;


import io.qameta.allure.Step;
import io.restassured.response.Response;

public class DeleteUserApi extends integration.ApiBase {
    Response response;

    public DeleteUserApi(String token) {
        super(token);
    }

    @Step("Delete user: {userId}")
    public String deleteUser(int code, String userId) {
        String endpoint = "/api/users/{id}";
        response = deleteRequest(endpoint, code, userId);
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
