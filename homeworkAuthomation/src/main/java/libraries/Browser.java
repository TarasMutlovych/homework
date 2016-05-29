package libraries;

import javax.swing.Action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sourceCode.BookingStartPage;
import sourceCode.IndustrialAllianceStartPage;
import sourceCode.StartPage;

public class Browser {

	private static WebDriver driver;
	
	public static StartPage openFirefox() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		return new StartPage(driver);
	}
	
	public static StartPage openChrome() {
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		return new StartPage(driver);
	}
	
	public static void openChrome1() {
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	public static IndustrialAllianceStartPage openStartPage1(String theSiteURL) {
		driver.get(theSiteURL);
		return new IndustrialAllianceStartPage (driver);
	}
	
	
	public static void close() {
		driver.close();
	}

	public static void waitForVisibility (WebDriver driver, WebElement elment) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(elment));
	}
	
	public static BookingStartPage openStartPage(String theSite) {
		driver.get(theSite);
		return new BookingStartPage(driver);
	}
	
	
	
	public static void sleepForMilisecs (int milisecs) {
		try {
			Thread.sleep(milisecs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void moveToElementAndClick (WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
		}
	
	public static void moveSliderToRight(WebDriver driver, WebElement element, int sliderPoints) {
		Actions actions = new Actions(driver);
		actions.dragAndDropBy(element, sliderPoints, 0).build().perform();
		
		//Action action = (Action) move.dragAndDropBy(element, 30, 0).build();
		//action.p;
	   // Action action = (Action) move.dragAndDropBy(slider, 30, 0)
	}
}
