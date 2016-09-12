package orangeEnterprice;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MyInfoTest {

	UserPage userPage;
	private String userRole = "EssUser";
	//private String userRole = "1stSupervisor";
	
	private String defultSection = "Personal Details";
	
	
	@BeforeClass
	public void setUp () {
		userPage = OrangeEnterprice.openTheSite().loginAs(userRole);
	}
	
	@Test(priority = 1)
	public void verifyTheDefaultSection() {
		userPage.openMyInfo();
		userPage.verifyThatDefaultSectionIs(defultSection);		
	}

	@Test(priority = 2)
	public void verifyEditSaveButtonTextChanging () {
		userPage.clickEditButton();
		userPage.verifyEditButtonTextIsChangedToSave();
	}
	
	@Test(priority = 3)
	public void verifyMandatoryFirstLastNameFields () {
		userPage.clickEditButton();
		userPage.testFirstNameMandatoryfield();
		userPage.clickEditButton();
		userPage.testLastNameMandatoryfield();
	}
}
