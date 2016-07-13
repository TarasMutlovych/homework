package homeworkAuthomation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import libraries.Browser;
import sourceCode.BookingCalendar;
import sourceCode.BookingStartPage;

public class TestCalendarClass {
	
	
	private WebDriver browser;
	
	BookingStartPage bookingStartPage;
	
	private String theSiteURL = "http://www.booking.com/";
	private String hotelName = "Hotel Montenegro";

	//Make sure that date format is "dd/year-mm"
	private String checInDate = "22/2016-6";
	private String checkOutDate = "30/2016-6";

	@BeforeClass
	public void setUp() {
		//browser = Browser.openFirefox();
		browser = Browser.openChrome();
		bookingStartPage = BookingStartPage.openStartPage(browser, theSiteURL);
	}
	
	@Test
	public void test () {
		bookingStartPage.openCheckInCalendar();
		bookingStartPage.selectDesiredMonth("January 2017");
		bookingStartPage.selectDesiredDate("January 2017", "22");
		
		bookingStartPage.openCheckOutCalendar();
		bookingStartPage.selectDesiredCheckOutMonth("February 2017");
	}
}
