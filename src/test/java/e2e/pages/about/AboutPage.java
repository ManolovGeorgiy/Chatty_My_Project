package e2e.pages.about;

import e2e.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AboutPage extends BasePage {
    @FindBy(xpath = "//div[@class='about-box']")
    WebElement aboutBox;

    public AboutPage(WebDriver driver) {
        super(driver);
    }

    @Step("Wait for loading open About")
    public void waitForLoading() {
        try {
            getWait().forVisibility(aboutBox);
        } catch (StaleElementReferenceException e) {
        }
    }

    public String getAboutText() {
        return aboutBox.getText();
    }
}
