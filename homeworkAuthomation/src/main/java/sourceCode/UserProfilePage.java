package sourceCode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import libraries.Browser;
import ru.yandex.qatools.allure.annotations.Step;

public class UserProfilePage {

	WebDriver driver;

	public UserProfilePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "user_firstname")
	WebElement userName;

	@FindBy(className = "user_lastname")
	WebElement userSurname;

	// Search lviv Region
	@FindBy(xpath = "//a[@class = 'header_link_new_icon popover_trigger']")
	WebElement getDestinationTipsButton;

	@FindBy(xpath = "//dd[@class = 'dsf_banner_awareness_index_cta']")
	WebElement startExploringButton;

	@Step
	public void verifyThatUserNameEqualsTo(String expectedFirstName) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String userFirstName = wait.until(ExpectedConditions.visibilityOf(userName)).getText();
		Assert.assertEquals(userFirstName, expectedFirstName);
	}

	@Step
	public void verifyThatUserSurnameEqualsTo(String expectedLastName) {
		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		String userLastName = wait1.until(ExpectedConditions.visibilityOf(userSurname)).getText();
		Assert.assertEquals(userLastName, expectedLastName);
	}

	@Step
	public void clickGetDestinationTipsButton() {
		// Browser.sleepForMilisecs(5000);
		getDestinationTipsButton.click();
	}

	@Step
	public DestinationTipsPage clickStartExploringButton() {
		startExploringButton.click();
		return new DestinationTipsPage(driver);
	}
}
