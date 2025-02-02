package testScripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import genericLibraries.BaseClass;
import pom.Header;
import pom.LoginPage;
import pom.NavBar;
import pom.ProductDetailsPage;
import pom.WishlistPage;

public class TC_02 extends BaseClass {
	@Test
	public void testCase2() throws FileNotFoundException, IOException {
		Header header = new Header(driver);
		// 4. click on Login/Signup icon
		String homepageTitle = dataUtilities.readPropertyFile("homepageTitle");
		assertEquals(driver.getTitle(), homepageTitle);

		header.clickLoginSignupButton();

		// 5. Enter email address and password
		LoginPage loginPage = new LoginPage(driver);
		String loginTitle = dataUtilities.readPropertyFile("loginTitle");
		assertEquals(driver.getTitle(), loginTitle);

		loginPage.emailAddressTextBox(dataUtilities.readExcelFile("Sheet1", 1, 1));
		loginPage.passwordTextBox(dataUtilities.readExcelFile("Sheet1", 1, 2));

		loginPage.clickLoginButton();

		// 6. Verify the account page
		String accountTitle = dataUtilities.readPropertyFile("accountTitle");
		assertEquals(driver.getTitle(), accountTitle);

		// 7. Click on Search box
		NavBar navBar = new NavBar(driver);
		navBar.clickSeachFieldButton();

		// 8. Search for Hand Towels
		navBar.searchBoxField(dataUtilities.readPropertyFile("HandTowels"));

		// 9. Scroll down upto fourth product
		// 10. Click on the fourth product
		navBar.chooseSearchSuggestedProducts(5, driver);

		// 11. Add to Wishlist
		ProductDetailsPage productDetails = new ProductDetailsPage(driver);
		assertEquals(productDetails.getProductTitle(), dataUtilities.readPropertyFile("productName"));

		productDetails.clickAddToWishListIcon();

		// 12. Click on Wishlist
		header.clickWishListButton();

		// 13. Verify if the product is added to the wishlist
		WishlistPage wishlist = new WishlistPage(driver);
		assertTrue(wishlist.wishListProductVerification(driver), "Product not found in the wishlist");

		// 14. Click on the close button
		WebElement firstEle = wishlist.hoverFirstWishlistedItem();
		utilities.mouseHover(firstEle, driver);
		wishlist.clickRemoveFromWishListButton();

	}
}
