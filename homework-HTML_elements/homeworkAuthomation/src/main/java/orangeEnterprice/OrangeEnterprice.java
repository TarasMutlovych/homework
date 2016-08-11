package orangeEnterprice;

import org.openqa.selenium.WebDriver;

import libraries.Browser;

public class OrangeEnterprice {

	static WebDriver browser;
	
	private String siteURL = "http://enterprise.demo.orangehrmlive.com";
	
public static OrangeEnterprice openStartPage () {
	 browser = Browser.openChrome();
		//browser = Browser.openFirefox();
		
	return null;	 
 }

/*
public OrangeUserPage loginAs (String userRole) {
	if (userRole.equals()


}
*/
}
