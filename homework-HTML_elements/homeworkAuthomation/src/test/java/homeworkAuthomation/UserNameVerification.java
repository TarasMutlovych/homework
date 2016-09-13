package homeworkAuthomation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import libraries.Browser;
import sourceCode.BookingStartPage;
import sourceCode.DestinationTipsPage;
import sourceCode.UserProfilePage;

public class UserNameVerification {

	private WebDriver browser;
	
	
	BookingStartPage bookingStartPage;
	UserProfilePage userProfilePage;
	DestinationTipsPage destinationTipsPage;
	
	private String theSiteURL = "http://www.booking.com";
	private String login = "patriotl@i.ua";
	private String pass = "dominic0018";
	private String expectedFirstname = "Taras";
	private String expectedLastName = "Mytlovych";

	@BeforeClass
	public void setUp() {
		//browser = Browser.openFirefox();
		browser = Browser.openChrome();
		browser.get(theSiteURL);
		bookingStartPage = new BookingStartPage(browser);
	}

	@Test(priority = 1)
	public void userNameAndSurnameVerification() {
		bookingStartPage.enterLogin(login);
		bookingStartPage.enterPassword(pass);
		userProfilePage = bookingStartPage.submitCredentials();
	
		userProfilePage.verifyThatUserNameEqualsTo(expectedFirstname);
		userProfilePage.verifyThatUserSurnameEqualsTo(expectedLastName);
	}
	
	//Lviv region, Ukraine
	
	@Test(priority = 2)
	public void verifyThePresenceOfLvivInDestinationsList() {
		userProfilePage.clickGetDestinationTipsButton();
		
		destinationTipsPage = userProfilePage.clickStartExploringButton();
		destinationTipsPage.enterLvivIntoSearch("Lviv");
		destinationTipsPage.selectLvivRegionFromDropdown();
		destinationTipsPage.clickSearchButton();
	
		destinationTipsPage.verifyThePresensOfResults();
	}
	
	@AfterClass
	public void tearDown() {
	Browser.close();
	}
}
