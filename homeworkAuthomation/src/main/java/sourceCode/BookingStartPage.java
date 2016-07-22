package sourceCode;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import htmlelements.BookingHeader;
import libraries.Browser;
import libraries.DatePicker;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

public class BookingStartPage {

	private static WebDriver driver;
	
	
	/// May be added to headers

	@FindBy(css = "li.user_center_option.uc_language")
	WebElement languageChangingButton;
/*
	@FindBy(xpath = "//li[@id = 'current_account'][position() = 2]//div")
	WebElement signInButtonOnTheStartScreen;
*/
	// end of candidates

	@FindBy(xpath = "//img[contains(@src,'gb')]")
	WebElement defaultEnglish;

	@FindBy(xpath = "//span[contains(text(), 'English')]")
	WebElement englishLanguageButton;

	@FindBy(className = "user_access_email")
	WebElement emailAddressField;

	@FindBy(className = "user_access_password")
	WebElement passwordField;

	@FindBy(className = "user_access_form")
	WebElement signInForm;

	@FindBy(id = "ss")
	WebElement searchInputField;

	@FindBy(css = "ul.c-autocomplete__list.sb-autocomplete__list.-visible li[data-i='0']")
	WebElement HotelInAutocompilelist;

	// calendar final
	@FindAll({
			// form #1 with separate fields for days and month
			@FindBy(name = "checkin_monthday"), @FindBy(name = "checkin_year_month"),
			@FindBy(name = "checkout_monthday"), @FindBy(name = "checkout_year_month"),

			// form #2 with one field for day and moth
			@FindBy(css = "div[data-calendar2-type='checkin'][data-sb-id='main']"),
			@FindBy(css = "div[data-calendar2-type='checkout'][data-sb-id='main']") })
	List<WebElement> dateInputFields;

	@FindBy(css = "div.sb-dates__grid")
	WebElement caledarForm;
	// calendar final

	@FindBy(id = "frm")
	WebElement searchForm;

	@FindBy(css = "ul.b-popular_list.lp_endorsements_popular_destinations_container + p > a")
	WebElement moreDestinatoins;

	private BookingHeader bookingHeader;
	
	public BookingStartPage(WebDriver driver) {
		this.driver = driver;
		//PageFactory.initElements(driver, this);
		HtmlElementLoader.populatePageObject(this,driver);	
	}

	public static BookingStartPage openStartPage(WebDriver browser, String theSiteURL) {
		browser.get(theSiteURL);
		return new BookingStartPage(browser);
	}

	// User First And Last Name Verification
	private void expandLoginPopUpWindow() {
		//Browser.waitForVisibility(driver, bookingHeader.signInButton);
		//bookingHeader.signInButton.click();
		bookingHeader.expandLoginPopUpWindow();
	}

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
		return new UserProfilePage(driver);
	}

	// Searching for Montenegro Hotel

	public void setEnglishLanguage() {
		Browser.waitForVisibility(driver, languageChangingButton);
		if (defaultEnglish != null) {

			Browser.moveToElementAndClick(driver, languageChangingButton);

			Browser.waitForVisibility(driver, englishLanguageButton);
			Browser.moveToElementAndClick(driver, englishLanguageButton);

		} else {
		}
	}

	@Step
	public void enterHotelName(String hotelName) {
		Browser.waitForVisibility(driver, searchInputField);
		searchInputField.sendKeys(hotelName);
		Browser.sleepForMilisecs(2000);
		Actions actions = new Actions(driver);
		actions.moveToElement(HotelInAutocompilelist).click().build().perform();

	}

	@Step
	public ResultsPage searchForTheHotel() {
		searchForm.submit();
		return new ResultsPage(driver);
	}

	// Test 5 - Working with Excel file
	@Step
	public DestinationsPage clickMoreDestinatons() {
		Browser.waitForVisibility(driver, moreDestinatoins);
		moreDestinatoins.click();
		return new DestinationsPage(driver);
	}

	// Hopefully final implementation

	private DatePicker calendarDatePicker;

	private DatePicker openCorrectCalendarInstance() {

		Browser.waitForVisibility(driver, caledarForm);
		int dateInputs = dateInputFields.size();

		if (dateInputs == 4) {
			System.out.println("4");
			return null;
		} else {
			return new BookingCalendarDatePlusYear(driver);
			// System.out.println("option 2");
		}
	}

	public void openCalendar() {
		calendarDatePicker = openCorrectCalendarInstance();
		calendarDatePicker.openCheckInCalendar();
	}

	public void selectCheckInDate1(String checInDate) {
		calendarDatePicker.selectCheckInDate1(checInDate);
	}

	public void openCheckOutCalendar1() {
		calendarDatePicker.openCheckOutCalendar();
	}

	public void selectDesiredCheckOutDate1(String checkOutDate) {
		calendarDatePicker.selectDesiredCheckOutDate1(checkOutDate);
	}

}