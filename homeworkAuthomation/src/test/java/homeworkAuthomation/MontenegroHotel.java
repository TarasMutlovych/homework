package homeworkAuthomation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import libraries.Browser;
import sourceCode.BookingStartPage;
import sourceCode.HotelMontenegroPage;
import sourceCode.PropertiesListPage;
import sourceCode.ResultsPage;

public class MontenegroHotel {

	private WebDriver browser;
	
	BookingStartPage bookingStartPage;
	ResultsPage resultsPage;
	PropertiesListPage propertiesListPage;
	HotelMontenegroPage hotelMontenegroPage;

	
	private String theSiteURL = "http://www.booking.com/";
	private String hotelName = "Hotel Montenegro";

	//Make sure that date format is "dd/year-mm"
	private String checInDate = "2017-1";
	private String checkOutDate = "30/2016-6";
 

	@BeforeClass
	public void setUp() {
		//browser = Browser.openFirefox();
		browser = Browser.openChrome();
		bookingStartPage = BookingStartPage.openStartPage(browser, theSiteURL);
	}
	
	@Test
	public void searchForHotel() {
		//bookingStartPage.enterHotelName(hotelName);
		bookingStartPage.selectCheckInDate(checInDate);
		//bookingStartPage.selectCheckOutDate(checkOutDate);
		//resultsPage  = bookingStartPage.searchForTheHotel();
	}
	/*
	@Test(dependsOnMethods = "searchForHotel") 
	public void checkThatHotelPresentsInResultList() {
		resultsPage.checkThePresenceOfHotel(hotelName);
	}
	
	//Verifying free WiFi and Parking presence
	@Test(dependsOnMethods = "checkThatHotelPresentsInResultList")
	public void openHotelMontenegroPage() {
		propertiesListPage = resultsPage.clickShowPropertiesButton();
		hotelMontenegroPage = propertiesListPage.openTheHotelPage();
	}
	
	@Test(dependsOnMethods = "openHotelMontenegroPage")
	public void checkFreeWiFi() {
		hotelMontenegroPage.checkPresenceOfFreeWiFiBenefit();
	}
	
	@Test(dependsOnMethods = "openHotelMontenegroPage")
	public void checkFreeParking() {
		hotelMontenegroPage.checkPresenceOfFreeParkingBenefit();
	}
	
	
	@AfterClass
	public void tearDown() {
		//Browser.close();
	}
	*/
}
