package integration.pages.user;

import integration.ApiBase;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetUserApi extends ApiBase {

    public GetUserApi(String token){
        super(token);
    }
    Response response;
    public String getUser(int code){
        String endpoint = "/api/me";
        response = getRequest(endpoint, 200);
        switch (response.getStatusCode()) {
            case 200:
                JsonPath jsonPath = response.jsonPath();
                assert jsonPath.getString("id") != null : "id is missing";
                assert jsonPath.getString("name") != null : "name is missing";
                assert jsonPath.getString("surname") != null : "surname is missing";
                assert jsonPath.getString("email") != null : "email is missing";
                assert jsonPath.getString("gender") != null : "gender is missing";
                assert jsonPath.getString("birthDate") != null : "birtDate is missing";
                assert jsonPath.getString("role") != null : "role is missing";
                assert jsonPath.getString("avatarUrl") != null : "role is missing";
                //assert jsonPath.getString("backgroundUrl") != null : "backgroundUrl is missing";
                break;
            case 400:
                System.err.println("Bad Request: " + response.asString());
                break;
            case 401:
                System.err.println("UNAUTHORIZED: " + response.asString());
                break;
            case 404:
                System.err.println("NOT_FOUND" + response.asString());
                break;
            default:
                System.err.println("Unexpected status code: " + response.getStatusCode() + " Response Body: " + response.asString());
        }
        return response.asString();
    }

    @Step("Get user")
    public String getUserInfo(int code) {
        String endPoint = "/api/me";
        Response response = getRequest(endPoint,200);
        switch (response.getStatusCode()) {
            case 200:
                JsonPath jsonPath = response.jsonPath();
                assert jsonPath.getString("id") != null : "id is missing";
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

