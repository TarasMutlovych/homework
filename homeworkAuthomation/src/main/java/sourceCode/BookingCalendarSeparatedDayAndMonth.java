package sourceCode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import libraries.DatePicker;

public class BookingCalendarSeparatedDayAndMonth implements DatePicker {

	private WebDriver driver;
	
	public BookingCalendarSeparatedDayAndMonth(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	private String monthDay;
	private String yearMonth;
	
	

	@FindBy(name = "checkin_monthday")
	WebElement droplistMonthDay;

	@FindBy(name = "checkin_year_month")
	WebElement droplistMonthYear;

	@FindBy(name = "checkout_monthday")
	WebElement droplistMonthDayCheckOut;

	@FindBy(name = "checkout_year_month")
	WebElement droplistMonthYearCheckOut;

	
	@Override
	public void openCheckInCalendar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectCheckInDate1(String checkInDate) {
		String[] dates = checkInDate.split("/");
		for (String parsedDate : dates) {
			if (parsedDate.length() <= 2) {
				monthDay = parsedDate;
			} else {
				yearMonth = parsedDate;
			}
		}
		

		Select droplistDay = new Select(droplistMonthDay);
		droplistDay.selectByValue(monthDay);
		Select droplistMonth = new Select(droplistMonthYear);
		droplistMonth.selectByValue(yearMonth);
		monthDay = null;
		yearMonth = null;
	}

	@Override
	public void openCheckOutCalendar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectDesiredCheckOutDate1(String checkOutDate) {

		String[] dates = checkOutDate.split("/");
		for (String parsedDate : dates) {
			if (parsedDate.length() <= 2) {
				monthDay = parsedDate;
			} else {
				yearMonth = parsedDate;
			}
	}

		Select droplistDay = new Select(droplistMonthDayCheckOut);
		droplistDay.selectByValue(monthDay);
		Select droplistMonth = new Select(droplistMonthYearCheckOut);
		droplistMonth.selectByValue(yearMonth);
		monthDay = null;
		yearMonth = null;

}
}
