package libraries;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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

	
	public static BookingStartPage openStartPage(String theSite) {
		driver.get(theSite);
		return new BookingStartPage(driver);
	}
	
	
}
