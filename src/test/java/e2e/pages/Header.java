package e2e.pages;

import e2e.enums.SideBarInfo;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class Header extends BasePage {
    @FindBy(xpath = "//*[@class='header']")
    WebElement headerElement;
    @FindBy(xpath = "//*[@class='header__nav header__menu']")
    WebElement headerMenu;
    @FindBy(xpath = "//*[@class='header__nav-list']")
    WebElement headerList;
    @FindBy(xpath = "//*[@class='header__logo']")
    WebElement logo;
    @FindBy(xpath = "//a[@href='/homeblog' and text()='Home']")
    WebElement homeButton;
    @FindBy(xpath = "//a[@href='/about' and text()='About']")
    WebElement aboutButton;
    @FindBy(xpath = "//a[@href='/contact' and text()='Contact']")
    WebElement contactButton;
    @FindBy(xpath = "//*[@class='header__user header__menu']")
    WebElement dropdownMenu;
    @FindBy(xpath = "//*[@class='post-header__left']")
    WebElement myPostClickButton;
    @FindBy(xpath = "//*[@class='post-content']")
    WebElement myPostTab;
    @FindBy(xpath = "//*[@data-test='post-header__plus']")
    WebElement createAPostButton;

    public Header(WebDriver driver) {
        super(driver);

    }

    @Step("Wait for loading Header")
    public void waitForLoading() {
        try {
            getWait().forVisibility(headerElement);
            getWait().forVisibility(headerMenu);
            getWait().forVisibility(headerList);
            Assert.assertTrue(headerList.isDisplayed());
            getWait().forVisibility(logo);
            getWait().forVisibility(homeButton);
            getWait().forVisibility(aboutButton);
            getWait().forVisibility(contactButton);
            getWait().forClickable(dropdownMenu);
            getWait().forClickable(myPostClickButton);
            getWait().forVisibility(myPostTab);
            getWait().forClickable(createAPostButton);


        } catch (StaleElementReferenceException e) {
        }
    }

    public void clickLogo() {
        logo.click();
    }

    public void clickHome() {
        homeButton.click();
    }

    public void clickAbout() {
        aboutButton.click();
    }

    public void clickContact() {
        contactButton.click();
    }

    public void tabDropdownMenu(SideBarInfo tab) {
        WebElement option = driver.findElement(By.xpath("//a[@href='" + tab.value + "']"));
        dropdownMenu.click();
        getWait().forVisibility(option);
        option.click();
    }

    public void myPostClick() {
        myPostClickButton.click();
    }

    public void setMyPostTab() {
        myPostTab.click();
    }

    public void createAPostClick() {
        createAPostButton.click();
    }

}

