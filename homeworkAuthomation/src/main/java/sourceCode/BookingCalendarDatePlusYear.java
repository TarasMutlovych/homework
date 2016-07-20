package sourceCode;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import libraries.Browser;
import libraries.DatePicker;

/** Class for Booking Calendar */
public class BookingCalendarDatePlusYear implements DatePicker {

	private WebDriver driver;
	private String monthToBeSelected;

	public BookingCalendarDatePlusYear(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "li.user_center_option.uc_language")
	WebElement languageChangingButton;

	@FindBy(xpath = "//span[contains(text(), 'English')]")
	WebElement englishLanguageButton;

	// Check In locators
	@FindAll({ @FindBy(css = "div[data-calendar2-title = 'Check-in'] i.sb-date-picker__close"),
			@FindBy(css = "div[data-calendar2-type='checkin'] i.sb-date-picker__close") })
	WebElement checkInDateSelectArrow;

	@FindBy(css = "div[data-calendar2-title = 'Check-in'] + div.c2-calendar div.c2-months-table div.c2-month th.c2-month-header-monthname")
	List<WebElement> checkInDateMonthHeader;

	@FindBy(css = "div[data-calendar2-title = 'Check-in'] + div.c2-calendar div.c2-months-table ")
	WebElement checkInCalendarTable;

	@FindBy(css = "div[data-calendar2-title = 'Check-in']  + div.c2-calendar div.c2-button-further span")
	WebElement calendarArrowCheckInFurtherMonth;

	// Check Out locators

	@FindBy(css = "div[data-calendar2-title = 'Check-out'] i.sb-date-picker__close")
	WebElement checkOutDateSelectArrow;

	@FindBy(css = "div[data-calendar2-title = 'Check-out'] + div.c2-calendar div.c2-months-table div.c2-month th.c2-month-header-monthname")
	List<WebElement> checkOutDateMonthHeader;

	@FindBy(css = "div[data-calendar2-title = 'Check-out'] + div.c2-calendar div.c2-months-table ")
	WebElement checkOutCalendarTable;

	@FindBy(css = "div[data-calendar2-title = 'Check-out']  + div.c2-calendar div.c2-button-further span")
	WebElement calendarArrowCheckOutFurtherMonth;

