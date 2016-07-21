package homeworkAuthomation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import libraries.Browser;
import sourceCode.BookingStartPage;
import sourceCode.HotelPage;
import sourceCode.ResultsPage;

public class MontenegroHotel {

	private WebDriver browser;

	BookingStartPage bookingStartPage;
	ResultsPage resultsPage;
	HotelPage hotelPage;

	private String theSiteURL = "http://www.booking.com/";
	private String hotelName = "Hotel Montenegro";
	// private String hotelName = "Reikartz Pochayiv";

	// Make sure that date format is "dd/year-mm"
	private String checInDate = "30/2016-10";
	private String checkOutDate = "7/2016-11";

	@BeforeClass
	public void setUp() {
		// browser = Browser.openFirefox();
		browser = Browser.openChrome();
		bookingStartPage = BookingStartPage.openStartPage(browser, theSiteURL);
		bookingStartPage.setEnglishLanguage();
	}

	@Test
	public void searchForHotel() {
		bookingStartPage.enterHotelName(hotelName);
		bookingStartPage.openCalendar();
		bookingStartPage.selectCheckInDate1(checInDate);

		bookingStartPage.openCheckOutCalendar1();
		bookingStartPage.selectDesiredCheckOutDate1(checkOutDate);
		resultsPage = bookingStartPage.searchForTheHotel();
	}

	@Test(dependsOnMethods = "searchForHotel")
	public void checkThatHotelPresentsInResultList() {
		resultsPage.checkThePresenceOfHotel(hotelName);
	}

	// Verifying free WiFi and Parking presence
	@Test(dependsOnMethods = "checkThatHotelPresentsInResultList")
	public void openHotelMontenegroPage() {
		hotelPage = resultsPage.clickShowPropertiesButton();
	}

	@Test(dependsOnMethods = "openHotelMontenegroPage")
	public void checkFreeWiFi() {
		hotelPage.checkPresenceOfFreeWiFiBenefit();
	}

	@Test(dependsOnMethods = "openHotelMontenegroPage")
	public void checkFreeParking() {
		hotelPage.checkPresenceOfFreeParkingBenefit();
	}

	@AfterClass
	public void tearDown() {
		Browser.close();
	}

}
