package homeworkAuthomation;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import libraries.Browser;
import sourceCode.BookingStartPage;
import sourceCode.HotelMontenegroPage;
import sourceCode.PropertiesListPage;
import sourceCode.ResultsPage;

public class MontenegroHotel {

	BookingStartPage bookingStartPage;
	ResultsPage resultsPage;
	PropertiesListPage propertiesListPage;
	HotelMontenegroPage hotelMontenegroPage;

	
	private String theSiteURL = "http://www.booking.com/";
	private String hotelName = "Hotel Montenegro";

	//Make sure that date format is "dd/year-mm"
	private String checInDate = "22/2016-6";
	private String checkOutDate = "30/2016-6";
 

	@BeforeClass
	public void setUp() {
		//Browser.openFirefox();
		Browser.openChrome();
		bookingStartPage = Browser.openStartPage(theSiteURL);
	}
	
	@Test
	public void searchForHotel() {
		bookingStartPage.enterHotelName(hotelName);
		bookingStartPage.selectCheckInDate(checInDate);
		bookingStartPage.selectCheckOutDate(checkOutDate);
		resultsPage  = bookingStartPage.searchForTheHotel();
	}
	
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
	

	// Додати обробку ексепшина з різним форматом пейджі
	//  винести Actions в браузер 
	// Винести парсинг в тулзи
	// Винести переміщення драйвера в бравзер
}
