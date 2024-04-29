package e2e.tests.about;

import e2e.TestBase;
import e2e.enums.SideBarInfo;
import e2e.pages.Header;
import e2e.pages.about.AboutPage;
import e2e.pages.adminPanel.AdminPanelPage;
import e2e.pages.homeBlog.HomeBlogPage;
import e2e.pages.login.LoginPage;
import e2e.pages.registration.RegistrationPage;
import org.testng.annotations.Test;

public class AboutPageTest extends TestBase {

    LoginPage loginPage;
    RegistrationPage registrationPage;
    HomeBlogPage homeBlogPage;
    Header header;
    AboutPage aboutPage;
    AdminPanelPage adminPanelPage;

    @Test
    public void testAboutBoxText() {

        String email = "about.text@gmail.com";
        String password = "Manowar333246";
        String confirmPassword = "Manowar333246";

        String expectedText = "about Chatty" + "Chatty is a social network platform designed to connect people and facilitate communication in a friendly and interactive environment. Our mission is to bring people together, encourage meaningful conversations, and create a positive online community." + "Join Chatty today and become a part of our growing community. Share your thoughts, connect with friends, and experience the joy of online socializing.";

        String emailLogin = "g.power@gmail.com";
        String passwordLogin = "GPower3333";

        String emailAccount = "about.text@gmail.com";

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.signUp();

        registrationPage = new RegistrationPage(app.driver);
        registrationPage.waitForLoading();
        registrationPage.optionUser();
        registrationPage.registration(email, password, confirmPassword);

        homeBlogPage = new HomeBlogPage(app.driver);

        header = new Header(app.driver);
        header.waitForLoading();
        header.clickAbout();

        aboutPage = new AboutPage(app.driver);

        String actualText = aboutPage.getAboutText();

        if (actualText.equals(expectedText)) {
            System.out.println("The text on the about page matches the expected text");
        } else {
            System.out.println("The text on the about page does not match the expected text");
        }

        header = new Header(app.driver);
        header.clickHome();
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
