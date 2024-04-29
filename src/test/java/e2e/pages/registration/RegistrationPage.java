package e2e.pages.registration;

import e2e.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage extends BasePage {
    @FindBy(xpath = "//select/option[@value='user']")
    WebElement userOption;
    @FindBy(xpath = "//select/option[@value='admin']")
    WebElement adminOption;
    @FindBy(xpath = "//*[@name='email']")
    WebElement emailInput;
    @FindBy(xpath = "//*[@class='input-password']")
    WebElement passwordInput;
    @FindBy(xpath = "//*[@class='password-eye']")
    WebElement eyeTab;
    @FindBy(xpath = "//*[@name='confirmPassword']")
    WebElement confirmPasswordInput;
    @FindBy(xpath = "//*[@class='registration-btn']")
    WebElement registrationButton;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Wait for loading Login page")
    public void waitForLoading() {
        try {
            getWait().forVisibility(userOption);
            getWait().forVisibility(adminOption);
            getWait().forVisibility(emailInput);
            getWait().forVisibility(passwordInput);
            getWait().forClickable(eyeTab);
            getWait().forVisibility(confirmPasswordInput);
            getWait().forVisibility(registrationButton);
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }
    }

    public void optionUser() {
        userOption.click();
    }

    public void optionAdmin() {
        adminOption.click();
    }

    @Step("Registration as user: {email}, {password}, {confirmPassword}")
    public void registration(String email, String password, String confirmPassword) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        eyeTab.click();
        confirmPasswordInput.sendKeys(confirmPassword);
        eyeTab.click();
        registrationButton.click();
    }

    @Step("check error message")
    public boolean textError() {
        Duration timeout = Duration.ofSeconds(5);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='text-error']")));
        try {
            return driver.findElement(By.xpath("//div[@class='text-error']")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Screenshot {actualScreenshotName}")
    public void takeLoginPageScreenshot(String actualScreenshotName) {
        try {
            waitForLoading();
            takeAndCompareScreenshot(actualScreenshotName, null);
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }
    }
}
