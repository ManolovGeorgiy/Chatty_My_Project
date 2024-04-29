package e2e.tests.profile;

import com.github.javafaker.Faker;
import e2e.TestBase;
import e2e.enums.GenderInfo;
import e2e.enums.SideBarInfo;
import e2e.pages.Header;
import e2e.pages.adminPanel.AdminPanelPage;
import e2e.pages.homeBlog.HomeBlogPage;
import e2e.pages.login.LoginPage;
import e2e.pages.profile.AddUserDialog;
import e2e.pages.profile.EditPasswordForm;
import e2e.pages.profile.EditUserForm;
import e2e.pages.registration.RegistrationPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EditUserDataProfileTestRemote extends TestBase {

    Faker faker = new Faker();
    RegistrationPage registrationPage;
    LoginPage loginPage;
    Header header;
    HomeBlogPage homeBlogPage;
    AddUserDialog addUserDialog;
    EditUserForm editUserForm;
    EditPasswordForm editPasswordForm;
    AdminPanelPage adminPanelPage;

    private void checkUserData(AddUserDialog page, String name, String surname, String date, String phone) {
        String actualName = page.getName();
        String actualSurname = page.getSurname();
        String actualDate = page.getDate();
        String actualPhone = page.getPhone();
        Assert.assertEquals(actualName, name, actualName + " is not equal " + name);
        Assert.assertEquals(actualSurname, surname, actualSurname + " is not equal " + surname);
        Assert.assertEquals(actualDate, date, actualDate + " is not equal " + date);
        Assert.assertEquals(actualPhone, phone, actualPhone + " is not equal " + phone);
    }

    private void checkEditUserData(EditUserForm page, String name, String surname, String date, String phone) {
        String actualName = page.getName();
        String actualSurname = page.getSurname();
        String actualDate = page.getDate();
        String actualPhone = page.getPhone();
        Assert.assertEquals(actualName, name, actualName + " is not equal " + name);
        Assert.assertEquals(actualSurname, surname, actualSurname + " is not equal " + surname);
        Assert.assertEquals(actualDate, date, actualDate + " is not equal " + date);
        Assert.assertEquals(actualPhone, phone, actualPhone + " is not equal " + phone);
    }

    @Epic(value = "User can edit data to the profile")
    @Feature(value = "User edited data to the profile")
    @Description(value = "User can edit data")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "User can edit data to the profile")
    public void userCanEditProfile() {

        String email = "edit.user.dataprofile@gmail.com";
        String password = "Manowar333246";
        String confirmPassword = "Manowar333246";

        String name = "Georg";
        String surname = "Man";
        String date = "1984-08-01";
        String phone = "+4915777777";
        String imageAvatar = "uploadReferences/userCanAddDate_Avatra.jpg";


        String editName = "Georgiy";
        String editSurname = "Manolov";
        String editFormattedDate = "1985-01-03";
        String editPhone = "+49157310789";
        String editImageAvatar = "uploadReferences/userCanAddEditDate_Avatar.jpg";

        String oldPassword = "Manowar333246";
        String newPassword = "Manowar33246";
        String confirmNewPassword = "Manowar33246";

        String emailLogin = "g.power@gmail.com";
        String passwordLogin = "GPower3333";

        String emailAccount = "edit.user.dataprofile@gmail.com";

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
        header.tabDropdownMenu(SideBarInfo.LOGIN);

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.login(email, password);

        homeBlogPage = new HomeBlogPage(app.driver);
        homeBlogPage.waitForLoading();

        header = new Header(app.driver);
        header.tabDropdownMenu(SideBarInfo.USERPROFILE);

        addUserDialog = new AddUserDialog(app.driver);
        addUserDialog.waitForLoading();
        addUserDialog.uploadImageAvatar(imageAvatar);
        addUserDialog.clickAddUserFormButton();
        addUserDialog.waitForLoading();
        addUserDialog.fillProfileForm(name, surname, GenderInfo.MALE, date, phone);
        addUserDialog.waitForLoading();
        addUserDialog.clickSaveButton();
        addUserDialog.waitForLoading();
        checkUserData(addUserDialog, name, surname, date, phone);

        header = new Header(app.driver);
        header.clickHome();
        header.tabDropdownMenu(SideBarInfo.USERPROFILE);

        editUserForm = new EditUserForm(app.driver);
        editUserForm.waitForLoading();
        editUserForm.uploadEditImageAvatar(editImageAvatar);
        editUserForm.waitForLoading();
        editUserForm.clickEditUserForm();
        editUserForm.waitForLoading();
        editUserForm.setEditProfileFormRemote(editName, editSurname, GenderInfo.MALE, editFormattedDate, editPhone);
        editUserForm.waitForLoading();
        editUserForm.saveButtonClick();
        editUserForm.waitForLoading();
        checkEditUserData(editUserForm, editName, editSurname, editFormattedDate, editPhone);

        editPasswordForm = new EditPasswordForm(app.driver);
        editPasswordForm.fillChangePasswordForm(oldPassword, newPassword, confirmNewPassword);
        editPasswordForm.clickSaveChangePasswordButton();

        header = new Header(app.driver);
        header.clickHome();
        header.tabDropdownMenu(SideBarInfo.LOGIN);

        loginPage = new LoginPage(app.driver);
        loginPage.login(email, confirmNewPassword);

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
}
