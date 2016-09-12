package htmlElementsOrangeEnterprice;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;


@Name("Personal Details")
@FindBy(id = "employee-details")
public class MoreInfoPersonalDetails extends HtmlElement{

	@Name ("Edit / Save / Processing button")
	@FindBy (id = "btnSave")
	public WebElement applyChangesButton;
	
	@Name("First Name Input")
	@FindBy(id = "personal_txtEmpFirstName")
	public WebElement firstNameInput;
	
	@Name("First Name Mandatory Field Error")
	@FindBy(id = "personal_txtEmpFirstName-error")
	public WebElement firstNameError;
	
	@Name("Middle Name Input")
	@FindBy(id = "personal_txtEmpMiddleName")
	public WebElement middleNameInput;
	
	@Name("Last Name Input")
	@FindBy(id = "personal_txtEmpLastName")
	public WebElement lastNameInput;
	
	@Name("Last Name Mandatory Field Error")
	@FindBy(id = "personal_txtEmpLastName")
	public WebElement lastNameError;
	
	@Name("Other ID Input")
	@FindBy(id = "personal_txtOtherID")
	public WebElement personalOtherID;
	
}
