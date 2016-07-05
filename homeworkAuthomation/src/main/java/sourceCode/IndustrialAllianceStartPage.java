package sourceCode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import libraries.Browser;
import ru.yandex.qatools.allure.annotations.Step;

public class IndustrialAllianceStartPage {

	WebDriver driver;

	public IndustrialAllianceStartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "li.dropdown.Pret.three-items a.dropdown-toggle")
	WebElement loansButton;

	@FindBy(css = "li.dropdown.Pret.three-items.open ul.dropdown-menu.dropdown-menu-large ul.mega-menu-content a[ data-utag-name='mortgage_loan']")
	WebElement mortgagesButton;

	@FindBy(css = "a#topLangMenuItem")
	WebElement changeLanguageEnButton;

	public static IndustrialAllianceStartPage openStartPage(WebDriver browser, String theSiteURL) {
		browser.get(theSiteURL);
		return new IndustrialAllianceStartPage(browser);
	}

	public void verifyTheLanguage(String language) {

		Browser.waitForVisibility(driver, changeLanguageEnButton);
		String languageToChange = changeLanguageEnButton.getText();
		System.out.println(languageToChange);
		if (languageToChange == language) {
			Browser.moveToElementAndClick(driver, changeLanguageEnButton);
		}
	}

	@Step
	public void clickLoansButton() {
		Browser.waitForVisibility(driver, loansButton);
		loansButton.click();
	}

	@Step
	public MortgagePage clickMortagesButton() {
		Browser.waitForVisibility(driver, mortgagesButton);
		Browser.moveToElementAndClick(driver, mortgagesButton);
		return new MortgagePage(driver);
	}

}
