package sourceCode;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import libraries.Browser;
import ru.yandex.qatools.allure.annotations.Step;

public class DestinationTipsPage {

	WebDriver driver;
	
	public DestinationTipsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@class = 'js-autocomplete-locations dsf_search_input']")
	WebElement searchField;
	
	@FindBy(css = "div.ac_elements__item.ac_elements__suggestion.region.theme_4823")
	WebElement lvivRegion;
	
	@FindBy(css = "a.js_dsf_button.dsf_button")
	WebElement searchButton;
	
	@FindBy(css = "div.dsf-interests-title")
	WebElement searchResultsHeader;
	
	@FindBy(css = "div.dsf_to_refresh")
	WebElement searchResultsList;
	
	@Step
	public void enterLvivIntoSearch(String destination) {
		Browser.waitForVisibility(driver, searchField);
		searchField.sendKeys(destination);
	}

	@Step
	public void selectLvivRegionFromDropdown() {
		Browser.sleepForMilisecs(4000);
		Browser.moveToElementAndClick(driver, lvivRegion);
	}
	
	@Step
	public void clickSearchButton() {
		searchButton.click();
	}

	@Step
	public void verifyThePresensOfResults() {
		Browser.waitForVisibility(driver, searchResultsHeader);
		String text = searchResultsHeader.getText();
		Assert.assertTrue(text.contains("Lviv Region, Ukraine"), "Header of serch results list does not contain Lviv Region, Ukraine");
		Assert.assertNotNull(searchResultsList, "There is no locations in Lviv Region, Ukraine");
	}
	
}
