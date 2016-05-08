package sourceCode;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import libraries.Browser;
import ru.yandex.qatools.allure.annotations.Step;

public class HotelMontenegroPage {

	WebDriver driver;
	
	public HotelMontenegroPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//p[@class = 'ph-item']/span[normalize-space(.) = 'Free WiFi']")
	WebElement freeWiFiBenefit;
	
	@FindBy(xpath = "//span[normalize-space(.) = 'Free parking']")
	WebElement freeParkingBenefit;
	
	@Step
	public void checkPresenceOfFreeWiFiBenefit() {
		//Browser.waitForVisibility(driver, freeWiFiBenefit);
		Assert.assertNotNull(freeWiFiBenefit, "No Free WiFi benefit in this hotel");
		}
	
	@Step
	public void checkPresenceOfFreeParkingBenefit() {
		//Browser.waitForVisibility(driver, freeParkingBenefit);
		Assert.assertNotNull(freeParkingBenefit, "No Free Parking benefit in this hotel");
		}
		
}
