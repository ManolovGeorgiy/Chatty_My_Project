package integration.tests.profile;

import com.fasterxml.jackson.core.JsonProcessingException;
import integration.pages.adminPanel.DeleteUserApi;
import integration.pages.profile.AddDataUser;
import integration.pages.user.GetUserApi;
import integration.pages.user.UpdateUser;
import integration.pages.user.UserApi;
import integration.schemas.UserRes;
import integration.schemas.UserUpdateReq;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserCanAddAndUpdateData {

    UserApi userApi;
    GetUserApi getUserApi;
    UserRes userRes;
    AddDataUser addDataUser;
    UserUpdateReq userUpdateReq;
    UpdateUser updateUser;
    DeleteUserApi deleteUserApi;
    @Feature(value = "Profile data adding and updating")
    @Description(value = "User can add and update data to profile")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "User can add and update data to profile")
    public void userCanAddAndUpdateProfile() throws JsonProcessingException {

        String emailRegistration = "user.addandupdate.data@gmail.com";
        String passwordRegistration = "Manowar33246";
        String confirmPassword = "Manowar33246";
        String role = "user";

        userApi = new UserApi();
        userApi.registration(emailRegistration, passwordRegistration, confirmPassword, role, 201);
        userApi.login(emailRegistration, passwordRegistration, 200);

        String email = "user.addandupdate.data@gmail.com";
        String password = "Manowar33246";
        String name = "Marta";
        String surname = "Berg";
        String birthDate = "1999-03-23T00:00:00.000Z";
        String phone = "+1234567890";
        String gender = "FEMALE";
        String imageAvatar = ("https://buffer.com/library/content/images/size/w1200/2023/10/free-images.jpg");

        String editName = "Maria";
        String editSurname = "Folk";
        String editBirthDate = "1989-12-31T00:00:00.000Z";
        String editPhone = "+4323648901";
        String editGender = "FEMALE";
        String editAvatar = ("https://rare-gallery.com/thumbs/5408546-woman-female-girl-photographer-camera-beanie-pine-holding-hand-jean-jacket-red-film-caucasian-blonde-photography-nature-exterior-hat-vintage-contax-creative-commons-images.jpg");

        String emailAdminLogin = "g.power@gmail.com";
        String passwordAdminLogin = "GPower3333";

        userApi = new UserApi();
        String token = userApi.login(email, password, 200);

        getUserApi = new GetUserApi(token);
        String userJson = getUserApi.getUserInfo(200);
        JsonPath object = new JsonPath(userJson);
        String userId = object.getString("id");

        userRes = new UserRes();
        userRes.setName(name);
        userRes.setSurname(surname);
        userRes.setBirthDate(birthDate);
        userRes.setPhone(phone);
        userRes.setGender(gender);
        userRes.setAvatarUrl(imageAvatar);

        addDataUser = new AddDataUser(token);
        addDataUser.addUserProfile(userId, userRes, 200);

        String addedUserData = getUserApi.getUserInfo(200);
        Assert.assertTrue(addedUserData.contains(name));
        Assert.assertTrue(addedUserData.contains(surname));
        Assert.assertTrue(addedUserData.contains(birthDate));
        Assert.assertTrue(addedUserData.contains(phone));
        Assert.assertTrue(addedUserData.contains(gender));
        Assert.assertTrue(addedUserData.contains(imageAvatar));

        userUpdateReq = new UserUpdateReq();
        userUpdateReq.setName(editName);
        userUpdateReq.setSurname(editSurname);
        userUpdateReq.setBirthDate(editBirthDate);
        userUpdateReq.setPhone(editPhone);
        userUpdateReq.setGender(editGender);
        userUpdateReq.setAvatarUrl(editAvatar);

        updateUser = new UpdateUser(token);
        updateUser.updateUserProfile(userId, userUpdateReq, 200);
        String updatedUserJson = getUserApi.getUserInfo(200);
        JsonPath updatedUser = new JsonPath(updatedUserJson);
        String editUserId = updatedUser.getString("id");

        String updatedUserData = getUserApi.getUserInfo(200);
        Assert.assertTrue(updatedUserData.contains(editName));
        Assert.assertTrue(updatedUserData.contains(editSurname));
        Assert.assertTrue(updatedUserData.contains(editBirthDate));
        Assert.assertTrue(updatedUserData.contains(editPhone));
        Assert.assertTrue(updatedUserData.contains(editGender));
        Assert.assertTrue(updatedUserData.contains(editAvatar));

        String tokenAdmin = userApi.login(emailAdminLogin, passwordAdminLogin, 200);
        getUserApi = new GetUserApi(tokenAdmin);

        deleteUserApi = new DeleteUserApi(tokenAdmin);
        deleteUserApi.deleteUser(204, editUserId);
    }
}
