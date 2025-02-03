package testScripts;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;

import genericLibraries.BaseClass;
import pom.Header;
import pom.LoginPage;
import pom.NavBar;
import pom.ProductDetailsPage;
import pom.ProductsPage;

public class TC_09 extends BaseClass {
	@Test
	public void testCase09() throws FileNotFoundException, IOException {
		logger.info("Click on Login");
		Header header = new Header(driver);
		header.clickLoginSignupButton();

		logger.info("Enter Username and password");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.emailAddressTextBox(dataUtilities.readExcelFile("Sheet1", 1, 1));
		loginPage.passwordTextBox(dataUtilities.readExcelFile("Sheet1", 1, 2));
		loginPage.clickLoginButton();

		logger.info("Verify the homepage");
		String accountTitle = dataUtilities.readPropertyFile("accountTitle");
		assertEquals(driver.getTitle(), accountTitle);

		logger.info("Click on cart");
		NavBar navBar = new NavBar(driver);
		navBar.clickCartButton();

		logger.info("Click on start Shopping");
		navBar.clickStartShoppingBtn();

		logger.info("Select Filters : Date Old to New");
		ProductsPage productsPage = new ProductsPage(driver);
		productsPage.clickSortByBtn();
		productsPage.selectDateOldToNewOption();

		logger.info("Click on the Product");
		productsPage.selectAnyProduct(5, driver);

		logger.info("Click on add to cart");
		ProductDetailsPage productDetails = new ProductDetailsPage(driver);
		String productName = dataUtilities.readPropertyFile("productName");
		assertEquals(productDetails.getProductTitle(), productName);

		productDetails.clickAddToCartBtn();

	}

	@Test(dependsOnMethods = "testCase09")
	public void testCase10() throws FileNotFoundException, IOException, InterruptedException {
		NavBar navBar = new NavBar(driver);
		utilities.waitElement(navBar.getAddOrderNoteButton(), driver);

		logger.info("Click on add/Edit Order Note");
		navBar.clickAddOrderNoteBtn();

		logger.info("Enter the desired text");
		navBar.enterAddNote("This is my order");

		logger.info("Click on save");
		navBar.clickSaveNoteBtn();

		logger.info("Click on close Button.");
		navBar.clickCloseCartDrawerBtn();

	}

}
