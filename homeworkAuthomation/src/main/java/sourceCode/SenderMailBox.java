package sourceCode;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import libraries.Browser;

public class SenderMailBox {
	
	WebDriver driver;
	 
	SenderMailBox (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy  (id = "gmail-sign-in")
	WebElement signInButton;
	
	@FindBy (id = "Email")
	WebElement emailField;
	
	@FindBy (css = "input[type = 'submit']")
	WebElement submitCredential;
	
	@FindBy (id = "Passwd")
	WebElement passField;
	
	@FindBy (css = "div.T-I.J-J5-Ji.T-I-KE.L3")
	WebElement composeButton;
	
	@FindBy (css = "div.dw div.AD div.aoD.hl")
	WebElement recipientEmail;
	
	@FindBy (css = "div.dw div.AD div.aoD.az6")
	WebElement subjectField;
	
	@FindBy (css = "div.dw div.AD div.Am.Al.editable.LW-avf")
	WebElement letterBody;
	
	@FindBy(css = "div.no div.nH.nn div.AD")
	WebElement letterForm;
	
	public static SenderMailBox openTheLogInPage (WebDriver driver, String URL) {
		driver.get(URL);
		return new SenderMailBox (driver);
	}
	
	public void logIntoTheMailBox (String email, String pass) {
		Browser.waitForVisibility(driver, signInButton);
		signInButton.click();
		Browser.waitForVisibility(driver, emailField);
		emailField.sendKeys(email);
		submitCredential.submit();
		Browser.waitForVisibility(driver, passField);
		passField.sendKeys(pass);
		passField.submit();
	}
	
	public void createAndSentLetted (String email, String subject, String body) {
		Browser.waitForVisibility(driver, composeButton);
		composeButton.click();
		//Browser.waitForVisibility(driver, recipientEmail);
			
		//recipientEmail.sendKeys(email);
		//subjectField.sendKeys(subject);
		//letterBody.sendKeys(body);	
		
		Browser.sleepForMilisecs(3000);
		Assert.assertNotNull(letterForm);
	}
}

