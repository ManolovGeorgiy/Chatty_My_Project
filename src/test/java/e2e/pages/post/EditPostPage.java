package e2e.pages.post;

import e2e.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends BasePage {
    @FindBy(xpath = "//*[@data-test='edit-button']")
    WebElement editPostButton;
    @FindBy(xpath = "//*[@data-test='delete-button']")
    WebElement deletePostButton;

    public EditPostPage(WebDriver driver) {
        super(driver);
    }

    @Step("Wait for loading edit a post")
    public void waitForLoading() {
        try {
            getWait().forClickable(editPostButton);
            getWait().forClickable(deletePostButton);
        } catch (StaleElementReferenceException e) {
        }
    }

    public void editPostButtonClick() {
        editPostButton.click();
    }

    public void deletePostButtonClick() {
        deletePostButton.click();
    }
}
