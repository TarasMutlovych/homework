package sourceCode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import libraries.Browser;

public class ResultsPage {

	WebDriver driver;
	public ResultsPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//p[normalize-space(.) = 'Budva, Budva County, Montenegro']/preceding-sibling::div[normalize-space(.) = 'Hotel Montenegro']")
	WebElement searchedHotelLink ;
	
	public void checkThePresenceOfHotel(String hotelName) {
		Browser.waitForVisibility(driver, searchedHotelLink);
		Assert.assertNotNull(searchedHotelLink);
	}
}
