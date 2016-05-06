package homeworkAuthomation;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import libraries.Browser;
import sourceCode.BookingStartPage;
import sourceCode.UserProfilePage;

public class TestCases1_3 {

	BookingStartPage bookingStartPage;
	UserProfilePage userProfilePage;

	private String theSiteURL = "http://www.booking.com";
	private String login = "patriotl@i.ua";
	private String pass = "dominic0018";
	private String expectedFirstname = "Taras";
	private String expectedLastName = "Mytlovych";

	@BeforeClass
	public void setUp() {
		// Browser.openFirefox();
		Browser.openChrome();
		bookingStartPage = Browser.openStartPage(theSiteURL);
	}

	@Test
	public void logIntoBookingCom() {
		bookingStartPage.enterLogin(login);
		bookingStartPage.enterPassword(pass);
		userProfilePage = bookingStartPage.submitCredentials();
	}

	@Test(dependsOnMethods = "logIntoBookingCom")
	public void verifyUserFirstAndLastName() {
		userProfilePage.verifyThatUserNameEqualsTo(expectedFirstname);
		userProfilePage.verifyThatUserSurnameEqualsTo(expectedLastName);
	}

	@AfterClass
	public void tearDown() {
		Browser.close();
	}
}
