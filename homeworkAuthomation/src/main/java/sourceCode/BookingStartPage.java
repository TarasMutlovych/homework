package sourceCode;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import libraries.Browser;
import ru.yandex.qatools.allure.annotations.Step;

public class BookingStartPage {

	private static WebDriver driver;

	public BookingStartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private BookingCalendar bookingCalendar;

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

	@FindBy(css = "ul.c-autocomplete__list.sb-autocomplete__list.-visible li[data-i='0']")
	WebElement HotelInAutocompilelist;

	@FindBy(css = "")
	private List<WebElement> months;

	@FindAll({

			// form #1 with separate fields for days and month
			@FindBy(name = "checkin_monthday"), @FindBy(name = "checkin_year_month"),
			@FindBy(name = "checkout_monthday"), @FindBy(name = "checkout_year_month"),

			// form #2 with one field for day and moth
			@FindBy(css = "div.sb-calendar__dates.sb-searchbox__clearfix.-interactive") })
	List<WebElement> dateInputFields;

	@FindBy(name = "checkin_monthday")
	WebElement droplistMonthDay;

	@FindBy(name = "checkin_year_month")
	WebElement droplistMonthYear;

	@FindBy(name = "checkout_monthday")
	WebElement droplistMonthDayCheckOut;

	@FindBy(name = "checkout_year_month")
	WebElement droplistMonthYearCheckOut;

	@FindBy(css = "div.sb-calendar__dates.sb-searchbox__clearfix.-interactive.-enabled.-outside")
	List<WebElement> openCalendarForm2;

	@FindBys({ @FindBy(css = "div.c2-wrapper-s-checkin div[data-id = 'M1464739200000']"),
			@FindBy(css = "td[data-id = '1466553600000']") })
	WebElement checkInDateForm2;

	@FindBys({ @FindBy(css = "div.c2-wrapper-s-checkout div[data-id = 'M1464739200000']"),
			@FindBy(css = "td[data-id = '1467244800000'") })
	WebElement checkOutDateForm2;

	@FindBy(id = "frm")
	WebElement searchForm;

	@FindBy(css = "ul.b-popular_list.lp_endorsements_popular_destinations_container + p > a")
	WebElement moreDestinatoins;

	public static BookingStartPage openStartPage(WebDriver browser, String theSiteURL) {
		browser.get(theSiteURL);
		return new BookingStartPage(browser);
	}

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
		Browser.sleepForMilisecs(2000);
		Actions actions = new Actions(driver);
		actions.moveToElement(HotelInAutocompilelist).click().build().perform();

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

		int dateInputs = dateInputFields.size();

		if (dateInputs == 4) {
			Select droplistDay = new Select(droplistMonthDay);
			droplistDay.selectByValue(monthDay);
			Select droplistMonth = new Select(droplistMonthYear);
			droplistMonth.selectByValue(monthYear);
			monthDay = null;
			monthYear = null;
		} else {
			Browser.sleepForMilisecs(2000);
			WebElement checkInCalendar = openCalendarForm2.get(0);
			Browser.moveToElementAndClick(driver, checkInCalendar);

			Browser.sleepForMilisecs(2000);
			Browser.moveToElementAndClick(driver, checkInDateForm2);
		}
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

		int dateInputs = dateInputFields.size();

		if (dateInputs == 4) {
			Select droplistDay = new Select(droplistMonthDayCheckOut);
			droplistDay.selectByValue(monthDay);
			Select droplistMonth = new Select(droplistMonthYearCheckOut);
			droplistMonth.selectByValue(monthYear);
			monthDay = null;
			monthYear = null;
		} else {
			WebElement checkOutCalendar = openCalendarForm2.get(1);
			Browser.moveToElementAndClick(driver, checkOutCalendar);

			Browser.sleepForMilisecs(2000);
			Browser.moveToElementAndClick(driver, checkOutDateForm2);
		}

	}

	@Step
	public ResultsPage searchForTheHotel() {
		searchForm.submit();
		return new ResultsPage(driver);
	}

	// Test 5 - Working with Excel file
	@Step
	public DestinationsPage clickMoreDestinatons() {
		Browser.waitForVisibility(driver, moreDestinatoins);
		moreDestinatoins.click();
		return new DestinationsPage(driver);
	}

	// Calendar

	public void openCheckInCalendar() {
		bookingCalendar = new BookingCalendar(driver);
		bookingCalendar.openCheckInCalendar();
	}

	public void selectDesiredMonth(String desiredMonth) {
		bookingCalendar = new BookingCalendar(driver);
		bookingCalendar.selectDesiredMonth(desiredMonth);
	}

	public void selectDesiredDate(String month, String desiredDate) {
		bookingCalendar = new BookingCalendar(driver);
		bookingCalendar.selectDesiredDate(month, desiredDate);
		;
	}

	public void openCheckOutCalendar() {
		bookingCalendar = new BookingCalendar(driver);
		bookingCalendar.openCheckOutCalendar();
	}

}