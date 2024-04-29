package e2e.pages.profile;

import e2e.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class EditPasswordForm extends BasePage {
    @FindBy(xpath = "//*[@data-test='profileChangePasswordButton']")
    WebElement changePasswordButton;
    @FindBy(xpath = "//*[@placeholder='Old password']")
    WebElement oldPasswordInput;
    @FindBy(xpath = "//*[@placeholder='New password']")
    WebElement newPasswordInput;
    @FindBy(xpath = "//*[@placeholder='Confirm new password']")
    WebElement confirmNewPasswordInput;
    @FindBy(xpath = "//*[@class='PasswordModal_pass_btn__eGL9h']")
    WebElement saveChangeButton;

    public EditPasswordForm(WebDriver driver) {
        super(driver);
    }

    @Step("Wait for loading change password")
    public void waitForLoading() {
        try {
            getWait().forVisibility(changePasswordButton);
            getWait().forVisibility(oldPasswordInput);
            Assert.assertTrue(oldPasswordInput.isDisplayed());
            getWait().forVisibility(newPasswordInput);
            Assert.assertTrue(newPasswordInput.isDisplayed());
            getWait().forVisibility(confirmNewPasswordInput);
            Assert.assertTrue(confirmNewPasswordInput.isDisplayed());
            getWait().forVisibility(saveChangeButton);
        } catch (StaleElementReferenceException e) {
        }
    }

    @Step("Fill out password forms")
    public void fillChangePasswordForm(String oldPassword, String newPassword, String confirmNewPassword) {
        changePasswordButton.click();
        oldPasswordInput.sendKeys(oldPassword);
        newPasswordInput.sendKeys(newPassword);
        confirmNewPasswordInput.sendKeys(confirmNewPassword);
    }

    @Step("click change password button")
    public void clickSaveChangePasswordButton() {
        saveChangeButton.click();
        getWait().forClickable(changePasswordButton);
    }

    public boolean isPasswordChanged() {
        return !saveChangeButton.isEnabled();
    }
}
