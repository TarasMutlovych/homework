package homeworkAuthomation;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import libraries.Browser;
import sourceCode.BookingStartPage;
import sourceCode.ResultsPage;

public class MontenegroHotel {

	BookingStartPage bookingStartPage;
	ResultsPage resultsPage;
	
	private String theSiteURL = "http://www.booking.com/";
	private String hotelName = "Hotel Montenegro";

	//Make sure that date format is "dd/year-mm"
	private String checInDate = "22/2016-6";
	private String checkOutDate = "19/2016-7";
 

	@BeforeClass
	public void setUp() {
		Browser.openFirefox();
		//Browser.openChrome();
		bookingStartPage = Browser.openStartPage(theSiteURL);
	}
	
	@Test
	public void serchForHotel() {
		bookingStartPage.enterHotelName(hotelName);
		bookingStartPage.selectCheckInDate(checInDate);
		bookingStartPage.selectCheckOutDate(checkOutDate);
		resultsPage  = bookingStartPage.searchForTheHotel();
	}
	
	@Test(dependsOnMethods = "serchForHotel") 
	public void checkThatHotelPrsentsInResultList() {
		resultsPage.checkThePresenceOfHotel(hotelName);
	}
	
	@AfterClass
	public void tearDown() {
		Browser.close();
	}
	
}
