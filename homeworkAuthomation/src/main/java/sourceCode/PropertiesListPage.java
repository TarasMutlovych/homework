package sourceCode;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import libraries.Browser;
import ru.yandex.qatools.allure.annotations.Step;

public class PropertiesListPage {

	WebDriver driver;

	public PropertiesListPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[normalize-space(.) ='Hotel Montenegro']/parent::a")
	WebElement hotelMontenegroBudvaLink;

	@Step
	public HotelMontenegroPage openTheHotelPage() {
		Browser.waitForVisibility(driver, hotelMontenegroBudvaLink);
		hotelMontenegroBudvaLink.click();
		
		// Switching driver
		String homePage = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		for (String windowID : windows) {
			if (!windowID.equalsIgnoreCase(homePage)){
				driver.close();
				driver.switchTo().window(windowID);
			}
		}
		return new HotelMontenegroPage(driver);
	}

}
