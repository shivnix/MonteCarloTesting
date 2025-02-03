package testScripts;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import genericLibraries.BaseClass;
import pom.AddressPage;
import pom.Header;
import pom.LoginPage;
import pom.UserAccountPage;

public class TC_07 extends BaseClass {
	@Test
	public void testCase07() throws EncryptedDocumentException, IOException {
		logger.info("Click on Login/Signup.");
		Header header = new Header(driver);
		header.clickLoginSignupButton();

		logger.info("Enter username and password.");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.emailAddressTextBox(dataUtilities.readExcelFile("Sheet1", 1, 1));
		loginPage.passwordTextBox(dataUtilities.readExcelFile("Sheet1", 1, 2));

		logger.info("Click on Login.");
		loginPage.clickLoginButton();

		logger.info("Verify whether you are on Account page.");
		String accountTitle = dataUtilities.readPropertyFile("accountTitle");
		assertEquals(driver.getTitle(), accountTitle);

		logger.info("Click on addresses.");
		UserAccountPage userAccount = new UserAccountPage(driver);
		userAccount.clickAddressesBtn();

		logger.info("Click on delete address.");
		AddressPage addPage = new AddressPage(driver);

		addPage.clickDeleteAddressBtn();

//		if(addPage.checkAddress(dataUtilities.readPropertyFile("add1"))) {
//			addPage.clickDeleteAddressBtn();
//		}

		utilities.alertAcceptPopup(driver);

//		logger.info("Verify the address is deleted");
//		boolean checkAddress = addPage.checkAddress(dataUtilities.readPropertyFile("add1"));
//		assertTrue(checkAddress, "Address Not Found");

	}
}
