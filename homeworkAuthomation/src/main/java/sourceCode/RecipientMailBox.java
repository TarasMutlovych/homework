package sourceCode;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class RecipientMailBox {

	WebDriver driver;

	RecipientMailBox(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	public void logIntoTheMailbox (String email, String pass) {
		String homePage = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		for (String windowID : windows) {
			if (!windowID.equalsIgnoreCase(homePage)){
				driver.close();
				driver.switchTo().window(windowID);
			}
		}
		SenderMailBox senderMailBox = new SenderMailBox (driver);
		senderMailBox.logIntoTheMailBox(email, pass);
	}
	
	public void verifyTheLetterArrives (String letterSubject) {
		SenderMailBox senderMailBox = new SenderMailBox (driver);
		senderMailBox.verifyThatLetterIsPresent(letterSubject);
	}
}
