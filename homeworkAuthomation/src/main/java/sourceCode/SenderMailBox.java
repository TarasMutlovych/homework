package sourceCode;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import libraries.Browser;

public class SenderMailBox {

	WebDriver driver;

	SenderMailBox(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "gmail-sign-in")
	WebElement signInButton;

	@FindBy(id = "Email")
	WebElement emailField;

	@FindBy(css = "input[type = 'submit']")
	WebElement submitCredential;

	@FindBy(id = "Passwd")
	WebElement passField;

	@FindBy(css = "div.T-I.J-J5-Ji.T-I-KE.L3")
	WebElement composeButton;

	@FindBy(xpath = "//input[@name = 'subjectbox']/parent::div/preceding-sibling:: div//textarea")
	WebElement recipientEmail;

	@FindBy(xpath = "//input[@name = 'subjectbox']")
	WebElement subjectField;

	@FindBy(xpath = "//input[@name = 'subjectbox']/ancestor::form/following-sibling::table//div[@role = 'textbox']")
	WebElement letterBody;

	@FindBy(xpath = "//div[@data-tooltip = 'Attach files']")
	WebElement attachFileButton;

	@FindBy(xpath = "//div[@data-tooltip = 'Send ‪(Ctrl-Enter)‬']")
	WebElement sendLetterButton;

	@FindBy(css = "div[role = 'navigation'] a[title = 'Sent Mail']")
	WebElement sentMailButton;

	@FindBy(xpath = "//a[contains(@title, 'Google Account')]")
	WebElement googleAccountButton;

	@FindBy(xpath = "//a[@href = 'https://accounts.google.com/AddSession?hl=en&continue=https://mail.google.com/mail&service=mail']")
	WebElement addAccountButton;

	public static SenderMailBox openTheLogInPage(WebDriver driver, String URL) {
		driver.get(URL);
		return new SenderMailBox(driver);
	}

	public void clickSignInBUtton() {
		Browser.waitForVisibility(driver, signInButton);
		signInButton.click();
	}

	public void logIntoTheMailBox(String email, String pass) {
		Browser.waitForVisibility(driver, emailField);
		emailField.sendKeys(email);
		submitCredential.submit();
		Browser.waitForVisibility(driver, passField);
		passField.sendKeys(pass);
		passField.submit();
	}

	public void createAndSentLetted(String email, String subject, String body) {
		Browser.waitForVisibility(driver, composeButton);
		composeButton.click();

		Browser.waitForElementToBeClickable(driver, recipientEmail);
		recipientEmail.sendKeys(email);
		subjectField.sendKeys(subject);
		letterBody.sendKeys(body);

		attachFileButton.click();

		String userDir = System.getProperty("user.dir");
		String pathToFile = userDir + "\\testware\\files\\test.jpg";
		StringSelection ss = new StringSelection(pathToFile);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		sendLetterButton.click();
	}

	public void openSentMail() {
		Browser.moveToElementAndClick(driver, sentMailButton);
	}

	public void verifyThatLetterIsPresent(String subject) {
		Browser.waitForVisibility(driver, driver.findElement(By.xpath("//span[contains(text(),'" + subject + "')]")));
	}

	public RecipientMailBox addNewAccount() {
		googleAccountButton.click();
		addAccountButton.click();
		return new RecipientMailBox(driver);
	}
}
