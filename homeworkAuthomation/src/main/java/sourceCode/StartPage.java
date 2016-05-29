package sourceCode;

import org.openqa.selenium.WebDriver;

public class StartPage {

	static WebDriver driver;
	
	public StartPage(WebDriver driver) {
		this.driver = driver;
	}
	/*
	public static StartPage openStartPage(String theSite) {
		driver.get(theSite);
		return new StartPage(driver);
	}
	*/
}
