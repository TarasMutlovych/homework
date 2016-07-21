package sourceCode;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
		@FindBy(css = "div[data-hotelid='98157'] a.hotel_name_link.url"),
	@FindBy(xpath = ".//div[@id = 'disambBlock_TOP_N']//div[@class = 'TOP_NWrapper']//a[@data-title = 'Hotel Montenegro, Budva, Budva County, Montenegro']")
	})	
	WebElement showPropertiesButton;
	
	private WebElement hotelLink;
	
	@Step
	public void checkThePresenceOfHotel(String hotelName) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.xpath("//span[contains(text(),'" + hotelName + "')]")));
		hotelLink = driver.findElement(By.xpath("//span[contains(text(),'" + hotelName + "')]"));	
	}

	
	//Opening Montenegro Hotel
	@Step
	public HotelPage clickShowPropertiesButton() {
		hotelLink.click();
		String homePage = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		for (String windowID : windows) {
			if (!windowID.equalsIgnoreCase(homePage)){
				driver.close();
				driver.switchTo().window(windowID);
			}
		}
		return new HotelPage(driver);

	}
}
