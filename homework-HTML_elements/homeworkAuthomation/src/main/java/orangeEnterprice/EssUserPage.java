package orangeEnterprice;

import java.util.List;

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
	private int numberOfOptions;
	private String[] maritalOptions = new String[numberOfOptions];

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
			Browser.waitForElementToBeClickable(driver, moreInfoPersonalDetails.applyChangesButton);
			// Browser.sleepForMilisecs(4000);
			Assert.assertEquals(moreInfoPersonalDetails.applyChangesButton.getAttribute("value"), "Save",
					"Button text is not changed ftom 'Edit' to 'Save");
		} catch (Exception e) {

		} finally {
			Browser.refreshThePage(driver);
		}
	}

	public void testFirstNameMandatoryfield() {
		Browser.waitForVisibility(driver, moreInfoPersonalDetails.firstNameInput);
		moreInfoPersonalDetails.firstNameInput.clear();
		Browser.moveToElementAndClick(driver, moreInfoPersonalDetails.applyChangesButton);

		try {
			Browser.waitForVisibility(driver, moreInfoPersonalDetails.firstNameError);
			Assert.assertEquals(moreInfoPersonalDetails.firstNameError.getText(), "Required",
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
			Assert.assertEquals(moreInfoPersonalDetails.lastNameError.getText(), "Required",
					"There is no 'Required' text below First Name input");
		} catch (Exception e) {

		} finally {
			Browser.refreshThePage(driver);
		}
	}

	public void setFirstName(String firstName) {
		Browser.waitForVisibility(driver, moreInfoPersonalDetails.firstNameInput);
		moreInfoPersonalDetails.firstNameInput.clear();
		moreInfoPersonalDetails.firstNameInput.sendKeys(firstName);
	}

	public void setLastName(String lastName) {
		Browser.waitForVisibility(driver, moreInfoPersonalDetails.lastNameInput);
		moreInfoPersonalDetails.lastNameInput.clear();
		moreInfoPersonalDetails.lastNameInput.sendKeys(lastName);
	}

	public void verifyThatNameisCahngedAbobeTheAvatar(String firstName, String lastName) {
		Browser.waitForVisibility(driver, moreInfoPersonalDetails.userFirstLastName);
		String expected = firstName + " " + lastName;
		Assert.assertEquals(moreInfoPersonalDetails.userFirstLastName.getText(), expected);

		if (moreInfoPersonalDetails.userFirstLastName.getText().equals(expected)) {
			Browser.waitForElementToBeClickable(driver, moreInfoPersonalDetails.applyChangesButton);
			Browser.moveToElementAndClick(driver, moreInfoPersonalDetails.applyChangesButton);
			moreInfoPersonalDetails.firstNameInput.clear();
			moreInfoPersonalDetails.firstNameInput.sendKeys("Linda");
			moreInfoPersonalDetails.lastNameInput.clear();
			moreInfoPersonalDetails.lastNameInput.sendKeys("Anderson");
			Browser.moveToElementAndClick(driver, moreInfoPersonalDetails.applyChangesButton);
		}
	}

	public void tickMaleGender() {
		Browser.waitForVisibility(driver, moreInfoPersonalDetails.genderMale);
		Browser.moveToElementAndClick(driver, moreInfoPersonalDetails.genderMale);
	}

	public void tickFemaleGender() {
		Browser.waitForVisibility(driver, moreInfoPersonalDetails.genderFemale);
		Browser.moveToElementAndClick(driver, moreInfoPersonalDetails.genderFemale);
	}

	public void verifyThatGenderChangingsIsSaved(String correctGender) {
		if (correctGender.equals("Male")) {
			Browser.waitForInvisibilityOFElements(driver, moreInfoPersonalDetails.successfullyMessage);
			Assert.assertEquals(moreInfoPersonalDetails.genderMale.getAttribute("checked"), "true");
		} else {
			Browser.waitForInvisibilityOFElements(driver, moreInfoPersonalDetails.successfullyMessage);
			Assert.assertEquals(moreInfoPersonalDetails.genderFemale.getAttribute("checked"), "true");
		}
	}

	public void expandMaritalStatusDropdown() {
		Browser.waitForVisibility(driver, moreInfoPersonalDetails.maritalStatusExpander);
		Browser.moveToElementAndClick(driver, moreInfoPersonalDetails.maritalStatusExpander);
	}

	private void getMaritalStatusOptions(List<WebElement> maritalStatusElements) {
		//Browser.waitForInvisibilityOFElements(driver, moreInfoPersonalDetails.maritalStatusElements);
		numberOfOptions = maritalStatusElements.size();
	System.out.println("length" + " " + maritalStatusElements.size());
			for (WebElement element : maritalStatusElements) {
				int n = 0;
				System.out.println("someoption" + " " + element.getAttribute("value"));
				maritalOptions[n] = element.getAttribute("value");
			n++;
			}
		System.out.println("options" + " " + maritalOptions);
		System.out.println("length" + " " + maritalOptions.length);
	}
	
	public void verifyMaritalOptions () {
		getMaritalStatusOptions(moreInfoPersonalDetails.maritalStatusElements);
	}
}