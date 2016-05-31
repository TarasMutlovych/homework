package homeworkAuthomation;

import java.io.IOException;

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

	BookingStartPage bookingStartPage;
	DestinationsPage destinationsPage;
	
	private String theSiteURL = "http://www.booking.com/";
	private String fileDestination = "C:\\Users\\User-PC\\git";
	private String fileName = "Cities.xlsx";
	
	@BeforeClass
	public void setUp() {
		Browser.openFirefox();
		//Browser.openChrome();
		bookingStartPage = Browser.openStartPage(theSiteURL);
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
