package sourceCode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import libraries.Browser;
import ru.yandex.qatools.allure.annotations.Step;

public class DestinationsPage {

	WebDriver driver;

	public DestinationsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "p.firstpar > a:nth-of-type(4)")
	WebElement destinationsCities;

	@FindBy(css = "div.block_third > div.block_header a:nth-of-type(1)")
	List<WebElement> foundCities;

	@Step
	public void clickdestinationsCities() {
		Browser.waitForVisibility(driver, destinationsCities);
		destinationsCities.click();
	}

	@Step
	public void verifyCitiesNumber() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElements(foundCities));
		
		int size = foundCities.size();
		ArrayList<String> cities = new ArrayList<String>();
		
		for (int i = 0; i < size; i++) {
			String city = foundCities.get(i).getText();
			cities.add(i, city);
		}

		File fExcel = new File("C:\\Users\\User-PC\\git\\City.xlsx");
		/*
		 * FileInputStream fis = new FileInputStream(fExcel); XSSFWorkbook wb =
		 * new XSSFWorkbook(fis); XSSFSheet sheet1 = wb.getSheet("Data"); /*
		 * sheet1.createRow(0).createCell(0).setCellValue("Cities");
		 * FileOutputStream fos = new FileOutputStream(fExcel); wb.write(fos);
		 */
		for (int i = 0; i < size; i++) {
			String cityToWrite = cities.get(i);
			FileInputStream fis = new FileInputStream(fExcel);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet1 = wb.getSheetAt(0);
			sheet1.createRow(i).createCell(0).setCellValue(cityToWrite);
			FileOutputStream fos = new FileOutputStream(fExcel);
			wb.write(fos);
			wb.close();
		}

		FileInputStream fis = new FileInputStream(fExcel);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = wb.getSheetAt(0);

		int rowNum = sheet1.getLastRowNum();
		int cityQuantityFromExcel = rowNum + 1;
		int cityQuantityFromCite = size;
		Assert.assertEquals(cityQuantityFromExcel, cityQuantityFromCite, "The quantity of Hotels is not the same");

	}

}
