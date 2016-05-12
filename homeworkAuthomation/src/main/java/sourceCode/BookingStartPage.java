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

	@FindBy(css = "ul.c-autocomplete__list.sb-autocomplete__list.-visible li[data-i='0']")
	WebElement HotelInAutocompilelist;

	@FindAll({

			// form #1 with separate fields for days and month
			@FindBy(name = "checkin_monthday"), @FindBy(name = "checkin_year_month"),
			@FindBy(name = "checkout_monthday"), @FindBy(name = "checkout_year_month"),

			// form #2 with one field for day and moth
			@FindBy(css = "div.sb-calendar__dates.sb-searchbox__clearfix.-interactive") })
	List<WebElement> dateInputFields;

	@FindBys({
		@FindBy(css = ""),
		@FindBy(css = "i.sb-calendar__close.bicon-downchevron")
	})
	WebElement checkInCalendarExpandingArrowForm2;
	
	@FindBys({
		@FindBy(css = ""),
		@FindBy(css = "i.sb-calendar__close.bicon-downchevron")
	})
	WebElement checkOutCalendarExpandingArrowForm2;
	
	//div[contains(text(), 'Check-in date')][@class = 'sb-calendar__label']/following-sibling::div
	@FindBy(name = "checkin_monthday")
	WebElement droplistMonthDay;

	@FindBy(name = "checkin_year_month")
	WebElement droplistMonthYear;

	@FindBy(name = "checkout_monthday")
	WebElement droplistMonthDayCheckOut;

	@FindBy(name = "checkout_year_month")
	WebElement droplistMonthYearCheckOut;

	@FindBy(css = "div.sb-calendar__dates.sb-searchbox__clearfix.-interactive.-enabled.-outside")
	List <WebElement> openCalendarForm2;
	
	@FindBy(css = "td.c2-day.c2-day-s-in-range.c2-day-s-first-in-range")
	List <WebElement> checkIn;
	
	@FindBy(css = "div[class = 'c2-calendar-viewport'][style = 'height: 198px;'] div[data-id = 'M1464739200000'] tbody td[data-id = '1466553600000']")
	WebElement checkInDateForm2;
	

	@FindBy(css = "div[class = 'c2-calendar-viewport'][style = 'height: 198px;'] div[data-id = 'M1464739200000'] tbody td[data-id = '1467244800000']")
	WebElement checkOutDateForm2;
	//@FindBy(xpath = "//input[@name = 'checkin_monthday']")
	//WebElement checkin_monthdayForm2;
	
	//@FindBy(xpath = "//input[@name = 'checkin_year_month']")
	//WebElement checkin_year_monthForm2;
	
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
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
		System.out.println(dateInputs);

		if (dateInputs == 4) {
			Select droplistDay = new Select(droplistMonthDay);
			droplistDay.selectByValue(monthDay);
			Select droplistMonth = new Select(droplistMonthYear);
			droplistMonth.selectByValue(monthYear);
			monthDay = null;
			monthYear = null;
		} else {
			//Browser.waitForVisibility(driver, checkin_monthdayForm2);
			//checkInCalendarExpandingArrowForm2.click();
			//checkin_monthdayForm2.clear();
			//checkin_monthdayForm2.sendKeys("11");
			//checkin_year_monthForm2.clear();
			//checkin_year_monthForm2.sendKeys("2016-7");
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			WebElement checkInCalendar = openCalendarForm2.get(0);
			Actions actions = new Actions(driver);
			actions.moveToElement(checkInCalendar).click().build().perform();
			
			//System.out.println("use another locators");
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			Actions actions1 = new Actions(driver);
			actions1.moveToElement(checkInDateForm2).click().build().perform();
			
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
		System.out.println(dateInputs);

		if (dateInputs == 4) {
			Select droplistDay = new Select(droplistMonthDayCheckOut);
			droplistDay.selectByValue(monthDay);
			Select droplistMonth = new Select(droplistMonthYearCheckOut);
			droplistMonth.selectByValue(monthYear);
			monthDay = null;
			monthYear = null;
		} else {
			//Browser.waitForVisibility(driver, checkOutCalendarExpandingArrowForm2);
			//checkOutCalendarExpandingArrowForm2.click();
			WebElement checkInCalendar = openCalendarForm2.get(1);
			
			Actions actions = new Actions(driver);
			actions.moveToElement(checkInCalendar).click().build().perform();
			
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			Actions actions1 = new Actions(driver);
			actions1.moveToElement(checkOutDateForm2).click().build().perform();
		}

	}

	@Step
	public ResultsPage searchForTheHotel() {
		searchForm.submit();
		return new ResultsPage(driver);
	}

}
