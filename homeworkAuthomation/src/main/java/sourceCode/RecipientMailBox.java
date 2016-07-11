package sourceCode;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	
	public void verifyTheLetterArrives (String subject) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.xpath("//span/*[contains(text(),'" + subject + "')]")));
	}
}
