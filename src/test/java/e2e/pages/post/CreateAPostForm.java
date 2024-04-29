package e2e.pages.post;

import config.Config;
import e2e.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CreateAPostForm extends BasePage {
    private final Config config = new Config();
    @FindBy(xpath = "//*[@class='post-header']")
    public WebElement header;
    @FindBy(xpath = "//*[@name='title']")
    WebElement titleInput;
    @FindBy(xpath = "//*[@data-test='description-input']")
    WebElement descriptionInput;
    @FindBy(xpath = "//*[@name='content']")
    WebElement contentInput;
    @FindBy(xpath = "//*[@class='post_uploaded_image__7qSWV']")
    WebElement imageInput;
    @FindBy(xpath = "//*[@id='publishDate']")
    WebElement publishData;
    @FindBy(xpath = "//*[@for='draftCheckbox']")
    WebElement tumblerSwitchDraft;
    @FindBy(xpath = "//*[@type='submit']")
    WebElement submitButton;

    public CreateAPostForm(WebDriver driver) {
        super(driver);
    }

    @Step("Wait for loading Create a post")
    public void waitForLoading() {
        try {
            getWait().forVisibility(header);
            getWait().forVisibility(titleInput);
            getWait().forVisibility(descriptionInput);
            getWait().forVisibility(contentInput);
            getWait().forVisibility(imageInput);
            getWait().forVisibility(publishData);
            getWait().forClickable(tumblerSwitchDraft);
            getWait().forVisibility(submitButton);
            Assert.assertTrue(submitButton.isDisplayed());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Step("Fill form {title},{description},{content},{path}")
    public void setPostForm(String title, String description, String content, String path) {
        titleInput.sendKeys(title);
        descriptionInput.sendKeys(description);
        contentInput.sendKeys(content);
        if (
                path != null
        ) {
            uploadImage(path);
        }
    }

    public String getTitle() {
        return titleInput.getAttribute("value");
    }

    public String getDescriptionText() {
        return descriptionInput.getAttribute("value");
    }

    public String getContent() {
        return contentInput.getAttribute("value");
    }

    public void clickToDraftCheckBox() {
        tumblerSwitchDraft.click();
    }

    @Step("Upload image: {relativeImagePath}")
    public void uploadImage(String relativeImagePath) {
        try {
            String absoluteImagePath = System.getProperty("user.dir") + "/" + relativeImagePath;
            WebElement fileInput = driver.findElement(By.xpath("//*[@accept='image/png,.png,image/jpg,.jpg,image/jpeg,.jpeg']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'block';", fileInput);
            fileInput.sendKeys(absoluteImagePath);
            Thread.sleep(5000);
        } catch (Exception e) {
            Assert.fail("Failed to upload image: " + e.getMessage());
        }
    }

    @Step("Click Submit Button")
    public void clickSubmitButton() {
        submitButton.click();
    }

    @Step("check after sending")
    public boolean errorText() {
        Duration timeout = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='error']")));
        try {
            return driver.findElement(By.xpath("//*[@class='error']")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Screenshot {actualScreenshotName}")
    public void takePostPageScreenshot(String actualScreenshotName) {
        try {
            waitForLoading();
            takeAndCompareScreenshot(actualScreenshotName, null);
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }
    }

    @Step("Fill form {title},{description},{content}")
    public void userCanNotCreateAPost(String title, String description, String content) {
        titleInput.sendKeys(title);
        descriptionInput.sendKeys(description);
        contentInput.sendKeys(content);
        submitButton.click();
    }

    public boolean isPostDisplayed(String postTitle) {
        try {
            WebElement postElement = driver.findElement(By.xpath("//*[@class='post-content__top' and .//h3[text()='" + postTitle + "']]"));
            ;
            return postElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}

