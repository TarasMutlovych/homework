package sourceCode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import libraries.Browser;
import ru.yandex.qatools.allure.annotations.Step;

public class MortgagePage {


	WebDriver driver;
	
	public MortgagePage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div.light-grey-tiles.tile-icon.icone-calculateur a[data-utag-name='calculate_your_payments']")
	WebElement calculateYourPaymentsButton;
	
	@FindBy(css = "button.fermer")
	WebElement closePopUp;

	@Step
	public MortgagePaymentCalculatorPage clickCalculateYourPaymentsButton () {
		//Browser.waitForVisibility(driver, calculateYourPaymentsButton);
		//if (closePopUp != null) {
		//	Browser.moveToElementAndClick(driver, closePopUp);
		//}
		Browser.scrollThePageDown(driver);
		Browser.waitForVisibility(driver, calculateYourPaymentsButton);
		//Browser.moveToElementAndClick(driver, calculateYourPaymentsButton);
		calculateYourPaymentsButton.click();
		return new MortgagePaymentCalculatorPage(driver);
	}
	
	
}
