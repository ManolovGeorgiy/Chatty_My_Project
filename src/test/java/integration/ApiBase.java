package integration;

import com.github.javafaker.File;
import config.Config;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import java.util.Random;

public class ApiBase {
    protected final RequestSpecification spec;
    private final Config config = new Config();
    protected final String BASE_URL = config.getProjectApiUrl();

    public ApiBase() {
        this.spec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .build();

    }

    public ApiBase(String token) {
        this.spec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + token)
                .build();
    }

    public Response getAllPosts(int skip, int limit, int expectedStatusCode, String endpoint) {
        Response response = RestAssured.given()
                .spec(spec)
                .queryParam("skip", skip)
                .queryParam("limit", limit)
                .when()
                .log().all()
                .get(endpoint)
                .then().log().all()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
        return response;
    }


    protected Response getRequest(String endpoint, int code) {
        Response response = RestAssured.given()
                .spec(spec)
                .when()
                .log().all()
                .get(endpoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(code);
        return response;
    }

    protected Response getRequestWhitParam(String endpoint, int code, String paramName, String id) {
        Response response = RestAssured.given()
                .spec(spec)
                .when()
                .pathParam(paramName, id)
                .log().all()
                .get(endpoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(code);
        return response;
    }

    protected Response getRequestWhitParamString(String endpoint, int code, String paramName, String id) {
        Response response = RestAssured.given()
                .spec(spec)
                .when()
                .pathParam(paramName, id)
                .log().all()
                .get(endpoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(code);
        return response;
    }

    protected Response postRequest(String endpoint, int code, Object body) {
        Response response = RestAssured.given()
                .spec(spec)
                .body(body)
                .when()
                .log().all()
                .post(endpoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(code);
        return response;
    }

    protected Response putRequest(String endpoint, int code, Object body, String paramName, String id) {
        Response response = RestAssured.given()
                .spec(spec)
                .body(body)
                .when()
                .pathParam(paramName, id)
                .log().all()
                .put(endpoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(code);
        return response;
    }

    protected Response deleteRequest(String endpoint, int code, String id) {
        Response response = RestAssured.given()
                .spec(spec)
                .when()
                .pathParam("id", id)
                .log().all()

                .delete(endpoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(code);
        return response;
    }

    public Response uploadImageRequest(String endpoint, File imageFile, int code) {
        Response response = RestAssured.given()
                .spec(spec)
                .contentType("multipart/form-data")
                .multiPart("multipartFile", imageFile, "image/png")
                .when()
                .post(endpoint)
                .then().log().all()
                .statusCode(code)
                .extract()
                .response();

        response.then().assertThat().statusCode(code);
        return response;
    }
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://your_api_base_url";
    }
}