package e2e.tests.post;

import com.github.javafaker.Faker;
import e2e.TestBase;
import e2e.enums.SideBarInfo;
import e2e.pages.Header;
import e2e.pages.adminPanel.AdminPanelPage;
import e2e.pages.homeBlog.HomeBlogPage;
import e2e.pages.login.LoginPage;
import e2e.pages.post.CreateAPostForm;
import e2e.pages.registration.RegistrationPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Locale;

import static org.testng.AssertJUnit.assertTrue;


public class UserCanNotCreateAPost extends TestBase {

    Faker faker = new Faker(new Locale("ENGLISH"));

    LoginPage loginPage;
    RegistrationPage registrationPage;
    HomeBlogPage homeBlogPage;
    CreateAPostForm createAPostForm;
    Header header;
    AdminPanelPage adminPanelPage;

    private void checkPostData(CreateAPostForm page, String title, String description, String content) {
        String actualTitle = page.getTitle();
        String actualDescription = page.getDescriptionText();
        String actualContent = page.getContent();
        Assert.assertEquals(actualTitle, title, actualTitle + " is not equal " + title);
        Assert.assertEquals(actualDescription, description, actualDescription + " is not equal " + description);
        Assert.assertEquals(actualContent, content, actualContent + " is not equal " + content);
    }

    @Epic(value = "User can not create a post")
    @Feature(value = "User not created post")
    @Description(value = "User can not create a post")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "User can not create a post with invalid data")
    public void userCanNotCreateAPost() throws InterruptedException {
        String email = "user.can.notCreateAPost@gmail.com";
        String password = "RedBul1234";
        String confirmPassword = "RedBul1234";
        String title = "";
        String description = faker.lorem().sentence(10);
        String content = faker.lorem().sentence(10);
        String imagePath = "uploadReferences/userCanCreateAPost_MyPost.jpg";

        String emailLogin = "g.power@gmail.com";
        String passwordLogin = "GPower3333";

        String emailAccount = "user.can.notCreateAPost@gmail.com";

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.signUp();

        registrationPage = new RegistrationPage(app.driver);
        registrationPage.waitForLoading();
        registrationPage.optionUser();
        registrationPage.registration(email, password, confirmPassword);

        homeBlogPage = new HomeBlogPage(app.driver);
        homeBlogPage.waitForLoading();

        header = new Header(app.driver);
        header.createAPostClick();
        header.waitForLoading();

        createAPostForm = new CreateAPostForm(app.driver);
        createAPostForm.setPostForm(title, description, content, imagePath);
        createAPostForm.uploadImage(imagePath);
        createAPostForm.waitForLoading();
        createAPostForm.clickSubmitButton();

        checkPostData(createAPostForm, title, description, content);

        homeBlogPage = new HomeBlogPage(app.driver);

        assertTrue("Please fill the field", createAPostForm.errorText());
        createAPostForm.takePostPageScreenshot("User_can_not_create_a_post");

        header = new Header(app.driver);
        header.tabDropdownMenu(SideBarInfo.LOGIN);

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login(emailLogin, passwordLogin);

        adminPanelPage = new AdminPanelPage(app.driver);
        adminPanelPage.waitForLoading();
        adminPanelPage.searchAccount(emailAccount);
        adminPanelPage.waitForLoading();
        adminPanelPage.clickDeleteAccount();
        adminPanelPage.searchAccount(emailAccount);

        header = new Header(app.driver);
        header.tabDropdownMenu(SideBarInfo.LOGIN);
    }

}
