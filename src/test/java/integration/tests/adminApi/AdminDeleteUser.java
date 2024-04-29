package integration.tests.adminApi;

import com.fasterxml.jackson.core.JsonProcessingException;
import integration.pages.adminPanel.DeleteUserApi;
import integration.pages.profile.AddDataUser;
import integration.pages.user.GetUserApi;
import integration.pages.user.UpdateUser;
import integration.pages.user.UserApi;
import integration.schemas.UserRes;
import integration.schemas.UserUpdateReq;
import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminDeleteUser {

    UserApi userApi;
    GetUserApi getUserApi;
    UserRes userRes;
    AddDataUser addDataUser;
    UpdateUser updateUser;
    UserUpdateReq userUpdateReq;
    DeleteUserApi deleteUserApi;

    private void checkUserData(UserRes user, String name, String surname, String birthDate, String phone, String gender, String avatarUrl) {
        Assert.assertEquals(user.getName(), name);
        Assert.assertEquals(user.getSurname(), surname);
        Assert.assertEquals(user.getBirthDate(), birthDate);
        Assert.assertEquals(user.getPhone(), phone);
        Assert.assertEquals(user.getGender(), gender);
        Assert.assertEquals(user.getAvatarUrl(), avatarUrl);
    }

    private void checkEditUserData(UserUpdateReq user, String name, String surname, String birthDate, String phone, String gender, String avatarUrl) {
        Assert.assertEquals(user.getName(), name);
        Assert.assertEquals(user.getSurname(), surname);
        Assert.assertEquals(user.getBirthDate(), birthDate);
        Assert.assertEquals(user.getPhone(), phone);
        Assert.assertEquals(user.getGender(), gender);
        Assert.assertEquals(user.getAvatarUrl(), avatarUrl);
    }

    @Feature(value = "Deleted User")
    @Story(value = "Admin can delete User")
    @Description(value = "Admin can delete User")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Admin can delete new User")
    public void adminCanDeleteUser() throws JsonProcessingException {

        String email = "bobo@gmail.com";
        String password = "Boba9876";
        String confirmPassword = "Boba9876";
        String role = "user";

        String emailAdminLogin = "g.power@gmail.com";
        String passwordAdminLogin = "GPower3333";

        userApi = new UserApi();
        userApi.registration(email, password, confirmPassword, role, 201);
        String token = userApi.login(email, password, 200);
        getUserApi = new GetUserApi(token);
        String userJson = getUserApi.getUserInfo(200);
        JsonPath object = new JsonPath(userJson);
        String userId = object.getString("id");

        userRes = new UserRes();
        userRes.setName("Bobo");
        userRes.setSurname("Bobo");
        userRes.setBirthDate("1995-06-27T00:00:00.000Z");
        userRes.setPhone("+1234567890");
        userRes.setGender("FEMALE");
        userRes.setAvatarUrl("https://imgv3.fotor.com/images/slider-image/Female-portrait-picture-enhanced-with-better-clarity-and-higher-quality-using-Fotors-free-online-AI-photo-enhancer.jpg");

        addDataUser = new AddDataUser(token);
        addDataUser.addUserProfile(userId, userRes, 200);

        checkUserData(userRes, "Bobo", "Bobo", "1995-06-27T00:00:00.000Z", "+1234567890", "FEMALE", "https://imgv3.fotor.com/images/slider-image/Female-portrait-picture-enhanced-with-better-clarity-and-higher-quality-using-Fotors-free-online-AI-photo-enhancer.jpg");

        userUpdateReq = new UserUpdateReq();
        userUpdateReq.setName("Marta");
        userUpdateReq.setSurname("Bobovna");
        userUpdateReq.setBirthDate("1983-06-12T00:00:00.000Z");
        userUpdateReq.setPhone("+2222567890");
        userUpdateReq.setGender("MALE");
        userUpdateReq.setAvatarUrl("https://letsenhance.io/static/8f5e523ee6b2479e26ecc91b9c25261e/1015f/MainAfter.jpg");

        updateUser = new UpdateUser(token);
        updateUser.updateUserProfile(userId, userUpdateReq, 200);
        checkEditUserData(userUpdateReq, "Marta", "Bobovna", "1983-06-12T00:00:00.000Z", "+2222567890", "MALE", "https://letsenhance.io/static/8f5e523ee6b2479e26ecc91b9c25261e/1015f/MainAfter.jpg");


        String tokenAdmin = userApi.login(emailAdminLogin, passwordAdminLogin, 200);
        getUserApi = new GetUserApi(tokenAdmin);

        deleteUserApi = new DeleteUserApi(tokenAdmin);
        deleteUserApi.deleteUser(204, userId);
    }
}