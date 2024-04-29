package e2e.tests.registration;

import e2e.TestBase;
import e2e.enums.SideBarInfo;
import e2e.pages.Header;
import e2e.pages.adminPanel.AdminPanelPage;
import e2e.pages.homeBlog.HomeBlogPage;
import e2e.pages.login.LoginPage;
import e2e.pages.registration.RegistrationPage;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertTrue;

public class RegistrationAndAuthorisationUserTest extends TestBase {

LoginPage loginPage;
RegistrationPage registrationPage;
HomeBlogPage homeBlogPage;
Header header;
AdminPanelPage adminPanelPage;

    @Epic(value = "User can registration and authorisation")
    @Feature(value = "The user has registered and logged in")
    @Description(value = "User can registration and authorisation")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "User can registration")
    public void userCanRegistration(){

        String email = "User.can.registration@gmail.com";
        String password = "Manowar333246";
        String confirmPassword = "Manowar333246";

        String emailLogin = "g.power@gmail.com";
        String passwordLogin = "GPower3333";

        String emailAccount = "User.can.registration@gmail.com";

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.signUp();

        registrationPage = new RegistrationPage(app.driver);
        registrationPage.waitForLoading();
        registrationPage.optionUser();
        registrationPage.registration(email,password,confirmPassword);

        homeBlogPage = new HomeBlogPage(app.driver);
        homeBlogPage.waitForLoading();

        header = new Header(app.driver);
        header.tabDropdownMenu(SideBarInfo.LOGIN);

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login(email,password);

        homeBlogPage = new HomeBlogPage(app.driver);
        homeBlogPage.waitForLoading();

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

    @Feature(value = "The user has not registered")
    @Description(value = "user can not registration ")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "User can not registration")
    public void UserCanNotRegistrationWithValidEmail() {

        String email = "tatar1@gmail.com";
        String password = "REd12345";
        String confirmPassword = "REd12345";

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.signUp();

        registrationPage = new RegistrationPage(app.driver);
        registrationPage.waitForLoading();
        registrationPage.optionUser();
        registrationPage.registration(email, password, confirmPassword);
        registrationPage.waitForLoading();
        assertTrue("Email already exists!", registrationPage.textError());
        registrationPage.takeLoginPageScreenshot("User_can't_registration");
    }
}

