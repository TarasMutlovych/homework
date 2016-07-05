package libraries;

import org.openqa.selenium.JavascriptExecutor;
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
	
	public static WebDriver openFirefox() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		return driver;
	}
	
	public static WebDriver openChrome() {
		//System.setProperty("webdriver.chrome.driver", localRootDir.getAbsolutePath() + "\\\\Libs\\\\chromedriver.exe"); // хромдрайвер у меня лежит в проекте в папочке Libs
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		return driver;
	}
		
	public static StartPage openChrome23() {
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
	
	public static void close() {
		driver.close();
	}

	public static void waitForVisibility (WebDriver driver, WebElement elment) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(elment));
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
		}
	
	public static void scrollThePageDown (WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0, 800)", "");
	}
	
	public static void scrollThePageUp (WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0, -700)", "");
	}
}
