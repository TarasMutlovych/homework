package sourceCode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import htmlelements.BookingHeader;
import libraries.Browser;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

public class UserProfilePage {

	WebDriver driver;

	public UserProfilePage(WebDriver driver) {
		this.driver = driver;
		HtmlElementLoader.populatePageObject(this,driver);
	}

	@FindBy(xpath = "//dd[@class = 'dsf_banner_awareness_index_cta']")
	WebElement startExploringButton;

	private BookingHeader bookingHeader;
	
	@Step
	public void verifyThatUserNameEqualsTo(String expectedFirstName) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		String userFirstName = wait.until(ExpectedConditions.visibilityOf(bookingHeader.userName)).getText();
		Assert.assertEquals(userFirstName, expectedFirstName);
	}

	@Step
	public void verifyThatUserSurnameEqualsTo(String expectedLastName) {
		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		String userLastName = wait1.until(ExpectedConditions.visibilityOf(bookingHeader.userSurname)).getText();
		Assert.assertEquals(userLastName, expectedLastName);
	}

	@Step
	public void clickGetDestinationTipsButton() {
		bookingHeader.getDestinationTipsButton.click();
	}

	@Step
	public DestinationTipsPage clickStartExploringButton() {
		startExploringButton.click();
		return new DestinationTipsPage(driver);
	}
}
