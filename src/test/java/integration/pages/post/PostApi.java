package integration.pages.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import integration.ApiBase;
import integration.schemas.PostCreateReq;
import integration.schemas.PostUpdateReq;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class PostApi extends ApiBase {

    Response response;

    Faker faker = new Faker();
    PostCreateReq postCreateReq;
    String title = "er";
    String description = "er";
    String body = faker.lorem().sentence(50);
    String publishDate = "";
    String imageUrl = "https://www.simplilearn.com/ice9/free_resources_article_thumb/what_is_image_Processing.jpg";

    public PostApi(String token) {
        super(token);
    }

    public PostCreateReq rndDataForCreatePost() {
        postCreateReq = new PostCreateReq();
        postCreateReq.setTitle(title);
        postCreateReq.setDescription(description);
        postCreateReq.setBody(body);
        postCreateReq.setPublishDate(publishDate);
        postCreateReq.setImageUrl(imageUrl);
        return this.postCreateReq;
    }

    public Response createAPostRoleUser(int code) {
        String endpoint = "/api/posts";
        Object body = rndDataForCreatePost();
        response = postRequest(endpoint, code, body);
        return response;
    }

    @Step("Create post")
    public String createPost(int code, PostCreateReq postCreateReg) throws JsonProcessingException {
        String endpoint = "/api/posts";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequestBody = objectMapper.writeValueAsString(postCreateReg);
        response = postRequest(endpoint, code, jsonRequestBody);
        return handleResponse(response);
    }

    @Step("Update post {id}")
    public String updateUserPost(String postId, PostUpdateReq postUpdateReq, int code) throws JsonProcessingException {
        String endpoint = "/api/posts/{id}";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequestBody = objectMapper.writeValueAsString(postUpdateReq);
        response = putRequest(endpoint, code, jsonRequestBody, "id", postId);
        return handleResponse(response);
    }

    public String deleteUserPost(String postId, int code) {
        String endpoint = "/api/posts/{id}";
        response = deleteRequest(endpoint, code, postId);
        return handleResponse(response);
    }

    public String getPostId(String postId, int code) {
        String endPoint = "/api/posts/{id}";
        response = getRequestWhitParam(endPoint, code, "id", postId);
        return handleResponse(response);
    }

    private String handleResponse(Response response) {
        switch (response.getStatusCode()) {
            case 200, 201, 204:
                return response.asString();
            case 400:
                return "Bad Request: " + response.jsonPath().getString("message");
            case 401:
                return "Unauthorized: " + response.jsonPath().getString("message");
            case 404:
                return "Not Found: " + response.jsonPath().getString("message");
            default:
                return "Unexpected status code: " + response.getStatusCode() + ". Response: " + response.asString();
        }
    }
}
