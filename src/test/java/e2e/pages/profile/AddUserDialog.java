package e2e.pages.profile;

import e2e.enums.GenderInfo;
import e2e.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddUserDialog extends BasePage {
    @FindBy(xpath = "//*[@data-test='post-header__plus']")
    WebElement editButton;
    @FindBy(xpath = "//*[@data-test='profileName']")
    WebElement nameInput;
    @FindBy(xpath = "//*[@name='surname']")
    WebElement surnameInput;
    @FindBy(xpath = "//select[@id='gender']")
    WebElement gender;
    @FindBy(xpath = "//*[@id='birthDate']")
    WebElement birthDateForm;
    @FindBy(xpath = "//*[@name='phone']")
    WebElement phoneInput;
    @FindBy(xpath = "//*[@data-test='profileSaveButton']")
    WebElement saveButton;
    @FindBy(xpath = "//*[@class='header']")
    WebElement headerElement;

    public AddUserDialog(WebDriver driver) {
        super(driver);
    }

    @Step("Wait for loading Edit profile page")
    public void waitForLoading() {
        try {
            getWait().forVisibility(editButton);
            getWait().forVisibility(nameInput);
            getWait().forVisibility(surnameInput);
            getWait().forVisibility(gender);
            getWait().forVisibility(birthDateForm);
            getWait().forVisibility(phoneInput);
            getWait().forVisibility(saveButton);
            getWait().forVisibility(headerElement);
        } catch (StaleElementReferenceException e) {
        }
    }

    @Step("click edit button profile")
    public void clickAddUserFormButton() {
        editButton.click();
    }

    public String getName() {
        getWait().forAttributeNotEmpty(nameInput);
        return nameInput.getAttribute("value");
    }

    public String getSurname() {
        getWait().forAttributeNotEmpty(surnameInput);
        return surnameInput.getAttribute("value");
    }

    public String getDate() {
        getWait().forAttributeNotEmpty(birthDateForm);
        return birthDateForm.getAttribute("value");
    }

    public String getPhone() {
        return phoneInput.getAttribute("value");
    }

    @Step("Upload image: {imagePath}")
    public void uploadImageAvatar(String relativeImagePath) {
        try {
            String absoluteImagePath = System.getProperty("user.dir") + "/" + relativeImagePath;
            WebElement fileInput = driver.findElement(By.xpath("//*[@accept='image/png,.png,image/jpg,.jpg,image/jpeg,.jpeg']"));
            fileInput.sendKeys(absoluteImagePath);
        } catch (Exception e) {
            Assert.fail("Failed to upload image: " + e.getMessage());
        }
    }

    @Step("Fill profile form {name},{surname},{date},{phone}")
    public void fillProfileForm(String name, String surname, GenderInfo tab, String date, String phone) {
        try {
            nameInput.clear();
            nameInput.sendKeys(name);
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }
        surnameInput.clear();
        surnameInput.sendKeys(surname);
        WebElement option = driver.findElement(By.xpath("//*[@value='" + tab.value + "']"));
        gender.click();
        getWait().forVisibility(option);
        option.click();
        try {
            String[] dateParts = date.split("-");
            birthDateForm.sendKeys(Keys.CONTROL, "a");
            birthDateForm.isDisplayed();
            birthDateForm.sendKeys(date);
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.TAB).perform();
            birthDateForm.sendKeys(dateParts[1]); //day
            birthDateForm.sendKeys(dateParts[2]); //month
            birthDateForm.sendKeys(dateParts[0]); //year
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }
        phoneInput.sendKeys(phone);
    }

    @Step("Set birth date")
    public void setBirthDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);
        birthDateForm.sendKeys(formattedDate);
    }

    @Step("Fill profile form {name},{surname},{date},{phone}")
    public void fillProfileFormLocal(String name, String surname, GenderInfo tab, String date, String phone) {
        try {
            nameInput.clear();
            nameInput.sendKeys(name);
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }
        surnameInput.clear();
        surnameInput.sendKeys(surname);
        WebElement option = driver.findElement(By.xpath("//*[@value='" + tab.value + "']"));
        gender.click();
        getWait().forVisibility(option);
        option.click();
        try {
            String[] dateParts = date.split("-");
            birthDateForm.sendKeys(Keys.CONTROL, "a");
            birthDateForm.isDisplayed();
            birthDateForm.sendKeys(date);
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.TAB).perform();
            birthDateForm.sendKeys(dateParts[2]); //day
            birthDateForm.sendKeys(dateParts[1]); //month
            birthDateForm.sendKeys(dateParts[0]); //year
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }
        phoneInput.sendKeys(phone);
    }

    @Step("click save button")
    public void clickSaveButton() {
        saveButton.click();
    }
}

