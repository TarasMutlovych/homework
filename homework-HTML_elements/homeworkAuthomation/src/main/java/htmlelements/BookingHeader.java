package htmlelements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import libraries.Browser;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;


@Name("Booking header")
@FindBy(xpath = "//div[@role = 'banner']")
public class BookingHeader extends HtmlElement {

	@Name ("Language Changing Button")
	@FindBy(css = "li.user_center_option.uc_language")
	public WebElement languageChangingButton ;

	@Name ("Sign In Button")
	@FindBy(xpath = ".//li[@id = 'current_account'][position() = 2]//div")
	public WebElement signInButton;
	
	@Name("Default English language")
	@FindBy(xpath = "//img[contains(@src,'gb')]")
	public WebElement defaultEnglish;
	
	@Name("Destination tips button")
	@FindBy(xpath = "//a[@class = 'header_link_new_icon popover_trigger']")
	public WebElement getDestinationTipsButton;


	@FindBy(className = "user_firstname")
	public WebElement userName;

	@FindBy(className = "user_lastname")
	public WebElement userSurname;

	
	public void expandLoginPopUpWindow() {
	signInButton.click();
	}
}
