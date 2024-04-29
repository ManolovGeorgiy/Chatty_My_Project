package integration.tests.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import integration.pages.post.*;
import integration.pages.user.UserApi;
import integration.schemas.PostCreateReq;
import integration.schemas.PostUpdateReq;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;


public class UserCanCreateEditAndDeleteNewPost {

    Faker faker = new Faker();
    UserApi userApi;
    PostApi postApi;
    PostCreateReq postCreateReq;
    PostUpdateReq postUpdateReq;

    private void checkPostData(String postId, PostCreateReq postCreateReq){

        JsonPath actualObjects = JsonPath.given(postApi.getPostId(postId,200));
        LinkedHashMap<String,String> postObjects = new LinkedHashMap<>();
        postObjects.put(actualObjects.getString("title"),postCreateReq.getTitle());
        postObjects.put(actualObjects.getString("description"),postCreateReq.getDescription());
        postObjects.put(actualObjects.getString("body"),postCreateReq.getBody());

        for (Map.Entry<String,String> postObject:postObjects.entrySet()){
            String actualResult = postObject.getKey();
            String expectedResult =postObject.getValue();
            Assert.assertEquals(actualResult,expectedResult, actualResult + " is not equals " + expectedResult);
        }
    }
    private void checkEditPostData(String postId, PostUpdateReq postUpdateReq){

        JsonPath actualObjects = JsonPath.given(postApi.getPostId(postId,200));
        LinkedHashMap<String,String> postObjects = new LinkedHashMap<>();
        postObjects.put(actualObjects.getString("title"),postUpdateReq.getTitle());
        postObjects.put(actualObjects.getString("description"),postUpdateReq.getDescription());
        postObjects.put(actualObjects.getString("body"),postUpdateReq.getBody());

        for (Map.Entry<String,String> postObject:postObjects.entrySet()){
            String actualResult = postObject.getKey();
            String expectedResult =postObject.getValue();
            Assert.assertEquals(actualResult,expectedResult, actualResult + " is not equals " + expectedResult);
        }
    }@Feature(value = "Creating,editing and deleting post")
    @Description(value = "New post")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "User Can create,edit and delete post")
    public void userCanCreatePost() throws JsonProcessingException {
        String email = "user.can.create.a.post@gmail.com";
        String password = "Manowar33246";

        String title = "Chatty";
        String description = "GPower";
        String body = faker.lorem().sentence(10);
        String imageURL = ("https://dfstudio-d420.kxcdn.com/wordpress/wp-content/uploads/2019/06/digital_camera_photo-1080x675.jpg");

        String editTitle = "GPower";
        String editDescription = "Beautiful";
        String editBody = faker.lorem().sentence(20);
        String editImageURL = ("https://letsenhance.io/static/8f5e523ee6b2479e26ecc91b9c25261e/1015f/MainAfter.jpg");

        userApi = new UserApi();
        String token = userApi.login(email, password, 200);

        postCreateReq = new PostCreateReq();
        postCreateReq.setTitle(title);
        postCreateReq.setDescription(description);
        postCreateReq.setBody(body);
        postCreateReq.setImageUrl(imageURL);

        postUpdateReq = new PostUpdateReq();
        postUpdateReq.setTitle(editTitle);
        postUpdateReq.setDescription(editDescription);
        postUpdateReq.setBody(editBody);
        postUpdateReq.setImageUrl(editImageURL);

        postApi = new PostApi(token);
        String response = postApi.createPost(201, postCreateReq);
        JsonPath jsonPath = new JsonPath(response);
        String postId = jsonPath.getString("id");
        postApi.getPostId(postId,200);
        checkPostData(postId,postCreateReq);

        String responseEdit = postApi.updateUserPost(postId, postUpdateReq, 200);
        JsonPath jsonPathEdit = new JsonPath(responseEdit);
        String editPostId = jsonPathEdit.getString("id");
        postApi.getPostId(postId,200);
        checkEditPostData(postId,postUpdateReq);

        postApi.deleteUserPost(editPostId, 204);
    }
}
