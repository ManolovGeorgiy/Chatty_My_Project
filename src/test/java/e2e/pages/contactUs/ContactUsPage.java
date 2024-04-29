package e2e.pages.contactUs;

import e2e.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactUsPage extends BasePage {
    @FindBy(xpath = "//*[@id='name']")
    WebElement nameUserInput;
    @FindBy(xpath = "//*[@id='email']")
    WebElement emailUserInput;
    @FindBy(xpath = "//*[@id='content']")
    WebElement contentUserInput;
    @FindBy(xpath = "//*[@type='submit']")
    WebElement sendMessageButton;

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    public String getUserName() {
        return nameUserInput.getAttribute("value");
    }

    public String getUserEmail() {
        return emailUserInput.getAttribute("value");
    }

    public String getUserContent() {
        return contentUserInput.getAttribute("value");
    }

    public String getNewContent() {
        return contentUserInput.getAttribute("value");
    }

    @Step("Wait for loading Contact Us")
    public void waitForLoading() {
        try {
            getWait().forVisibility(nameUserInput);
            getWait().forVisibility(emailUserInput);
            getWait().forVisibility(contentUserInput);
            getWait().forVisibility(sendMessageButton);
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }
    }

    @Step("Screenshot {actualScreenshotName}")
    public void takeFeedbackFormPageScreenshot(String actualScreenshotName) {
        try {
            waitForLoading();
            takeAndCompareScreenshot(actualScreenshotName, null);
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }
    }

    @Step("Fill out the setFeedbackForm form {name},{emailContact},{text}")
    public void setFeedbackForm(String name, String emailContact, String text) {
        nameUserInput.sendKeys(name);
        emailUserInput.sendKeys(emailContact);
        contentUserInput.sendKeys(text);
    }

    public void sendMessageButtonClick() {
        sendMessageButton.click();
    }

    @Step("check after sending")
    public boolean isMessageSent() {
        Duration timeout = Duration.ofSeconds(1);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='success-message']")));
        try {
            return driver.findElement(By.xpath("//div[@class='success-message']")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean istErrorDisplayed() {
        Duration timeout = Duration.ofSeconds(5);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='error']")));
        try {
            return driver.findElement(By.xpath("//*[@class='error']")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
