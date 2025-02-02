package testScripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

//import org.testng.Assert;
import org.testng.annotations.Test;

import genericLibraries.BaseClass;
import pom.CartPage;
import pom.Header;
import pom.LoginPage;
import pom.NavBar;
import pom.ProductDetailsPage;
import pom.ProductsPage;
import pom.WishlistPage;

public class TC_01_AddingProductTOCart extends BaseClass {

	@Test
	public void TestCase() throws IOException, Exception {
//		3-click on Login/Signup icon
		Header header = new Header(driver);
		String homepageTitle = dataUtilities.readPropertyFile("homepageTitle");
		assertEquals(driver.getTitle(), homepageTitle);

		header.clickLoginSignupButton();

//		4-enter email address
		LoginPage loginPage = new LoginPage(driver);
		String loginTitle = dataUtilities.readPropertyFile("loginTitle");
		assertEquals(driver.getTitle(), loginTitle);

		loginPage.emailAddressTextBox(dataUtilities.readExcelFile("Sheet1", 1, 1));

//		5-enter password
		loginPage.passwordTextBox(dataUtilities.readExcelFile("Sheet1", 1, 2));

//		6-click on  login
		loginPage.clickLoginButton();

//		7-verify you are in AccountPage.
		String accountTitle = dataUtilities.readPropertyFile("accountTitle");
		assertEquals(driver.getTitle(), accountTitle);

//		8-Mouse over on Home Furnishing
		NavBar navBar = new NavBar(driver);
		utilities.mouseHover(navBar.homeFurnishingOption(), driver);

//		9-click on  AC Comforter
		navBar.clickAcComforter(driver);

//		10.click on size and select the size
		ProductsPage productPage = new ProductsPage(driver);
		String acComforter = dataUtilities.readPropertyFile("acComforter");
		assertTrue(driver.getTitle().contains(acComforter), acComforter + ": Page not found");

		productPage.clickSizeBtn(driver);
		productPage.clickSingleOption(driver);

//		11-Scroll down click on color and select the colour
		productPage.clickColorBtn(driver);
		productPage.clickColorOption(driver);

//		12-Scroll down click on brand and select the brand of item
		productPage.clickBrandBtn(driver);
		productPage.clickBrandOption(driver);

//		13-Scroll down click on availability and choose in stock
		productPage.clickAvailabilityBtn(driver);
		productPage.clickInStockOption(driver);

//		14-Click on any product and click add to wishlist.
		productPage.selectAnyProduct(5, driver);

		ProductDetailsPage productDetail = new ProductDetailsPage(driver);
		String productName = dataUtilities.readPropertyFile("productName");
		assertEquals(productDetail.getProductTitle(), productName);

		productDetail.clickAddToWishListIcon();

//		15-click on wishlist button
		header.clickWishListButton();

//		16-verify you are in wishlist page
		assertEquals(driver.getTitle(), dataUtilities.readPropertyFile("wishlistTitle"));

//		17-Verify the item is added to wishlist or not.
		WishlistPage wishlistPage = new WishlistPage(driver);
		boolean checkProduct = wishlistPage.wishListProductVerification(driver);
		assertTrue(checkProduct, "Product not found in the wishlist");

//		18-if the item present in the wishlist,click on add to cart.
		wishlistPage.addSelectedItemToCart(driver);

//		19-click on add to cart icon

//		20-verify the item is added to cart 
		CartPage cartPage = new CartPage(driver);
		checkProduct = cartPage.cartProductVerification(driver);
		assertTrue(checkProduct, "Product not found in the cart");

	}
}
