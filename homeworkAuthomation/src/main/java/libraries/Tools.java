package libraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Tools  {

	//Створення і видалення Excel
	// Start page + child
	// Allure report + screenshots for BankCiteTest
	
	public static void createExcelAndWrite (String fileDestination, String fileName, ArrayList <String> list) {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Sample sheet");
		
		int listSize = list.size();
		
		for (int i = 0; i < listSize; i++) {
		String valueToWrite = list.get(i);
		Row row = sheet.createRow(i); 
		 org.apache.poi.ss.usermodel.Cell cell = row.createCell(0);
		 cell.setCellValue(valueToWrite);
		 }
		
		try {
		    FileOutputStream out = 
		            new FileOutputStream(new File(fileDestination +"\\" + fileName));
		    workbook.write(out);
		    out.close();
		    System.out.println("Excel written successfully..");
		     
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}}
	
	public static int getNumberOfFilledExcellRows (String fileDestination, String fileName) throws IOException {
		FileInputStream fis = new FileInputStream(fileDestination +"\\" + fileName);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = wb.getSheetAt(0);

		int rowNum = sheet1.getLastRowNum();
		 int cityQuantityFromExcel = rowNum + 1;
		 return cityQuantityFromExcel;
	}
	
	public static void deleteTheFile (String fileDestination, String fileName) {
		File fExcel = new File(fileDestination + "//" +  fileName);
		fExcel.delete();
	}

}
