package htmlelements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Sign In form")
@FindBy(xpath = "//div[contains(@class, 'signin user-access')]")
public class SignInForm extends HtmlElement {

	@Name("Email adress field")
	@FindBy(className = "user_access_email")
	public WebElement emailAddressField;

	@Name("Pasword field")
	@FindBy(className = "user_access_password")
	public WebElement passwordField;

	@FindBy(className = "user_access_form")
	public WebElement signInForm;

}
