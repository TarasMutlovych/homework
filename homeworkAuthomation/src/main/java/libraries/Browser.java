package libraries;


import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Browser {

	private static WebDriver driver;

	public static WebDriver openFirefox() {
/*	String userDir = System.getProperty("user.dir");
		String pathToFireFoxdriver = userDir + "\\testware\\browsers\\Firefox4601.exe";
		System.setProperty("webdriver.firefox.exe",pathToFireFoxdriver);
		WebDriver driver=new  FirefoxDriver();
		 
		String pathToFireFoxdriver = "C:\\Users\\Mozilla Firefox47\\firefox.exe";
		//String pathToFireFoxdriver = "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
		File pathToBinary = new File(pathToFireFoxdriver);
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();       
		driver = new FirefoxDriver(ffBinary,firefoxProfile);
*/
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		return driver;
	}

	public static WebDriver openChrome() {
		String userDir = System.getProperty("user.dir");
		String pathToChromedriver = userDir + "\\testware\\browsers\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", pathToChromedriver);

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}

	public static void close() {
		driver.close();
	}

	public static void waitForVisibility(WebDriver driver, WebElement elment) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(elment));
	}

	public static void waitForElementToBeClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void waitForInvisibilityOFElements(WebDriver driver, List <WebElement> elements) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
	}

	public static void sleepForMilisecs(int milisecs) {
		try {
			Thread.sleep(milisecs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void moveToElementAndClick(WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
	}

	public static void moveSliderToRight(WebDriver driver, WebElement element, int sliderPoints) {
		Actions actions = new Actions(driver);
		actions.dragAndDropBy(element, sliderPoints, 0).build().perform();
	}

	public static void scrollThePageDown(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0, 800)", "");
	}

	public static void scrollThePageUp(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0, -700)", "");
	}
}
