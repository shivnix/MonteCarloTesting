package genericLibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DataUtilities {

	public String readPropertyFile(String data) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(AutoConstant.propertyFilePath));
		return properties.getProperty(data);
	}

	public String readExcelFile(String sheetName, int rowNumber, int cellNumber)
			throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(AutoConstant.excelFilePath);
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet(sheetName);
		return sheet.getRow(rowNumber).getCell(cellNumber).getStringCellValue();
	}
	
	public void writePropertyFile(String key, String value) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(AutoConstant.propertyFilePath));
		properties.setProperty(key, value);
		properties.store(new FileOutputStream(AutoConstant.propertyFilePath), "Updated File");
	}

}
