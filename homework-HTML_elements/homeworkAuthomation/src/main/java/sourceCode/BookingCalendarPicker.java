package sourceCode;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import libraries.Browser;

import org.openqa.selenium.support.PageFactory;

public class BookingCalendarPicker {

	
	private static BookingCalendarPicker correctDatePickerInstance = null;
	
	@FindBy (css = "div.sb-dates__grid")
	static
	WebElement caledarForm;
	
	@FindAll({

		// form #1 with separate fields for days and month
		@FindBy(name = "checkin_monthday"), @FindBy(name = "checkin_year_month"),
		@FindBy(name = "checkout_monthday"), @FindBy(name = "checkout_year_month"),

		// form #2 with one field for day and moth
		@FindBy(css = "div[data-calendar2-type='checkin'][data-sb-id='main']"),
		@FindBy(css = "div[data-calendar2-type='checkout'][data-sb-id='main']")})
	static
List<WebElement> dateInputFields;
	
	public static BookingCalendarPicker getInstanse (WebDriver driver) {
		BookingCalendarPicker bookingCalendarPicker = PageFactory.initElements(driver, BookingCalendarPicker.class);
		
		Browser.waitForVisibility(driver, caledarForm);
		
		int dateInputs = dateInputFields.size();

		if (dateInputs == 4) {
			System.out.println("4");
		} else {
			//correctDatePickerInstance = new BookingCalendarDatePlusYear (driver);
			System.out.println("option 2");
		}

		
		return correctDatePickerInstance;
		
	}
	
	public void openCheckInCalendar() { }
	
}
