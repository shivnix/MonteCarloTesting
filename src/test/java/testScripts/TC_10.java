package testScripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import genericLibraries.BaseClass;
import pom.CartPage;
import pom.CheckOutPage;
import pom.Header;
import pom.LoginPage;
import pom.NavBar;
import pom.ProductDetailsPage;
import pom.ProductsPage;
import pom.WishlistPage;

public class TC_10 extends BaseClass {
	public void testCase10() throws FileNotFoundException, IOException {
		logger.info("Test Case 10 started");
		
		logger.info("Verify homepage");
		Header header = new Header(driver);
		String homepageTitle = dataUtilities.readPropertyFile("homepageTitle");
		assertEquals(driver.getTitle(), homepageTitle);

		logger.info("Click on login/sign up");
		header.clickLoginSignupButton();

		logger.info("Enter username");
		LoginPage loginPage = new LoginPage(driver);
		String loginTitle = dataUtilities.readPropertyFile("loginTitle");
		assertEquals(driver.getTitle(), loginTitle);
		
		logger.info("Enter email");
		loginPage.emailAddressTextBox(dataUtilities.readExcelFile("Sheet1", 1, 1));

		logger.info("Enter password");
		loginPage.passwordTextBox(dataUtilities.readExcelFile("Sheet1", 1, 2));

		logger.info("Click login");
		loginPage.clickLoginButton();

		logger.info("Click on cart");
		String accountTitle = dataUtilities.readPropertyFile("accountTitle");
		assertEquals(driver.getTitle(), accountTitle);

		NavBar navBar = new NavBar(driver);
		navBar.clickCartButton();

//		logger.info("Make sure 'Your cart is empty' text is displaying);

		logger.info("Click start shopping.");
		navBar.clickStartShoppingBtn();

		logger.info("Click on sort by");
		ProductsPage productPage = new ProductsPage(driver);
		productPage.clickSortByBtn();

		logger.info("Select 'high to low'");
		productPage.selectHighToLowOption();

		logger.info("Add any product to wishlist");
		productPage.selectAnyProduct(5, driver);

		ProductDetailsPage productDetail = new ProductDetailsPage(driver);
		String productName = dataUtilities.readPropertyFile("productName");
		assertEquals(productDetail.getProductTitle(), productName);

		productDetail.clickAddToWishListIconCheck();

		logger.info("Click on wishlist");
		header.clickWishListButton();

		logger.info("Verify if product got added to wishlist");
		WishlistPage wishlistPage = new WishlistPage(driver);

		boolean checkProduct = wishlistPage.wishListProductVerification(driver);
		assertTrue(checkProduct, "Product not found in the wishlist");

		logger.info("Add that item to cart");
		wishlistPage.addSelectedItemToCart(driver);

		logger.info("Enter text in 'add order note'");
		CartPage cartPage = new CartPage(driver);
		checkProduct = cartPage.cartProductVerification(driver);
		assertTrue(checkProduct, "Product not found in the cart");

		cartPage.enterNote("This is a note!");

		logger.info("Initiate checkout click");
		cartPage.clickCheckOutBtn();

		logger.info("Enter text bangalore and select second suggesstion");
		CheckOutPage checkoutPage = new CheckOutPage(driver);
		assertEquals(checkoutPage.getProductName(), dataUtilities.readPropertyFile("productName"));

		checkoutPage.useDiffAddBtn(driver);
		checkoutPage.enterAddress(dataUtilities.readPropertyFile("checkoutAdd"), driver);
		checkoutPage.getAddress(2, driver);

	}
}
