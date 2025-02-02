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
//		4)Click on Login/Signup.
		Header header = new Header(driver);
		header.clickLoginSignupButton();

//		5)Enter username and password.
		LoginPage loginPage = new LoginPage(driver);
		loginPage.emailAddressTextBox(dataUtilities.readExcelFile("Sheet1", 1, 1));
		loginPage.passwordTextBox(dataUtilities.readExcelFile("Sheet1", 1, 2));

//		6)Click on Login.
		loginPage.clickLoginButton();

//		7)Verify whether you are on Account page.
		String accountTitle = dataUtilities.readPropertyFile("accountTitle");
		assertEquals(driver.getTitle(), accountTitle);

//		8)Click on addresses.
		UserAccountPage userAccount = new UserAccountPage(driver);
		userAccount.clickAddressesBtn();

//		9)Click on delete address.
		AddressPage addPage = new AddressPage(driver);
		
		addPage.clickDeleteAddressBtn();
		
//		if(addPage.checkAddress(dataUtilities.readPropertyFile("add1"))) {
//			addPage.clickDeleteAddressBtn();
//		}

		utilities.alertAcceptPopup(driver);

//		10)verify the address is deleted
//		boolean checkAddress = addPage.checkAddress(dataUtilities.readPropertyFile("add1"));
//		assertTrue(checkAddress, "Address Not Found");

	}
}
