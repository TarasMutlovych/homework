package sourceCode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class UserProfilePage {

	WebDriver driver;
	
	public UserProfilePage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "user_firstname")
	WebElement userName;
	
	@FindBy (className = "user_lastname")
	WebElement userSurname;

	public void verifyThatUserNameEqualsTo(String expectedFirstName) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String userFirstName = wait.until(ExpectedConditions.visibilityOf(userName)).getText();		
		Assert.assertEquals(userFirstName, expectedFirstName);
	}

	public void verifyThatUserSurnameEqualsTo(String expectedLastName) {
		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		String userLastName = wait1.until(ExpectedConditions.visibilityOf(userSurname)).getText();		
		Assert.assertEquals(userLastName, expectedLastName);
	}
}
