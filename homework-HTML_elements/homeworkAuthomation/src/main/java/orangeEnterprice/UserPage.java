package orangeEnterprice;

public interface UserPage {

	/**
	 * Method for opening My Info page
	 */
	public void openMyInfo();

	/**
	 * Metod for verifiation that default section is opened after clicking
	 * "My info" on menu nav bar
	 */
	public void verifyThatDefaultSectionIs(String defultSection);

	/**
	 * Clicking "Edit" button on Personal Details section of My Info => Personal
	 * Details page
	 */
	public void clickEditButton();

	/**
	 * Verifying the text changing of edit button after clicking it
	 */
	public void verifyEditButtonTextIsChangedToSave();

	/**
	 * Method for verification mandatory First Name field (notification below
	 * the field should be shown)
	 */
	public void testFirstNameMandatoryfield();

	/**
	 * Method for verification mandatory First Name field (notification below
	 * the field should be shown)
	 */
	public void testLastNameMandatoryfield();

	/**
	 * Setting First name on Personal Details section of My Info => Personal
	 * Details page
	 * 
	 * @param firstName
	 *            to set
	 */
	public void setFirstName(String firstName);

	/**
	 * Setting Last name on Personal Details section of My Info => Personal
	 * Details page
	 * 
	 * @param firstName
	 *            to set
	 */
	public void setLastName(String lastName);

	/**
	 * Verifying that changed name is displaying on My info = =>Personal Details
	 * page above the profile picture
	 * 
	 * @param firstName
	 *            entered Name
	 * @param lastName
	 *            entered Surname
	 */
	public void verifyThatNameisCahngedAbobeTheAvatar(String firstName, String lastName);

	/**
	 * Method for selecting "Male" Gender on My Info => Personal Details page
	 */
	public void tickMaleGender();

	/**
	 * Method for selecting "Female" Gender on  My Info =>
	 *  Personal Details page
	 */
	public void tickFemaleGender();
	
	/**
	 * Verification of gender changing
	 * @param correctGender
	 */
	public void verifyThatGenderChangingsIsSaved (String correctGender);
	
	/**
	 * Expanding Marital Status Dropdown
	 */
	public void expandMaritalStatusDropdown();
	
	public void verifyMaritalOptions (); 
}
