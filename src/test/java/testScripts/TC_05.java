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
//		3) click on login/signup btn 
		Header header = new Header(driver);
		header.clickLoginSignupButton();

//		4) Enter Email
		LoginPage loginPage = new LoginPage(driver);
		loginPage.emailAddressTextBox(dataUtilities.readExcelFile("Sheet1", 1, 1));

//		5) Enter Password
		loginPage.passwordTextBox(dataUtilities.readExcelFile("Sheet1", 1, 2));

//		6) click on login btn
		loginPage.clickLoginButton();

//		7) verify you are in profile page
		assertEquals(driver.getTitle(), dataUtilities.readPropertyFile("loginTitle"));

//		8) click on monte carlo home link it will redirect you "Homes by Monte Carlo"
		header.clickMonteCarloHomeLogoButton();

//		9) scroll down till shop blankets btn element and click shop blankets button
		MonteCarloHomePage2 monteHome2 = new MonteCarloHomePage2(driver);
		monteHome2.clickShopBlanketBtn();

//		10) Select the product and click on that product
		ProductsPage products = new ProductsPage(driver);
		products.selectAnyProduct(2, driver);

//		11) it will redirect you that product page and then click on product details & description
		ProductDetailsPage productDetail = new ProductDetailsPage(driver);
		assertEquals(productDetail.getProductTitle(), dataUtilities.readPropertyFile("productName"));
		
		productDetail.clickProductDetailsDescription();

//		12) get the manufacture address text
		String manufatureAddress = productDetail.getManufacturerAddress(driver);

//		13) scroll to end then you will find link Our Stores and then click on it.
		utilities.scrollbarTopToBottom(driver);
		productDetail.clickOurStoresBtn();

//		14) verify your page through title that you are in our stores page
		StoresPage storePage = new StoresPage(driver);
		assertEquals(driver.getTitle(), dataUtilities.readPropertyFile("storePageTitle"));

//		15) you will find search box you can search (by city,state or zip) then send keys zip code 
		storePage.enterInSearchBox(dataUtilities.readPropertyFile("pinCode"));

//		16) you will find store located in that location in that pincode
		assertTrue(manufatureAddress.contains(dataUtilities.readPropertyFile("pinCode")), "Wrong Zipcode!");

//		17) click on getDirections btn that is visible on the store card
		storePage.clickGetDirectionBtn();

//		18) it should navigates you google map.
		utilities.switchChildWindow(driver);
		assertTrue(driver.getTitle().contains("Google Maps"), "Google Map Page not found");
	}

}
