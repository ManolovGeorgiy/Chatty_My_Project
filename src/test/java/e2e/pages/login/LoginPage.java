package e2e.pages.login;

import e2e.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//p[@class='link']/a[text()='Sign up']")
    WebElement signUpLink;
    @FindBy(xpath = "//*[@name='email']")
    WebElement emailInput;
    @FindBy(xpath = "//*[@class='input-password']")
    WebElement passwordInput;
    @FindBy(xpath = "//*[@class='registration-btn']")
    WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Login page")
    public void waitForLoading() {
        try {
            getWait().forVisibility(signUpLink);
            getWait().forVisibility(emailInput);
            Assert.assertTrue(emailInput.isDisplayed());
            getWait().forVisibility(passwordInput);
            Assert.assertTrue(passwordInput.isDisplayed());
            getWait().forVisibility(loginButton);
        } catch (StaleElementReferenceException e) {
            driver.navigate().refresh();

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

    @Step("Login as user: {email}, {password}")
    public void login(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();

    }

    @Step("Open Registration page {}")
    public void signUp() {
        try {
            signUpLink.click();
        } catch (StaleElementReferenceException e) {
            driver.navigate().refresh();
            signUp();
        }
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
}
