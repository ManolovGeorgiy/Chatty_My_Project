package e2e.tests.login;

import e2e.TestBase;
import e2e.pages.login.LoginPage;
import e2e.utils.DataProviders;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class LoginAdminTest extends TestBase {

    LoginPage loginPage;

    @Description(value = "admin can login")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "Admin can login")
    public void adminCanLogin() {
        String email = "g.power@gmail.com";
        String password = "GPower3333";
        loginPage = new LoginPage(app.driver);
        loginPage.login(email, password);
    }

    @Feature(value = "adminPanel is not logged in")
    @Description(value = "adminPanel can't login")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "Admin can not login with invalid email")
    public void adminCanNotLoginWithInvalidEmail() {
        String email = "gpower@gmail.com";
        String password = "GPower3333";
        loginPage = new LoginPage(app.driver);
        loginPage.login(email, password);
        assertTrue("User not found. Please register.", loginPage.textError());
    }

    @Feature(value = "adminPanel is not logged in")
    @Description(value = "adminPanel can't login")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "Admin can not login with invalid password")
    public void adminCanNotLoginWithInvalidPassword() {
        String email = "g.power@gmail.com";
        String password = "GPower3334";
        loginPage = new LoginPage(app.driver);
        loginPage.login(email, password);
    }

    @Feature(value = "adminPanel is not logged in")
    @Description(value = "adminPanel can't login")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "Admin can not login without email and password")
    public void adminCanNotLoginWithoutAEmailAndPassword() {
        String email = "";
        String password = "";

        loginPage = new LoginPage(app.driver);
        loginPage.login(email, password);
        loginPage.takeLoginPageScreenshot("adminCanNotLoginWithoutAEmailAndPassword");
    }

    @Feature(value = "adminPanel is not logged in")
    @Description(value = "adminPanel can't login")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "Admin can not login wit invalid email and password")
    public void adminCanNotLoginWiInvalidEmailAndPassword() {
        String email = "gpower@gmail.com";
        String password = "GPower3334";
        loginPage = new LoginPage(app.driver);
        loginPage.login(email, password);
        assertTrue("User not found. Please register.", loginPage.textError());
    }

    @Feature(value = "adminPanel is not logged in")
    @Description(value = "adminPanel can't login")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "All negative Test", dataProvider = "adminCanNotLoginTest", dataProviderClass = DataProviders.class)
    public void adminCanNotLoginWithInvalidData(String email, String password, String caseName) {
        loginPage = new LoginPage(app.driver);
        loginPage.login(email, password);
        loginPage.waitForLoading();
        loginPage.takeLoginPageScreenshot(caseName + "_negative_login_case");
        loginPage.waitForLoading();
    }
}
