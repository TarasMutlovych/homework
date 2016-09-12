package orangeEnterprice;

public interface UserPage {

	/**
	 * Method for opening My Info page
	 */
	public void openMyInfo ();

	public void verifyThatDefaultSectionIs(String defultSection);
	
	public void clickEditButton();
	
	public void verifyEditButtonTextIsChangedToSave ();
	
	/**
	 * Method for verification mandatory First Name field
	 */
	public void testFirstNameMandatoryfield ();

	public void testLastNameMandatoryfield ();
}
