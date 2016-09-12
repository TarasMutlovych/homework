package orangeEnterprice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import htmlElementsOrangeEnterprice.MenuNavBar;
import htmlElementsOrangeEnterprice.MoreInfoPersonalDetails;
import libraries.Browser;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

public class EssUserPage implements UserPage {

	// Scanner in = new Scanner (Path.get("myfile") , "UTC-8")

	@FindBy(css = "div#pdMainContainer div.head h1")
	WebElement myInfoSectionHeader;

	public EssUserPage(WebDriver driver) {
		this.driver = driver;
		HtmlElementLoader.populatePageObject(this, driver);
	}

	private MenuNavBar menuNavBar;
	private WebDriver driver;
	private MoreInfoPersonalDetails moreInfoPersonalDetails;

	/**
	 * Method for opening My Info page
	 */
	public void openMyInfo() {
		Browser.waitForElementToBeClickable(driver, menuNavBar.myInfoButton);
		Browser.moveToElementAndClick(driver, menuNavBar.myInfoButton);
	}

	public void verifyThatDefaultSectionIs(String defultSection) {
		Browser.waitForElementToBeClickable(driver, myInfoSectionHeader);
		Assert.assertEquals(myInfoSectionHeader.getText(), defultSection, "Defaul section is not as expected");
	}

	@Override
	public void clickEditButton() {
		Browser.waitForElementToBeClickable(driver, moreInfoPersonalDetails.applyChangesButton);
		Browser.moveToElementAndClick(driver, moreInfoPersonalDetails.applyChangesButton);
	}

	@Override
	public void verifyEditButtonTextIsChangedToSave() {
		try {
			Browser.waitForVisibility(driver, moreInfoPersonalDetails.applyChangesButton);
			Assert.assertEquals(moreInfoPersonalDetails.applyChangesButton.getText(), "Save",
					"Button text is not changed ftom 'Edit' to 'Save");
		} catch (Exception e) {

		} finally {
			Browser.refreshThePage(driver);
		}
	}

	/**
	 * Method for verification mandatory First Name field
	 */
	public void testFirstNameMandatoryfield() {
		Browser.waitForVisibility(driver, moreInfoPersonalDetails.firstNameInput);
		moreInfoPersonalDetails.firstNameInput.clear();
		Browser.moveToElementAndClick(driver, moreInfoPersonalDetails.applyChangesButton);

		try {
			Browser.waitForVisibility(driver, moreInfoPersonalDetails.firstNameError);
			Assert.assertEquals(moreInfoPersonalDetails.firstNameError.getText(), "Requiared",
					"There is no 'Required' text below First Name input");
		} catch (Exception e) {

		} finally {
			Browser.refreshThePage(driver);
		}
	}

	public void testLastNameMandatoryfield() {
		Browser.waitForVisibility(driver, moreInfoPersonalDetails.lastNameInput);
		moreInfoPersonalDetails.lastNameInput.clear();
		Browser.moveToElementAndClick(driver, moreInfoPersonalDetails.applyChangesButton);

		try {
			Browser.waitForVisibility(driver, moreInfoPersonalDetails.firstNameError);
			Assert.assertEquals(moreInfoPersonalDetails.lastNameError.getText(), "Requaired",
					"There is no 'Required' text below First Name input");
		} catch (Exception e) {

		} finally {
			Browser.refreshThePage(driver);
		}

	}
}
