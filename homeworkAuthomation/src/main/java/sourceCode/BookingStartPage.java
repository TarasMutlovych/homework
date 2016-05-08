package sourceCode;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import libraries.Browser;
import ru.yandex.qatools.allure.annotations.Step;

public class BookingStartPage extends StartPage {

	public BookingStartPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//li[@id = 'current_account'][position() = 2]//div")
	WebElement signInButtonOnTheStartScreen;

	@FindBy(className = "user_access_email")
	WebElement emailAddressField;

	@FindBy(className = "user_access_password")
	WebElement passwordField;

	@FindBy(className = "user_access_form")
	WebElement signInForm;

	@FindBy(id = "ss")
	WebElement searchInputField;

	@FindBy(name = "checkin_monthday")
	WebElement droplistMonthDay;

	@FindBy(name = "checkin_year_month")
	WebElement droplistMonthYear;

	@FindBy(name = "checkout_monthday")
	WebElement droplistMonthDayCheckOut;

	@FindBy(name = "checkout_year_month")
	WebElement droplistMonthYearCheckOut;

	@FindBy(id = "frm")
	WebElement searchForm;

	// User First And Last Name Verification
	private void expandLoginPopUpWindow() {
		Browser.waitForVisibility(driver, signInButtonOnTheStartScreen);
		signInButtonOnTheStartScreen.click();
	}

	@Step
	public void enterLogin(String login) {
		expandLoginPopUpWindow();
		emailAddressField.sendKeys(login);
	}

	@Step
	public void enterPassword(String pass) {
		passwordField.sendKeys(pass);
	}

	@Step
	public UserProfilePage submitCredentials() {
		signInForm.submit();
		return new UserProfilePage(driver);
	}

	// Searching for Montenegro Hotel

	@Step
	public void enterHotelName(String hotelName) {
		Browser.waitForVisibility(driver, searchInputField);
		searchInputField.sendKeys(hotelName);
	}

	private String monthDay;
	private String monthYear;

	@Step
	public void selectCheckInDate(String CheckInDate) {

		String[] dates = CheckInDate.split("/");
		for (String parsedDate : dates) {
			if (parsedDate.length() <= 2) {
				monthDay = parsedDate;
			} else {
				monthYear = parsedDate;
			}
		}
		Select droplistDay = new Select(droplistMonthDay);
		droplistDay.selectByValue(monthDay);
		Select droplistMonth = new Select(droplistMonthYear);
		droplistMonth.selectByValue(monthYear);
		monthDay = null;
		monthYear = null;
	}

	@Step
	public void selectCheckOutDate(String checkOutDate) {

		String[] dates = checkOutDate.split("/");
		for (String parsedDate : dates) {
			if (parsedDate.length() <= 2) {
				monthDay = parsedDate;
			} else {
				monthYear = parsedDate;
			}
		}
		Select droplistDay = new Select(droplistMonthDayCheckOut);
		droplistDay.selectByValue(monthDay);
		Select droplistMonth = new Select(droplistMonthYearCheckOut);
		droplistMonth.selectByValue(monthYear);
		monthDay = null;
		monthYear = null;
	}

	@Step
	public ResultsPage searchForTheHotel() {
		searchForm.submit();
		return new ResultsPage(driver);
	}

}
