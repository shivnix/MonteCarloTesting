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

public class TC_09_10 extends BaseClass {
	@Test
	public void testCase09() throws FileNotFoundException, IOException {
//		4.Click on Login
		Header header = new Header(driver);
		header.clickLoginSignupButton();

//		5.Enter Username and password
		LoginPage loginPage = new LoginPage(driver);
		loginPage.emailAddressTextBox(dataUtilities.readExcelFile("Sheet1", 1, 1));
		loginPage.passwordTextBox(dataUtilities.readExcelFile("Sheet1", 1, 2));
		loginPage.clickLoginButton();

//		6.Verify the homepage 
		String accountTitle = dataUtilities.readPropertyFile("accountTitle");
		assertEquals(driver.getTitle(), accountTitle);

//		7.Click on cart
		NavBar navBar = new NavBar(driver);
		navBar.clickCartButton();

//		8.Click on start Shopping
		navBar.clickStartShoppingBtn();

//		9.Select Filters : Date Old to New
		ProductsPage productsPage = new ProductsPage(driver);
		productsPage.clickSortByBtn();
		productsPage.selectDateOldToNewOption();

//		10.Click on the Product
		productsPage.selectAnyProduct(5, driver);

//		11.Click on add to cart
		ProductDetailsPage productDetails = new ProductDetailsPage(driver);
		String productName = dataUtilities.readPropertyFile("productName");
		assertEquals(productDetails.getProductTitle(), productName);
		
		productDetails.clickAddToCartBtn();

	}

	@Test(dependsOnMethods = "testCase09")
	public void testCase10() throws FileNotFoundException, IOException, InterruptedException {
		NavBar navBar = new NavBar(driver);
		utilities.waitElement(navBar.getAddOrderNoteButton(), driver);
		
//		12.Click on add/Edit Order Note
		navBar.clickAddOrderNoteBtn();

//		13.Enter the desired text 
		navBar.enterAddNote("This is my order");

//		14.Click on save
		navBar.clickSaveNoteBtn();

//		15. Click on close Button.
		navBar.clickCloseCartDrawerBtn();

	}

}
