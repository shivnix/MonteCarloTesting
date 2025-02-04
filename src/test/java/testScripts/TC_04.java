package testScripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import genericLibraries.BaseClass;
import pom.Header;
import pom.LoginPage;
import pom.NavBar;
import pom.ProductDetailsPage;
import pom.ProductsPage;

public class TC_04 extends BaseClass {
	@Test
	public void testCase4() throws EncryptedDocumentException, IOException, InterruptedException {
		logger.info("click on signin");
		Header header = new Header(driver);
		String homepageTitle = dataUtilities.readPropertyFile("homepageTitle");
		assertEquals(driver.getTitle(), homepageTitle);

		header.clickLoginSignupButton();

		logger.info("Enter the username");
		LoginPage loginPage = new LoginPage(driver);
		String loginTitle = dataUtilities.readPropertyFile("loginTitle");
		assertEquals(driver.getTitle(), loginTitle);

		loginPage.emailAddressTextBox(dataUtilities.readExcelFile("Sheet1", 1, 1));

		logger.info("Enter the password");
		loginPage.passwordTextBox(dataUtilities.readExcelFile("Sheet1", 1, 2));

		logger.info("Click on login");
		loginPage.clickLoginButton();

		logger.info("Click on search bar and search for women hooded jackets");
		String accountTitle = dataUtilities.readPropertyFile("accountTitle");
		assertEquals(driver.getTitle(), accountTitle);

		NavBar navBar = new NavBar(driver);

		navBar.clickSeachFieldButton();
		navBar.searchBoxField(dataUtilities.readPropertyFile("womenHoddedJackets"));

		logger.info("Click on view all results");
		navBar.clickViewAllResultBtn();

		logger.info("Click on any product");
		ProductsPage products = new ProductsPage(driver);
		boolean checkSearchResult = products.getSearchResultVerfication()
				.contains(dataUtilities.readPropertyFile("womenHoddedJackets"));
		assertTrue(checkSearchResult, "Search result incorrect!");

		products.selectwomenHoddedJackets(5, driver);

		logger.info("Select the size");
		ProductDetailsPage productDetails = new ProductDetailsPage(driver);
		assertEquals(productDetails.getProductTitle(), dataUtilities.readPropertyFile("productName"));

		productDetails.selectSize();

		logger.info("Increase the quantity to 2");
		productDetails.clickIncCountBtn();

		logger.info("Add the product to cart");
		productDetails.clickAddToCartBtn();

		logger.info("Intiate checkout");
		productDetails.clickInitialteCheckoutBtn();

	}

}
