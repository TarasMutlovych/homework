package sourceCode;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import libraries.Browser;
import ru.yandex.qatools.allure.annotations.Step;

public class ResultsPage {

	WebDriver driver;
	public ResultsPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//p[normalize-space(.) = 'Budva, Budva County, Montenegro']/preceding-sibling::div[normalize-space(.) = 'Hotel Montenegro']/a")
	WebElement searchedHotelLink ;
	
	@FindBy(xpath = ".//div[@id = 'disambBlock_TOP_N']//div[@class = 'TOP_NWrapper']//a[@data-title = 'Hotel Montenegro, Budva, Budva County, Montenegro']")
	WebElement showPropertiesButton;
	
	@Step
	public void checkThePresenceOfHotel(String hotelName) {
		Browser.waitForVisibility(driver, searchedHotelLink);
		Assert.assertNotNull(searchedHotelLink);
	}

	
	//Opening Montenegro Hotel
	@Step
	public PropertiesListPage clickShowPropertiesButton() {
		Browser.waitForVisibility(driver, showPropertiesButton);
		showPropertiesButton.click();
		return new PropertiesListPage(driver) ;
	}
}
