package integration.pages.user;

import integration.ApiBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UserInfo extends ApiBase {

    Response response;

    public UserInfo(String token) {
        super(token);
    }

    public String getUser() {
        String endpoint = "/api/me";
        response = getRequest(endpoint, 200);
        switch (response.getStatusCode()) {
            case 200:
                JsonPath jsonPath = response.jsonPath();
                assert jsonPath.getString("id") != null : "ID is missing";
                assert jsonPath.getString("email") != null : "Name is missing";
                assert jsonPath.getString("role") != null : "Surname is missing";
                break;
            case 400:
                System.err.println("Bad Request: " + response.asString());
                break;
            case 401:
                System.err.println("Unauthorized: " + response.asString());
                break;
            default:
                System.err.println("Unexpected status code: " + response.getStatusCode() + " Response Body: " + response.asString());
        }
        return response.asString();
    }
}
