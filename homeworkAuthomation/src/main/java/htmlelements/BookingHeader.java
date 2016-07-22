package htmlelements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
	
	public void expandLoginPopUpWindow() {
	//Browser.waitForVisibility(driver, signInButton);
	signInButton.click();
	}
}
