package integration.tests.registration;

import integration.pages.adminPanel.DeleteUserApi;
import integration.pages.user.GetUserApi;
import integration.pages.user.UserApi;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

public class RegistrationApiTest {

    UserApi userApi;
    DeleteUserApi deleteUserApi;
    GetUserApi getUserApi;

    @Description(value = "User can registration")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "New user can registration")
    public void UserCanRegistration() {
        String email = "registration.user@gmail.com";
        String password = "Manowar33246";
        String confirmPassword = "Manowar33246";
        String role = "user";

        String emailAdminLogin = "g.power@gmail.com";
        String passwordAdminLogin = "GPower3333";

        userApi = new UserApi();
        userApi.registration(email, password, confirmPassword, role, 201);
        userApi.login(email, password, 200);

        userApi = new UserApi();
        String token = userApi.login(email, password, 200);
        getUserApi = new GetUserApi(token);
        String userJson = getUserApi.getUserInfo(200);
        JsonPath object = new JsonPath(userJson);
        String userId = object.getString("id");
        System.out.println(userId);

        String tokenAdmin = userApi.login(emailAdminLogin, passwordAdminLogin, 200);
        getUserApi = new GetUserApi(tokenAdmin);

        deleteUserApi = new DeleteUserApi(tokenAdmin);
        deleteUserApi.deleteUser(204, userId);
    }

    @Feature(value = "The user has not registered")
    @Description(value = "User can not registration with invalid password")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "New user can registration with invalid password")
    public void userCanNotRegistrationWithInvalidPassword() {
        String email = "user.can.registration@gmail.com";
        String password = "Mano1234";
        String confirmPassword = "Mano12345";
        String role = "user";
        userApi = new UserApi();
        userApi.registration(email, password, confirmPassword, role, 400);
    }

    @Feature(value = "The user has not registered")
    @Description(value = "User can not registration with invalid email")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "New user can registration wit invalid email")
    public void userCanNotRegistrationWithInvalidEmail() {
        String email = "user.can.registrationgmail.com";
        String password = "Mano1234";
        String confirmPassword = "Mano1234";
        String role = "user";
        userApi = new UserApi();
        userApi.registration(email, password, confirmPassword, role, 400);
    }

    @Feature(value = "The user has not registered")
    @Description(value = "User can not registration without data")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "User can not registration without data")
    public void userCanNotRegistrationWithoutData() {
        String email = "";
        String password = "";
        String confirmPassword = "";
        String role = "user";
        userApi = new UserApi();
        userApi.registration(email, password, confirmPassword, role, 400);
    }
}