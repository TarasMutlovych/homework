package htmlElementsOrangeEnterprice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Left Side Menu")
@FindBy(id = "sidenav")
public class MoreInfoLeftSideMenu extends HtmlElement {

	@Name("Personal Details")
	@FindBy(xpath = ".//a[contains(@href,'viewPersonalDetails')]")
	public WebElement personalDetails;
	
	@Name("Social Media Details")
	@FindBy(xpath = ".//a[contains(@href,'viewEmployeeSocialMediaDetails')]")
	public WebElement socialMediaDetails;
	
	@Name("Contact Details")
	@FindBy(xpath = ".//a[contains(@href,'contactDetails')]")
	public WebElement contactDetails;
	

	@Name("Emergency Contacts")
	@FindBy(xpath = ".//a[contains(@href,'viewEmergencyContacts')]")
	public WebElement emergencyContacts;

	@Name("Dependents")
	@FindBy(xpath = ".//a[contains(@href,'viewDependents')]")
	public WebElement dependents;

	@Name("Immigration")
	@FindBy(xpath = ".//a[contains(@href,'viewImmigration')]")
	public WebElement immigration;

	@Name("Job")
	@FindBy(xpath = ".//a[contains(@href,'JobDetails')]")
	public WebElement job;

	@Name("Salary List")
	@FindBy(xpath = ".//a[contains(@href,'viewSalaryList')]")
	public WebElement salaryList;

	@Name("Report To")
	@FindBy(xpath = ".//a[contains(@href,'viewReportToDetails')]")
	public WebElement reportTo;

	@Name("Qualifications")
	@FindBy(xpath = ".//a[contains(@href,'viewQualifications')]")
	public WebElement qualifications;

	@Name("Memberships")
	@FindBy(xpath = ".//a[contains(@href,'viewMemberships')]")
	public WebElement memberships;

	@Name("Direct Deposit")
	@FindBy(xpath = ".//a[contains(@href,'viewDirectDepositDetails')]")
	public WebElement directDeposit;
}
