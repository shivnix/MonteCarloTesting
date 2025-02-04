package testScripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
//import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import pom.CartPage;
import pom.Header;
import pom.LoginPage;
import pom.NavBar;
import pom.ProductDetailsPage;
import pom.ProductsPage;
import pom.WishlistPage;

public class TC_01_AddingProductTOCart extends BaseClass {
	private SoftAssert softAssert = new SoftAssert();
	@Test
	public void TestCase() throws IOException, Exception {
		logger = LogManager.getLogger(TC_01_AddingProductTOCart.class);
		logger.info("Click on Login/Signup icon");
		Header header = new Header(driver);
		String homepageTitle = dataUtilities.readPropertyFile("homepageTitle");
		assertEquals(driver.getTitle(), homepageTitle);

		header.clickLoginSignupButton();

		logger.info("Enter email address");
		LoginPage loginPage = new LoginPage(driver);
		String loginTitle = dataUtilities.readPropertyFile("loginTitle");
		assertEquals(driver.getTitle(), loginTitle);

		loginPage.emailAddressTextBox(dataUtilities.readExcelFile("Sheet1", 1, 1));

		logger.info("Enter password");
		loginPage.passwordTextBox(dataUtilities.readExcelFile("Sheet1", 1, 2));

		logger.info("Click on  login");
		loginPage.clickLoginButton();

		logger.info("Verify you are in AccountPage.");
		String accountTitle = dataUtilities.readPropertyFile("accountTitle");
		assertEquals(driver.getTitle(), accountTitle);

		logger.info("Mouse over on Home Furnishing");
		NavBar navBar = new NavBar(driver);
		utilities.mouseHover(navBar.homeFurnishingOption(), driver);

		logger.info("Click on  AC Comforter");
		navBar.clickAcComforter(driver);

		logger.info("Click on size and select the size");
		ProductsPage productPage = new ProductsPage(driver);
		String acComforter = dataUtilities.readPropertyFile("acComforter");
		
		
		softAssert.assertTrue(driver.getTitle().contains(acComforter), acComforter + ": Page not found");
		
		productPage.clickSizeBtn(driver);
		productPage.clickSingleOption(driver);

		logger.info("Scroll down click on color and select the colour");
		productPage.clickColorBtn(driver);
		productPage.clickColorOption(driver);

		logger.info("Scroll down click on brand and select the brand of item");
		productPage.clickBrandBtn(driver);
		productPage.clickBrandOption(driver);

		logger.info("Scroll down click on availability and choose in stock");
		productPage.clickAvailabilityBtn(driver);
		productPage.clickInStockOption(driver);
		
		softAssert.assertAll();

		logger.info("Click on any product and click add to wishlist.");
		productPage.selectAnyProduct(5, driver);

		ProductDetailsPage productDetail = new ProductDetailsPage(driver);
		String productName = dataUtilities.readPropertyFile("productName");
		assertEquals(productDetail.getProductTitle(), productName);

		productDetail.clickAddToWishListIcon(driver);

		logger.info("Click on wishlist button");
		header.clickWishListButton();

		logger.info("Verify you are in wishlist page");
		assertEquals(driver.getTitle(), dataUtilities.readPropertyFile("wishlistTitle"));

		logger.info("Verify the item is added to wishlist or not.");
		WishlistPage wishlistPage = new WishlistPage(driver);
		boolean checkProduct = wishlistPage.wishListProductVerification(driver);
		assertTrue(checkProduct, "Product not found in the wishlist");

		logger.info("If the item present in the wishlist,click on add to cart.");
		wishlistPage.addSelectedItemToCart(driver);

		logger.info("Verify the item is added to cart");
		CartPage cartPage = new CartPage(driver);
		checkProduct = cartPage.cartProductVerification(driver);
		assertTrue(checkProduct, "Product not found in the cart");

	}
}
