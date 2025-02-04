package genericLibraries;

import java.io.IOException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners(Listener1.class)
public class BaseClass {

	public static Logger logger = LogManager.getLogger(BaseClass.class);;

	public static WebDriver driver;
	public DataUtilities dataUtilities = new DataUtilities();
	public WebDriverUtilities utilities = new WebDriverUtilities();

	@BeforeClass
	public void openApp() throws EncryptedDocumentException, IOException {
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get(dataUtilities.readExcelFile("Sheet1", 1, 0));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@AfterClass
	public void closeApp() {
		driver.quit();
	}

}
