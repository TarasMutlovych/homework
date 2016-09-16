package orangeEnterprice;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MyInfoTest {

	UserPage userPage;
	private String userRole = "EssUser";
	//private String userRole = "1stSupervisor";
	
	private String defultSection = "Personal Details";
	private String firstName = "Tom";
	private String lastName = "Black";
	private String [] maritalStatusOptions = {"-- Select--", "Single", "Married", "Other"};
	
	@BeforeClass
	public void setUp () {
		userPage = OrangeEnterprice.openTheSite().loginAs(userRole);
	}
	
	@Test(priority = 1)
	public void verifyTheDefaultSection() {
		userPage.openMyInfo();
		//userPage.verifyThatDefaultSectionIs(defultSection);		
	}
/*
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
	
	@Test(dependsOnMethods = "verifyTheDefaultSection") 
	public void verifyFirstLastNameChanging () {
		userPage.clickEditButton();
		userPage.setFirstName(firstName);
		userPage.setLastName(lastName);
		userPage.clickEditButton();
		userPage.verifyThatNameisCahngedAbobeTheAvatar(firstName, lastName);
	}
	
	@Test(dependsOnMethods = "verifyTheDefaultSection") 
	public void verifyTheGenderChanging () {
		userPage.clickEditButton();
		userPage.tickMaleGender();
		userPage.clickEditButton();
		userPage.verifyThatGenderChangingsIsSaved("Male");
		
		userPage.clickEditButton();
		userPage.tickFemaleGender();
		userPage.clickEditButton();
		userPage.verifyThatGenderChangingsIsSaved("Female");
	}
	*/
	@Test(dependsOnMethods = "verifyTheDefaultSection") 
	public void verifyTheMaritalStatus () {
		userPage.clickEditButton();
		userPage.expandMaritalStatusDropdown();
		userPage.verifyMaritalOptions();
	}
}
