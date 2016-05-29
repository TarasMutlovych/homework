package sourceCode;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import libraries.Browser;
import ru.yandex.qatools.allure.annotations.Step;

public class MortgagePaymentCalculatorPage {

WebDriver driver;
	
	public MortgagePaymentCalculatorPage (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	private Point location1;
	private Point location2;
	
	@FindBy(css = "div.row.fond-formulaire.bleu-pale + div.row div.slider-track div.slider-handle.min-slider-handle.custom")
	WebElement purchasePriceSlider;
	
	@Step
	public void moveSlider(int SliderPoints) {
		Browser.waitForVisibility(driver, purchasePriceSlider);
		location1 =  purchasePriceSlider.getLocation();
		Browser.moveSliderToRight(driver, purchasePriceSlider, SliderPoints);
		location2 =  purchasePriceSlider.getLocation();
	}
	
	@Step
	public void verifyTheSliderMoving() {
		Assert.assertNotEquals(location1, location2, "Slider is not moved");
	}
	
}
