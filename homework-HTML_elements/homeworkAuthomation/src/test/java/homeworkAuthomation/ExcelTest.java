package homeworkAuthomation;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import libraries.Browser;
import libraries.Tools;
import sourceCode.BookingStartPage;
import sourceCode.DestinationsPage;

public class ExcelTest {

	private WebDriver browser;
	BookingStartPage bookingStartPage;
	DestinationsPage destinationsPage;
	
	private String theSiteURL = "http://www.booking.com/";
	private String fileDestination = "C:\\Users\\User-PC\\git";
	private String fileName = "Cities.xlsx";
	
	@BeforeClass
	public void setUp() {
		//browser = Browser.openFirefox();
		browser = Browser.openChrome();
		bookingStartPage = BookingStartPage.openStartPage(browser, theSiteURL);
	}
	
	@Test
	public void openDestinationsPage() throws Exception {
		destinationsPage = bookingStartPage.clickMoreDestinatons();
		destinationsPage.clickdestinationsCities();
		destinationsPage.verifyNumberOfCities(fileDestination, fileName);
	}
	
	@AfterClass
	public void tearDown() {
		Browser.close();
		//Tools.deleteTheFile(fileDestination, fileName);
	}
	
}
