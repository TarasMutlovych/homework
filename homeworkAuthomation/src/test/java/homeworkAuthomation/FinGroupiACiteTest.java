package homeworkAuthomation;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import libraries.Browser;
import sourceCode.IndustrialAllianceStartPage;
import sourceCode.MortgagePage;
import sourceCode.MortgagePaymentCalculatorPage;

public class FinGroupiACiteTest {

	IndustrialAllianceStartPage industrialAllianceStartPage;
	MortgagePage mortgagePage;
	MortgagePaymentCalculatorPage mortgagePaymentCalculator;

	private String theSiteURL = "http://www.ia.ca/";
	private int pointsToMovePurchasePriceSlider = 60;

	@BeforeClass
	public void setUp() {
		// Browser.openFirefox();
		Browser.openChrome1();
		industrialAllianceStartPage = Browser.openStartPage1(theSiteURL);
	}

	@Test(priority = 1)
	public void openLoansDropdown() {
		industrialAllianceStartPage.clickLoansButton();
	}

	@Test(priority = 2)
	public void clickMortagesButton() {
		mortgagePage = industrialAllianceStartPage.clickMortagesButton();
	}

	@Test(priority = 3)
	public void clickCalculateYourPayments() {
		mortgagePaymentCalculator = mortgagePage.clickCalculateYourPaymentsButton();
	}

	@Test(priority = 4)
	public void moveSliiderToTheRight() {
		mortgagePaymentCalculator.moveSlider(pointsToMovePurchasePriceSlider);
	}

	@Test (priority =5)
		public void verifySliderMoving() {
		}
	
	@AfterClass
	public void tearDown() {
		Browser.close();
	}
	
}
