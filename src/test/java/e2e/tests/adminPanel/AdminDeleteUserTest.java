package e2e.tests.adminPanel;

import e2e.TestBase;
import e2e.pages.adminPanel.AdminPanelPage;
import e2e.pages.login.LoginPage;
import org.testng.annotations.Test;

public class AdminDeleteUserTest extends TestBase {

    LoginPage loginPage;
    AdminPanelPage adminPanelPage;

    @Test
    public void deleteAccount() {
        String emailLogin = "g.power@gmail.com";
        String passwordLogin = "GPower3333";

        String emailAccount = "tatar@abv.bg";

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login(emailLogin, passwordLogin);

        adminPanelPage = new AdminPanelPage(app.driver);
        adminPanelPage.waitForLoading();
        adminPanelPage.searchAccount(emailAccount);
        adminPanelPage.waitForLoading();
        adminPanelPage.clickDeleteAccount();
        adminPanelPage.searchAccount(emailAccount);
    }
}

