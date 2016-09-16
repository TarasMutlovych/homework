package htmlElementsOrangeEnterprice;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

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
	
	@Name("User's name above profile picture")
	@FindBy(css = "div#profile-pic h1")
	public WebElement userFirstLastName;
	
	@Name("Gender Male")
	@FindBy(id = "personal_optGender_1")
	public WebElement genderMale;
	
	@Name("Gender Female")
	@FindBy(id = "personal_optGender_2")
	public WebElement genderFemale;
	
	@Name("Personal Detail Successfully Saved massage")
	@FindAll(@FindBy (css = "div.message.success.fadable"))
	public List <WebElement> successfullyMessage;
	
	@FindBy (id = "personal_cmbMarital")
	public WebElement maritalStatusExpander;
	
	@FindBy(css = "select#personal_cmbMarital option")
	public List <WebElement> maritalStatusElements;
}
