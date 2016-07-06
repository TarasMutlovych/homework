package homeworkAuthomation;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import libraries.Browser;
import sourceCode.IndustrialAllianceStartPage;
import sourceCode.MortgagePage;
import sourceCode.MortgagePaymentCalculatorPage;

public class FinGroupiACiteTest {

	private WebDriver browser;
	IndustrialAllianceStartPage industrialAllianceStartPage;
	MortgagePage mortgagePage;
	MortgagePaymentCalculatorPage mortgagePaymentCalculator;

	private String theSiteURL = "http://www.ia.ca/";
	private String language = "FR";
	//private String language = "EN";
	private int pointsToMovePurchasePriceSlider = 60;
	private int desiredPurchasePrice = 500000;
	private int desiredDownPayment = 50000;
	private String amortization = "15";
	private String paymentFrequency = "weekly";
	private String desiredRate = "5";
	private String expectedPayments = "836.75";

	@BeforeClass
	public void setUp() {
		browser = Browser.openChrome();
		//browser = Browser.openFirefox();
		industrialAllianceStartPage = IndustrialAllianceStartPage.openStartPage(browser, theSiteURL);
		industrialAllianceStartPage.verifyTheLanguage(language);
	}

	@Test(priority = 1)
	public void openMortgagePaymentCalculatorPage() {
		industrialAllianceStartPage.clickLoansButton();
		mortgagePage = industrialAllianceStartPage.clickMortagesButton();
		mortgagePaymentCalculator = mortgagePage.clickCalculateYourPaymentsButton();
	}

	@Test(priority = 2)
	public void verifyTheSliderMoving() {
		mortgagePaymentCalculator.moveSlider(pointsToMovePurchasePriceSlider);
		mortgagePaymentCalculator.verifyTheSliderMoving();
	}

	@Test(priority = 3)
	public void verifyThePayments() {
		mortgagePaymentCalculator.changePurchasePrice(desiredPurchasePrice);
		mortgagePaymentCalculator.changeDownPayment(desiredDownPayment);
		mortgagePaymentCalculator.selectAmortizaion(amortization);
		mortgagePaymentCalculator.selectPaymentFrequency(paymentFrequency);
		mortgagePaymentCalculator.changeInterestRate(desiredRate);
		mortgagePaymentCalculator.calculate();
	
		Assert.assertEquals(mortgagePaymentCalculator.getPayments(), expectedPayments,
				"The payments are not as expected");
	}

	@AfterClass
	public void tearDown() {
		Browser.close();
	}

}
