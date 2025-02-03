package testScripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import genericLibraries.BaseClass;
import pom.Header;
import pom.LoginPage;
import pom.NavBar;
import pom.ProductDetailsPage;
import pom.ProductsPage;

public class TC_03 extends BaseClass {
	@Test
	public void testCase3() throws EncryptedDocumentException, IOException {

		logger.info("Click Login/Signup");
		Header header = new Header(driver);
		header.clickLoginSignupButton();

		logger.info("Enter Username and password");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.emailAddressTextBox(dataUtilities.readExcelFile("Sheet1", 1, 1));
		loginPage.passwordTextBox(dataUtilities.readExcelFile("Sheet1", 1, 2));

		logger.info("Click Login");
		loginPage.clickLoginButton();

		logger.info("Verify the profile page");
		String accountTitle = dataUtilities.readPropertyFile("accountTitle");
		assertEquals(driver.getTitle(), accountTitle);

		logger.info("MouseOver on Mens section");
		NavBar navBar = new NavBar(driver);
		WebElement mensOption = navBar.hoverMens();
		utilities.mouseHover(mensOption, driver);

		logger.info("Click on Round Neck Sweaters");
		navBar.clickRoundNeckSweaters();

		logger.info("Click on size filter");
		assertTrue(driver.getTitle().contains(navBar.getRoundNeckSweatersTitle()), "Rounded Neck Page Not Found!");

		ProductsPage products = new ProductsPage(driver);
		products.clickSizeBtn(driver);

		logger.info("Select 38 / M(94)");
		products.clickSize38Product(driver);

		logger.info("Scroll down & Click on Brand filter");
		products.clickBrandBtn(driver);

		logger.info("Select the checkbox of Rock.it");
		products.clickRockItBrandOption(driver);

		logger.info("Scroll down & Click on sleeve filter");
		products.clickSleeveBtn(driver);

		logger.info("Select the checkbox of Full Sleeve");
		products.clickFullSleeveOption(driver);

		logger.info("Select the third product");
		products.selectAnyProduct(3, driver);

		logger.info("Scroll & Enter the pincode in Check Pincode TextBox");
		ProductDetailsPage productDetail = new ProductDetailsPage(driver);
		assertEquals(productDetail.getProductTitle(), dataUtilities.readPropertyFile("productName"));

		productDetail.enterPinCode("560100");

		logger.info("Click on Check pincode");
		productDetail.clickCheckPincodeBtn();
	}

}
