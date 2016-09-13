package homeworkAuthomation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import libraries.Browser;
import libraries.Tools;
import sourceCode.RecipientMailBox;
import sourceCode.SenderMailBox;

public class Gmail {

	private WebDriver browser;
	
	SenderMailBox senderMailBox;
	RecipientMailBox recipientMailBox;
	
	
	private String letterSubject;
	private String letterBody;
	
	private String URL = "https://www.google.com/intl/en/mail/help/about.html";
	private String senderEmail = "testqwerty1970@gmail.com";
	private String senderPass = "123456789QW";
	private String recipientEmail = "tarasmytlovych@gmail.com";
	private	String recipientPass = "dominic001";
	
	@BeforeClass
	public void setUp() {
		browser = Browser.openChrome();
		//browser = Browser.openFirefox();
		senderMailBox = SenderMailBox.openTheLogInPage(browser, URL);
		letterSubject = Tools.getRandomString(10);
		letterBody = Tools.getRandomString(20);
	}
	
	@Test (priority = 1)
	public void sendLetter() {
		senderMailBox.clickSignInBUtton();
		senderMailBox.logIntoTheMailBox(senderEmail, senderPass);
		senderMailBox.createAndSentLetted(recipientEmail, letterSubject, letterBody);
		senderMailBox.openSentMail();
		senderMailBox.verifyThatLetterIsPresent(letterSubject);
		senderMailBox.verifyThatAttachmentisInTheLetter();
	}
	
	@Test (priority = 2) 
	public void verifyReceivingEmail() {
		recipientMailBox = senderMailBox.addNewAccount();
		recipientMailBox.logIntoTheMailbox(recipientEmail, recipientPass);
		recipientMailBox.verifyTheLetterArrives(letterSubject);
	}
	
	@AfterClass
	public void tearDown() {
		Browser.close();
	}

	
	
}
