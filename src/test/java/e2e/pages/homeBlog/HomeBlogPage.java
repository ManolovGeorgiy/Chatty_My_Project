package e2e.pages.homeBlog;


import e2e.enums.MenuInfo;
import e2e.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeBlogPage extends BasePage {

    @FindBy(xpath = "//*[@class='sidebar__section']")
    WebElement sectionSidebar;
    @FindBy(xpath = "//*[@class='menu']")
    WebElement menu;

    public HomeBlogPage(WebDriver driver) {
        super(driver);
    }

    @Step("Wait for loading HomeBlog page")
    public void waitForLoading() {
        try {
            getWait().forVisibility(sectionSidebar);
            getWait().forVisibility(menu);
        } catch (StaleElementReferenceException e) {
        }
    }

    public void tabMenu(MenuInfo tab) {
        WebElement option = driver.findElement(By.xpath("//a[@href='" + tab.value + "']"));
        menu.click();
        getWait().forVisibility(option);
        option.click();
    }

    public void clickHomeBlogButton() {
        sectionSidebar.sendKeys();
        //homeBlogButton.click();
    }

    public void clickDraftButton() {
        sectionSidebar.sendKeys();
        //draftButton.click();
    }

}

