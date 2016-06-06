package sourceCode;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import libraries.Browser;
import libraries.Tools;
import ru.yandex.qatools.allure.annotations.Step;

public class MortgagePaymentCalculatorPage {

	WebDriver driver;

	public MortgagePaymentCalculatorPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private Point location1;
	private Point location2;

	@FindBy(css = "div.row.fond-formulaire.bleu-pale + div.row div.slider-track div.slider-handle.min-slider-handle.custom")
	WebElement purchasePriceSlider;

	@FindBy(css = "input[name = 'PrixPropriete']")
	WebElement purchasePriceInnput;

	@FindBy(xpath = "(//div[@class = 'slider-tick-label-container'])[1]/div[@class = 'slider-tick-label'][last()]/span")
	WebElement maxPurchasePrice;

	@FindBy(css = "button.btn-slider.icone-tuile-plus")
	WebElement purchasePricePlusButton;

	@FindBy(css = "input[name = 'MiseDeFond']")
	WebElement downPaymentInput;

	@FindBy(xpath = "(//div[@class = 'slider-tick-label-container'])[2]/div[@class = 'slider-tick-label'][last()]/span")
	WebElement maxDownPayment;

	@FindBy(xpath = "(//button[@class = 'btn-slider icone-tuile-plus'])[2]")
	WebElement downPaymentPlusButton;

	@FindBy(css = "select[name = 'Amortissement']")
	WebElement amortizationDroplist;

	@FindBy(css = "select[name = 'FrequenceVersement']")
	WebElement paymentFrequencyDroplist;

	@FindBy(css = "select[name = 'FrequenceVersement'] option")
	List<WebElement> frequencyOptionsWebelements;

	@FindBy(css = "input[name = 'TauxInteret']")
	WebElement inputInterestRate;

	/*
	 * @FindBy(css = "button[class = 'btn-trouver'][id = 'btn_calculer']")
	 * WebElement calculateButton;
	 */

	@FindBy(css = "span.calculateur-resultats-total")
	WebElement payments;

	@FindBy(css = "form[data-utag-fname = 'form_calculator_mortgagesloan']")
	WebElement calculationForm;

	@Step
	public void moveSlider(int SliderPoints) {
		Browser.scrollThePageDown(driver);
		Browser.waitForVisibility(driver, purchasePriceSlider);
		location1 = purchasePriceSlider.getLocation();
		Browser.moveSliderToRight(driver, purchasePriceSlider, SliderPoints);
		location2 = purchasePriceSlider.getLocation();
	}

	@Step
	public void verifyTheSliderMoving() {
		Assert.assertNotEquals(location1, location2, "Slider is not moved");
	}

	@Step
	public void changePurchasePrice(int desiredPurchasePrice) {
		Browser.waitForVisibility(driver, purchasePriceInnput);
		purchasePriceInnput.clear();

		String maxPriceText = maxPurchasePrice.getText();
		String[] numbersArray = maxPriceText.split(" "); 
		String maxPriceTextWithoutSpaces = numbersArray[0];
		int listOfValues = numbersArray.length;
		for (int i = 1; i < listOfValues; i++) {
			maxPriceTextWithoutSpaces = maxPriceTextWithoutSpaces + numbersArray[i];
		}

		int maxPrice = Integer.parseInt(maxPriceTextWithoutSpaces);
		int currentPurchasePrice = Integer.parseInt(purchasePriceInnput.getAttribute("value"));

		if (desiredPurchasePrice > maxPrice) {
			System.out.println("You were trying to set PurchasePrice bigger than maximum allowed.");
			System.out.println("Maximum Purchase Price " + maxPrice + " was selected");

		} else {

			while (currentPurchasePrice <= maxPrice) {
				Browser.moveToElementAndClick(driver, purchasePricePlusButton);
				currentPurchasePrice = Integer.parseInt(purchasePriceInnput.getAttribute("value"));
				if (currentPurchasePrice == desiredPurchasePrice) {
					break;
				} else {
				}
			}
		}

		/*
		 * for (int i = 0; i <4; i++) { Browser.moveToElementAndClick(driver,
		 * purchasePricePlusButton); int currentPurchasePrice =
		 * Integer.parseInt(purchasePriceInnput.getAttribute("value")); if
		 * (currentPurchasePrice == desiredPurchasePrice) { break; } else { }
		 * String finalv = purchasePriceInnput.getAttribute("value");
		 * System.out.println(finalv); }
		 */
	}

	@Step
	public void changeDownPayment(int desiredDownPayment) {
		Browser.waitForVisibility(driver, downPaymentInput);
		downPaymentInput.clear();

		String maxDownPaymentText = maxDownPayment.getText();
		String[] numbersArray = maxDownPaymentText.split(" ");
		String maxDownPaymentWithoutSpaces = numbersArray[0];
		int listOfValues = numbersArray.length;
		for (int i = 1; i < listOfValues; i++) {
			maxDownPaymentWithoutSpaces = maxDownPaymentWithoutSpaces + numbersArray[i];
		}

		int maxPayment = Integer.parseInt(maxDownPaymentWithoutSpaces);
		int currentDownPayment = Integer.parseInt(downPaymentInput.getAttribute("value"));

		if (desiredDownPayment > maxPayment) {
			System.out.println("You were trying to set Down Payment bigger than maximum allowed.");
			System.out.println("Maximum Down Payment " + maxPayment + " was selected");

		} else {
			while (currentDownPayment <= maxPayment) {
				Browser.moveToElementAndClick(driver, downPaymentPlusButton);
				currentDownPayment = Integer.parseInt(downPaymentInput.getAttribute("value"));
				if (currentDownPayment == desiredDownPayment) {
					break;
				} else {
				}
			}
		}
	}

	@Step
	public void selectAmortizaion(String option) {
		Browser.waitForVisibility(driver, amortizationDroplist);
		Tools.selectOptionFromDroplist(amortizationDroplist, option);
	}

	@Step
	public void selectPaymentFrequency(String option) {
		ArrayList<String> frequencyOptions = new ArrayList<String>();
		int numsOfOptions = frequencyOptionsWebelements.size();
		for (int i = 0; i < numsOfOptions; i++) {
			frequencyOptions.add(i, frequencyOptionsWebelements.get(i).getAttribute("value"));
		}

		String[] inputOptionsEn = { "Monthly", "Biweekly", "Biweekly +", "weekly", "weekly +" };
		String[] inputOptionsFr = { "Mensuel", "la quinzaine", " la quinzaine +", "Hebdomadaire", "Hebdomadaire +" };

		for (int i = 0; i < numsOfOptions; i++) {
			if (option == inputOptionsEn[i]) {
				String frequency = frequencyOptions.get(i);
				Tools.selectOptionFromDroplist(paymentFrequencyDroplist, frequency);
			} else {
				if (option.contains(inputOptionsFr[i])) {
					String frequency = frequencyOptions.get(i);
					Tools.selectOptionFromDroplist(paymentFrequencyDroplist, frequency);
				}
			}
		}

	}

	@Step
	public void changeInterestRate(String desiredRate) {
		inputInterestRate.clear();
		inputInterestRate.sendKeys(desiredRate);
	}

	// 0677100937
	@Step
	public void calculate() {
		calculationForm.submit();
		Browser.scrollThePageUp(driver);

	}

	@Step
	public String getPayments() {
		Browser.waitForVisibility(driver, payments);
		return payments.getText();
	}
}
