package testScripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import genericLibraries.BaseClass;
import pom.Header;
import pom.LoginPage;
import pom.NavBar;
import pom.ProductDetailsPage;
import pom.WishlistPage;

public class TC_02_AddingProductToWishList extends BaseClass {
	@Test
	public void testCase2() throws FileNotFoundException, IOException {
		logger = LogManager.getLogger(TC_02_AddingProductToWishList.class);
		Header header = new Header(driver);

		logger.info("Click on Login/Signup icon");
		String homepageTitle = dataUtilities.readPropertyFile("homepageTitle");
		assertEquals(driver.getTitle(), homepageTitle);

		header.clickLoginSignupButton();

		logger.info("Enter email address and password");
		LoginPage loginPage = new LoginPage(driver);
		String loginTitle = dataUtilities.readPropertyFile("loginTitle");
		assertEquals(driver.getTitle(), loginTitle);

		loginPage.emailAddressTextBox(dataUtilities.readExcelFile("Sheet1", 1, 1));
		loginPage.passwordTextBox(dataUtilities.readExcelFile("Sheet1", 1, 2));

		loginPage.clickLoginButton();

		logger.info("Verify the account page");
		String accountTitle = dataUtilities.readPropertyFile("accountTitle");
		assertEquals(driver.getTitle(), accountTitle);

		logger.info("Click on Search box");
		NavBar navBar = new NavBar(driver);
		navBar.clickSeachFieldButton();

		logger.info("Search for Hand Towels");
		navBar.searchBoxField(dataUtilities.readPropertyFile("HandTowels"));

		logger.info("Scroll down upto fourth product and click on the fourth product");
		navBar.chooseSearchSuggestedProducts(5, driver);

		logger.info("Add to Wishlist");
		ProductDetailsPage productDetails = new ProductDetailsPage(driver);
		assertEquals(productDetails.getProductTitle(), dataUtilities.readPropertyFile("productName"));

		productDetails.clickAddToWishListIcon(driver);

		logger.info("Click on Wishlist");
		header.clickWishListButton();

		logger.info("Verify if the product is added to the wishlist");
		WishlistPage wishlist = new WishlistPage(driver);
		assertTrue(wishlist.wishListProductVerification(driver), "Product not found in the wishlist");

		logger.info("Click on the close button");
		WebElement firstEle = wishlist.hoverFirstWishlistedItem();
		utilities.mouseHover(firstEle, driver);
		wishlist.clickRemoveFromWishListButton();

	}
}