	private String month;
	private String year;
	private String day;
	private String monthPlusYear;
	String monthPlusYearInNumbers;
	String[] monthList = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };

	public void openCheckInCalendar() {
		Browser.waitForVisibility(driver, languageChangingButton);
		Browser.moveToElementAndClick(driver, languageChangingButton);

		Browser.waitForVisibility(driver, englishLanguageButton);
		Browser.moveToElementAndClick(driver, englishLanguageButton);

		Browser.waitForVisibility(driver, checkInDateSelectArrow);
		Browser.moveToElementAndClick(driver, checkInDateSelectArrow);
	}

	// selecting check in date

	// private methods for selecting check in date
	private void selectDesiredCheckInMonth(String desiredMonth) {

		String currentVisibleMonthWithYear = getCurrentMonth(months(checkInDateMonthHeader));

		while (!currentVisibleMonthWithYear.equals(desiredMonth)) {
			if (getCurrentMonth(months(checkInDateMonthHeader)).equals(desiredMonth)) {
				break;
			}
			Browser.moveToElementAndClick(driver, calendarArrowCheckInFurtherMonth);
			Browser.sleepForMilisecs(4000);
			currentVisibleMonthWithYear = getCurrentMonth(months(checkInDateMonthHeader));
		}
	}

	private void selectDesiredCheckInDate(String month, String day) {
		List<WebElement> webElementsCheckInDays = driver.findElements(By
				.xpath("//div[@data-calendar2-title = 'Check-in']/following-sibling:: div[@class = 'c2-calendar']//th[contains(text(), '"
						+ month + "')]/parent::tr/parent::thead/following-sibling::tbody//span"));
		List<String> checkInDays = new ArrayList<String>();
		for (int i = 0; i < webElementsCheckInDays.size(); i++) {
			checkInDays.add(i, webElementsCheckInDays.get(i).getText().replaceAll("\\D", ""));
		}

		for (int i = 0; i < checkInDays.size(); i++) {
			if (checkInDays.get(i).equals(day)) {
				Browser.moveToElementAndClick(driver, webElementsCheckInDays.get(i));
				break;
			}
		}
	}
	// end of private methods for selecting check in date

	public void selectCheckInDate1(String desiredDate) {
		String[] dates = desiredDate.split("/");

		for (String parsedDate : dates) {
			if (parsedDate.length() <= 2) {
				day = parsedDate;
			} else {
				monthPlusYearInNumbers = parsedDate;
			}
		}
		String[] dataToSplit = monthPlusYearInNumbers.split("-");
		for (String parsedDate : dataToSplit) {
			if (parsedDate.length() == 4) {
				year = parsedDate;
			} else {
				int i = Integer.parseInt(parsedDate);
				month = monthList[i - 1];
			}
		}

		monthPlusYear = month + " " + year;

		selectDesiredCheckInMonth(monthPlusYear);
		selectDesiredCheckInDate(monthPlusYear, day);

	}

	// general private metods
	private List<WebElement> visibleMonths(List<WebElement> monthHeaders) {
		List<WebElement> filtered = new ArrayList<>();
		for (WebElement element : monthHeaders) {
			if (element.isDisplayed())
				filtered.add(element);
		}
		return filtered;
	}

	private List<String> months(List<WebElement> monthHeaders) {
		return visibleMonths(monthHeaders).stream().map(WebElement::getText).collect(Collectors.toList());
	}
	// end of general private metods

	private String getCurrentMonth(List<String> monthWithYearList) {

		String monthWithYear = monthWithYearList.get(0);
		return monthWithYear;
	}

	// Check Out
	public void openCheckOutCalendar() {
		Browser.waitForElementToBeClickable(driver, checkOutDateSelectArrow);
		Browser.moveToElementAndClick(driver, checkOutDateSelectArrow);
	}

	private void selectDesiredCheckOutMonth(String desiredMonth) {
		monthToBeSelected = desiredMonth;
	
		String currentVisibleMonthWithYear = getCurrentMonth(months(checkOutDateMonthHeader));
		while (!currentVisibleMonthWithYear.equals(desiredMonth)) {
			Browser.waitForVisibility(driver, checkOutCalendarTable);
			;
			if (getCurrentMonth(months(checkOutDateMonthHeader)).equals(desiredMonth)) {
				break;
			}
			Browser.moveToElementAndClick(driver, calendarArrowCheckOutFurtherMonth);
		}
	}

	private void selectDesiredCheckOutDate(String month, String desiredDate) {
		List<WebElement> webElementsCheckInDays = driver.findElements(By
				.xpath("//div[@data-calendar2-title = 'Check-out']/following-sibling:: div[@class = 'c2-calendar']//th[contains(text(), '"
						+ month + "')]/parent::tr/parent::thead/following-sibling::tbody//span"));
		List<String> checkOutDays = new ArrayList<String>();
		for (int i = 0; i < webElementsCheckInDays.size(); i++) {
			checkOutDays.add(i, webElementsCheckInDays.get(i).getText().replaceAll("\\D", ""));
		}

		for (int i = 0; i < checkOutDays.size(); i++) {
			if (checkOutDays.get(i).equals(desiredDate)) {
				Browser.moveToElementAndClick(driver, webElementsCheckInDays.get(i));
				break;
			}
		}
	}

	public void selectDesiredCheckOutDate1(String desiredDate) {
		String[] dates = desiredDate.split("/");

		for (String parsedDate : dates) {
			if (parsedDate.length() <= 2) {
				day = parsedDate;
			} else {
				monthPlusYearInNumbers = parsedDate;
			}
		}
		String[] dataToSplit = monthPlusYearInNumbers.split("-");
		for (String parsedDate : dataToSplit) {
			if (parsedDate.length() == 4) {
				year = parsedDate;
			} else {
				int i = Integer.parseInt(parsedDate);
				month = monthList[i - 1];
			}
		}

		monthPlusYear = month + " " + year;

		selectDesiredCheckOutMonth(monthPlusYear);
		selectDesiredCheckOutDate(monthPlusYear, day);

	}

}
