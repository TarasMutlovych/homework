package libraries;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sourceCode.BookingStartPage;
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
	
	public static void close() {
		driver.close();
	}

	public static void waitForVisibility (WebDriver pageDriver, WebElement elment) {
		WebDriverWait wait = new WebDriverWait(pageDriver, 10);
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
}
