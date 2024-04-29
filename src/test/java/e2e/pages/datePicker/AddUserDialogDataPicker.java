package e2e.pages.datePicker;

import e2e.enums.GenderInfo;
import e2e.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class AddUserDialogDataPicker extends BasePage {
    @FindBy(xpath = "//*[@data-test='post-header__plus']")
    WebElement editButton;
    @FindBy(xpath = "//*[@data-test='profileName']")
    WebElement nameInput;
    @FindBy(xpath = "//*[@name='surname']")
    WebElement surnameInput;
    @FindBy(xpath = "//select[@id='gender']")
    WebElement gender;
    @FindBy(xpath = "//*[@id='birthDate']")
    WebElement birthDateClick;
    @FindBy(xpath = "//*[@name='phone']")
    WebElement phoneInput;
    @FindBy(xpath = "//*[@data-test='profileSaveButton']")
    WebElement saveButton;
    @FindBy(xpath = "//*[@class='header']")
    WebElement headerElement;

    public AddUserDialogDataPicker(WebDriver driver) {
        super(driver);
    }

    @Step("Wait for loading Edit profile page")
    public void waitForLoading() {
        try {
            getWait().forVisibility(editButton);
            getWait().forVisibility(nameInput);
            getWait().forVisibility(surnameInput);
            getWait().forVisibility(gender);
            getWait().forVisibility(birthDateClick);
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
        getWait().forAttributeNotEmpty(birthDateClick);
        return birthDateClick.getAttribute("value");
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
    public void fillProfileFormLocal(String name, String surname, GenderInfo tab, String phone) {
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
        phoneInput.sendKeys(phone);
    }

    @Step("click save button")
    public void clickDatePickerButton() {
        birthDateClick.click();
        WebElement currentDate = null;
        currentDate.isDisplayed();
        WebElement body = null;
        body.isDisplayed();

        String[] currentMothAndYear = currentDate.getText().split(" ");
        int currentMonth = Month.valueOf(currentMothAndYear[0].toUpperCase()).getValue();
        int currentYear = Integer.parseInt(currentMothAndYear[1]);
        LocalDate date = null;
        int monthDiff = (date.getYear() - currentYear) * 12 + (date.getMonthValue() - currentMonth);
        for (int i = 0; i < Math.abs(monthDiff); i++) {
            if (monthDiff > 0) {
                WebElement rightArrow = null;
                rightArrow.click();
            } else {
                WebElement leftArrow = null;
                leftArrow.click();
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd", Locale.ENGLISH);
        String formattedDate = date.format(formatter);
        WebElement dayCell = driver.findElement(By.xpath("//*[input(@aria-label, '" + formattedDate + "')]"));
        currentDate.getText().equals(date.getMonth().name() + " " + date.getYear());
        dayCell.click();
        body.isDisplayed();
    }


    @Step("click save button")
    public void clickSaveButton() {
        saveButton.click();
    }
}

