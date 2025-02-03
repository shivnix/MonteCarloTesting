package testScripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import genericLibraries.BaseClass;
import pom.Header;
import pom.LoginPage;
import pom.MonteCarloHomePage2;
import pom.ProductDetailsPage;
import pom.ProductsPage;
import pom.StoresPage;

public class TC_05 extends BaseClass {
	@Test
	public void testCase04() throws EncryptedDocumentException, IOException {
		logger.info("Click on login/signup btn");
		Header header = new Header(driver);
		header.clickLoginSignupButton();

		logger.info("Enter Email");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.emailAddressTextBox(dataUtilities.readExcelFile("Sheet1", 1, 1));

		logger.info("Enter Password");
		loginPage.passwordTextBox(dataUtilities.readExcelFile("Sheet1", 1, 2));

		logger.info("Click on login btn");
		loginPage.clickLoginButton();

		logger.info("Verify you are in profile page");
		assertEquals(driver.getTitle(), dataUtilities.readPropertyFile("loginTitle"));

		logger.info("Click on monte carlo home link it will redirect you 'Homes by Monte Carlo'");
		header.clickMonteCarloHomeLogoButton();

		logger.info("Scroll down till shop blankets btn element and click shop blankets button");
		MonteCarloHomePage2 monteHome2 = new MonteCarloHomePage2(driver);
		monteHome2.clickShopBlanketBtn();

		logger.info("Select the product and click on that product");
		ProductsPage products = new ProductsPage(driver);
		products.selectAnyProduct(2, driver);

		logger.info("It will redirect you that product page and then click on product details & description");
		ProductDetailsPage productDetail = new ProductDetailsPage(driver);
		assertEquals(productDetail.getProductTitle(), dataUtilities.readPropertyFile("productName"));

		productDetail.clickProductDetailsDescription();

		logger.info("Get the manufacture address text");
		String manufatureAddress = productDetail.getManufacturerAddress(driver);

		logger.info("Scroll to end then you will find link Our Stores and then click on it.");
		utilities.scrollbarTopToBottom(driver);
		productDetail.clickOurStoresBtn();

		logger.info("Verify your page through title that you are in our stores page");
		StoresPage storePage = new StoresPage(driver);
		assertEquals(driver.getTitle(), dataUtilities.readPropertyFile("storePageTitle"));

		logger.info("You will find search box you can search (by city,state or zip) then send keys zip code");
		storePage.enterInSearchBox(dataUtilities.readPropertyFile("pinCode"));

		logger.info("You will find store located in that location in that pincode");
		assertTrue(manufatureAddress.contains(dataUtilities.readPropertyFile("pinCode")), "Wrong Zipcode!");

		logger.info("Click on getDirections btn that is visible on the store card");
		storePage.clickGetDirectionBtn();

		logger.info("It should navigates you google map.");
		utilities.switchChildWindow(driver);
		assertTrue(driver.getTitle().contains("Google Maps"), "Google Map Page not found");
	}

}
