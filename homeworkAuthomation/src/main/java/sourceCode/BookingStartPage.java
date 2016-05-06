package sourceCode;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.allure.annotations.Step;

public class BookingStartPage extends StartPage {

	
	public BookingStartPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		}
	
	@FindBy(xpath = "//li[@id = 'current_account'][position() = 2]//div")
	WebElement signInButtonOnTheStartScreen;
	
	@FindBy(className = "user_access_email")
	WebElement emailAddressField; 
	
	@FindBy(className = "user_access_password")
	WebElement passwordField; 
	
	@FindBy(css = "input[type = 'submit']")
	WebElement signInButtonOnTheLogInPopUp; 
	
	@FindBy(className = "user_access_form")
	WebElement signInForm;
	
	private void expandLoginPopUpWindow() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(signInButtonOnTheStartScreen));
		signInButtonOnTheStartScreen.click();
	}
	
	//@After(value = "expandLoginPopUpWindow")
	@Step
	public void enterLogin(String login) {
		expandLoginPopUpWindow();
		emailAddressField.sendKeys(login);
	}

	@Step
	public void enterPassword(String pass) {
		passwordField.sendKeys(pass);	
	}

	@Step
	public UserProfilePage submitCredentials() {
		signInForm.submit();
		//signInButtonOnTheLogInPopUp.click();
		//Alert alert = driver.switchTo().alert();
		//alert.accept();
		//((JavascriptExecutor) driver).executeScript(document.,);	
		//((JavascriptExecutor) driver).executeScript("arguments[0].submit()", signInButtonOnTheLogInPopUp);
		return new UserProfilePage(driver);
	}
	
}
