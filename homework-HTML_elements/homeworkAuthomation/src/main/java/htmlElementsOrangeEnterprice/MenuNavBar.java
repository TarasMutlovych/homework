package htmlElementsOrangeEnterprice;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("MenuNavBar")
@FindBy(className = "menu")
public class MenuNavBar extends HtmlElement {

	@Name("PIM")
	@FindBy(id = "menu_pim_viewPimModule")
	public WebElement pimButton;

	@Name("Leave")
	@FindBy(id = "menu_leave_viewLeaveModule")
	public WebElement leaveButton;

	@Name("Time")
	@FindBy(id = "menu_time_viewTimeModule")
	public WebElement timeButton;

	@Name("Recruitment")
	@FindBy(id = "menu_recruitment_viewRecruitmentModule")
	public WebElement recruitmentButton;

	@Name("Disciplinary")
	@FindBy(id = "menu_discipline_defaultDisciplinaryView")
	public WebElement disciplinaryButton;

	@Name("MyInfo")
	@FindBy(id = "menu_pim_viewMyDetails")
	public WebElement myInfoButton;

	@Name("Configuration")
	@FindBy(id = "menu_admin_Configuration")
	WebElement configuration;

	@Name("Training")
	@FindBy(id = "menu_training_defaultTrainingModulePage")
	WebElement training;

	@Name("Performance")
	@FindBy(id = "menu_performance_viewMyAppraisals")
	WebElement performance;
	
	@Name("onBoarding")
	@FindBy(id = "menu_onboarding_defaultMenuView")
	WebElement onBoarding;
	
	@Name("More")
	@FindBy(id = "menu_dashboard_index")
	WebElement more;
	
}
