package sourceCode;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
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
	
	@FindAll({
		@FindBy(partialLinkText = "Hotel Montenegro"),
	@FindBy(xpath = "//p[normalize-space(.) = 'Budva, Budva County, Montenegro']/preceding-sibling::div[normalize-space(.) = 'Hotel Montenegro']/a")
	
	})
	WebElement searchedHotelLink ;
	
	@FindAll({
		@FindBy(xpath = "div[data-hotelid='98157'] a.b-button.b-button_primary.sr_cta_button"),
	@FindBy(xpath = ".//div[@id = 'disambBlock_TOP_N']//div[@class = 'TOP_NWrapper']//a[@data-title = 'Hotel Montenegro, Budva, Budva County, Montenegro']")
	})	
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
