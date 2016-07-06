package homeworkAuthomation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import libraries.Browser;
import sourceCode.SenderMailBox;

public class Gmail {

	private WebDriver browser;
	private String URL = "https://www.google.com/intl/en/mail/help/about.html";
	private String senderEmail = "testqwerty1970@gmail.com";
	private String senderPass = "123456789QW";
	private String letterSubject = "You will never guess";
	private String letterBody = "You will never guess what is inside";
	SenderMailBox senderMailBox;
	private String recipientEmail = "tarasmytlovych@gmail.com";
	private	String recipientpass = "";
	
	@BeforeClass
	public void setUp() {
		browser = Browser.openChrome();
		senderMailBox = SenderMailBox.openTheLogInPage(browser, URL);
	}
	
	@Test (priority = 1)
	public void sendLetter() {
		senderMailBox.logIntoTheMailBox(senderEmail, senderPass);
		senderMailBox.createAndSentLetted(recipientEmail, letterSubject, letterBody);
		senderMailBox.verifyThatLetterIsSent(letterBody);
	}
	
	
	
}
