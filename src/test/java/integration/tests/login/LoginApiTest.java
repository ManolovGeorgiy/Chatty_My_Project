package integration.tests.login;


import integration.pages.user.GetUserApi;
import integration.pages.user.UserApi;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

public class LoginApiTest {

    UserApi userApi;
    GetUserApi getUserApi;

    @Description(value = "admin can login")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "Admin can login with valid data")
    public void adminCanLogin() {
        String email = "g.power@gmail.com";
        String password = "GPower3333";
        userApi = new UserApi();
        String token = userApi.login(email, password, 200);
        getUserApi = new GetUserApi(token);
        String userJson = getUserApi.getUser(200);
        JsonPath object = new JsonPath(userJson);
        String userId = object.getString("id");
        System.out.println(userId);
    }

    @Description(value = "adminPanel can't login")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "Admin can login with invalid password")
    public void adminCannotLoginWithInvalidPassword() {
        String email = "g.power@gmail.com";
        String password = "GPower3334";
        userApi = new UserApi();
        String token = userApi.login(email, password, 401);
        getUserApi = new GetUserApi(token);
    }

    @Description(value = "admin can not login")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "Admin can login with invalid email")
    public void adminCannotLoginWithInvalidEmail() {
        String email = "g.powerr@gmail.com";
        String password = "GPower3333";
        userApi = new UserApi();
        String token = userApi.login(email, password, 404);
        getUserApi = new GetUserApi(token);
    }

    @Feature(value = "admin is not logged in")
    @Description(value = "admin can not login")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "Admin can login without email and Password ")
    public void adminCanNotLoginWithoutEmailAndPassword() {
        String email = "";
        String password = "";
        userApi = new UserApi();
        String token = userApi.login(email, password, 400);
        getUserApi = new GetUserApi(token);
    }
}
