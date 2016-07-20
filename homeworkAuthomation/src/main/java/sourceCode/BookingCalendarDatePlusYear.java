package sourceCode;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	// Check In locators

	@FindBy(css = "div[data-calendar2-title = 'Check-in'] i.sb-date-picker__close")
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
	String[] monthList = new DateFormatSymbols().getMonths();

	
	public void openCheckInCalendar() {
		Browser.waitForElementToBeClickable(driver, checkInDateSelectArrow);
		Browser.moveToElementAndClick(driver, checkInDateSelectArrow);
		// checkInDateSelectArrow.click();
	}
	
	// selecting check in date
	
	// private methods for selecting check in date  
	private void selectDesiredCheckInMonth(String desiredMonth) {
		//monthToBeSelected = desiredMonth;
		String currentVisibleMonthWithYear = getCurrentMonth(months(checkInDateMonthHeader));
		// System.out.println("Before clicking " + currentVisibleMonthWithYear);
		while (!currentVisibleMonthWithYear.equals(desiredMonth)) {
			Browser.sleepForMilisecs(2000);
			Browser.waitForVisibility(driver, checkInCalendarTable);
			;
			if (getCurrentMonth(months(checkInDateMonthHeader)).equals(desiredMonth)) {
				break;
			}
			Browser.moveToElementAndClick(driver, calendarArrowCheckInFurtherMonth);

			// System.out.println("VISIBLE: " + months(checkInDateMonthHeader));
			// System.out.println("After clicking " +
			// getCurrentMonth(months(checkInDateMonthHeader)));
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

	
	
	// general privat metods
	private List<WebElement> visibleMonths(List<WebElement> monthHeaders) {
		List<WebElement> filtered = new ArrayList<>();
		for (WebElement element : monthHeaders) {
			if (element.isDisplayed())
				filtered.add(element);
		}
		// .out.println("filtered " + filtered);
		return filtered;
	}

	private List<String> months(List<WebElement> monthHeaders) {
		return visibleMonths(monthHeaders).stream().map(WebElement::getText).collect(Collectors.toList());
	}
	// end of general private metods

	private String getCurrentMonth(List<String> monthWithYearList) {
		// Browser.sleepForMilisecs(4000);
		String monthWithYear = monthWithYearList.get(0);
		// String currentMonth = monthWithYear.replaceAll("\\d|\\s", "");
		return monthWithYear;
	}

	
	// Check Out
	public void openCheckOutCalendar() {
		Browser.waitForElementToBeClickable(driver, checkOutDateSelectArrow);
		Browser.moveToElementAndClick(driver, checkOutDateSelectArrow);
		// Browser.waitForElementToBeClickable(driver,
		// calendarArrowCheckOutFurtherMonth);
	}

	public void selectDesiredCheckOutMonth(String desiredMonth) {
		monthToBeSelected = desiredMonth;
		System.out.println("Check out list size " + checkOutDateMonthHeader.size());

		String currentVisibleMonthWithYear = getCurrentMonth(months(checkOutDateMonthHeader));
		// System.out.println("Before clicking " + currentVisibleMonthWithYear);
		while (!currentVisibleMonthWithYear.equals(desiredMonth)) {
			Browser.waitForVisibility(driver, checkOutCalendarTable);
			;
			if (getCurrentMonth(months(checkOutDateMonthHeader)).equals(desiredMonth)) {
				break;
			}
			Browser.moveToElementAndClick(driver, calendarArrowCheckOutFurtherMonth);
		}
	}

	public void selectDesiredCheckOutDate(String month, String desiredDate) {
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

}
