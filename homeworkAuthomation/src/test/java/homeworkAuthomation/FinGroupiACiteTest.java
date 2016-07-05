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
	private String language = "EN";
	private int pointsToMovePurchasePriceSlider = 60;
	private int desiredPurchasePrice = 500000;
	private int desiredDownPayment = 50000;
	private String amortization = "15";
	private String paymentFrequency = "weekly";
	private String desiredRate = "5";
	private String expectedPayments = "$ 836.75";

	@BeforeClass
	public void setUp() {
		//browser = Browser.openChrome();
		browser = Browser.openFirefox();
		industrialAllianceStartPage = IndustrialAllianceStartPage.openStartPage(browser, theSiteURL);
		industrialAllianceStartPage.verifyTheLanguage(language);
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

	@Test(priority = 5)
	public void verifySliderMoving() {
		mortgagePaymentCalculator.verifyTheSliderMoving();
	}

	@Test(priority = 6)
	public void changePurchasePrice() {
		mortgagePaymentCalculator.changePurchasePrice(desiredPurchasePrice);
	}

	@Test(priority = 7)
	public void changeDownPayment() {
		mortgagePaymentCalculator.changeDownPayment(desiredDownPayment);
	}

	@Test(priority = 8)
	public void selectAmortization() {
		mortgagePaymentCalculator.selectAmortizaion(amortization);
	}

	@Test(priority = 9)
	public void selectPaymentFrequency() {
		mortgagePaymentCalculator.selectPaymentFrequency(paymentFrequency);
	}

	@Test(priority = 10)
	public void changeInterestRate() {
		mortgagePaymentCalculator.changeInterestRate(desiredRate);
	}

	@Test(priority = 11)
	public void calculate() {
		mortgagePaymentCalculator.calculate();
	}

	@Test(priority = 12)
	public void verifyThePayments() {
		Assert.assertEquals(mortgagePaymentCalculator.getPayments(), expectedPayments,
				"The payments are not as expected");
	}

	@AfterClass
	public void tearDown() {
		Browser.close();
	}

}
