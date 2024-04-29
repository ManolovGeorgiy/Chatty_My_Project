package e2e.pages.datePicker;

import e2e.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.w3c.dom.html.HTMLInputElement;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class DatePickerCalendar extends BasePage {

    @FindBy(xpath = "//*[@id='birthDate']")
    WebElement birthDateClick;
    public DatePickerCalendar(WebElement birthDate, WebDriver driver) {
        super(driver);
    }
    @Step("Wait for loading Edit profile page")
    public void waitForLoading() {
        try {
            getWait().forVisibility(birthDateClick);
        } catch (StaleElementReferenceException e) {
        }
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
        WebElement dayCell = driver.findElement(By.xpath("//*[contains(@aria-label, '" + formattedDate + "')]"));
        currentDate.getText().equals(date.getMonth().name() + " " + date.getYear());
        dayCell.click();
        body.isDisplayed();
    }
}
