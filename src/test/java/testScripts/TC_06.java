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

public class TC_06 extends BaseClass {
	@Test
	public void testCase06() throws EncryptedDocumentException, IOException {
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

		logger.info("Click on add a new address.");
		AddressPage addPage = new AddressPage(driver);
		addPage.clickAddNewAddressBtn();

		logger.info("Enter all the details");
		addPage.enterFirstName(dataUtilities.readPropertyFile("firstName"));
		addPage.enterLastName(dataUtilities.readPropertyFile("lastName"));
		addPage.enterCompany(dataUtilities.readPropertyFile("companyName"));
		addPage.enterPhone(dataUtilities.readPropertyFile("phoneNum"));
		addPage.enterAddressOne(dataUtilities.readPropertyFile("add1"));
		addPage.enterAddressTwo(dataUtilities.readPropertyFile("add2"));
		addPage.enterCity(dataUtilities.readPropertyFile("cityName"));
		addPage.enterZip(dataUtilities.readPropertyFile("zipCode"));

		logger.info("Click on Set it as default address.");
		addPage.clickDefaultCheckBox();

		logger.info("Click on add a new address.");
		addPage.clickAddNewAddressSideBar();

//		logger.info("Verify weather the new address is added or not.");
//		boolean checkAddress = addPage.checkAddress(dataUtilities.readPropertyFile("add1"));
//		assertTrue(checkAddress, "Address Not Found");

	}
}
