package homeworkAuthomation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import libraries.Browser;
import libraries.DatePicker;
import sourceCode.BookingStartPage;

public class TestCalendarClass {
	
	
	private WebDriver browser;
	
	
	BookingStartPage bookingStartPage;
	//BookingCalendarPicker calendarPicker;
	
	
	//private DatePicker calendarDatePicker;
	
	private String theSiteURL = "http://www.booking.com/";
	private String hotelName = "Hotel Montenegro";

	//Make sure that date format is "dd/year-mm"
	private String checInDate = "31/2016-12";
	private String checkOutDate = "1/2017-02";

	@BeforeClass
	public void setUp() {
		//browser = Browser.openFirefox();
		browser = Browser.openChrome();
		bookingStartPage = BookingStartPage.openStartPage(browser, theSiteURL);
	}
	
	@Test
	public void test () {
		bookingStartPage.openCalendar();
		bookingStartPage.selectCheckInDate1(checInDate);
		
		bookingStartPage.openCheckOutCalendar1();
		bookingStartPage.selectDesiredCheckOutDate1(checkOutDate);
			}
}
