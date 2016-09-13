package orangeEnterprice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import libraries.Browser;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

public class OrangeEnterprice {

	@FindBy (name = "txtUsername")
	WebElement userNameField;
	
	@FindBy (name = "txtPassword")
	WebElement userPassField;
	
	@FindBy (name = "Submit")
	WebElement submitButton;
	
	public OrangeEnterprice(WebDriver driver) {
		this.driver = driver;
		// PageFactory.initElements(driver, this);
		HtmlElementLoader.populatePageObject(this, driver);
	}
	
	private static String siteURL = "http://enterprise.demo.orangehrmlive.com";
	public WebDriver driver;

	private String essUserName = "linda.anderson";
	private String essUserPass = "linda.anderson";

	private String firstSupervisorUserName = "anthony.nolan";
	private String firstSupervisorPass = "anthony.nolan";

/** Static Method for instantiating OrangeEnterprice class
 * 
 * @return OrangeEnterprice instance
 */
	public static OrangeEnterprice openTheSite() {
		WebDriver driver = Browser.openChrome();
		// browser = Browser.openFirefox();
		driver.get(siteURL);
		driver.manage().window().maximize();
		return new OrangeEnterprice(driver);
	}

	/**Method for logging in and returning different user pages depends on what User Role was selected 
	 * 
	 * @param userRole
	 * @return Instance of User Page for exact user role
	 */
	public UserPage loginAs(String userRole) {
		if (userRole.equals("EssUser")) {
			
			Browser.waitForVisibility(driver, userNameField);
			userNameField.sendKeys(essUserName);
			userPassField.sendKeys(essUserPass);
			Browser.moveToElementAndClick(driver, submitButton);
			return new EssUserPage(driver);
		} if (userRole.equals("1stSupervisor")) {
			
			Browser.waitForVisibility(driver, userNameField);
			userNameField.sendKeys(firstSupervisorUserName);
			userPassField.sendKeys(firstSupervisorPass);
			Browser.moveToElementAndClick(driver, submitButton);
			return new FirstSupervisorPage (driver);
		}
		else {
			return null;
		}

	}

}
