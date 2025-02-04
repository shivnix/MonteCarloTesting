package testScripts;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;

import genericLibraries.BaseClass;
import pom.NavBar;
import pom.ProductDetailsPage;
import pom.ProductsPage;

public class TC_08 extends BaseClass {
	@Test
	public void testCase08() throws FileNotFoundException, IOException {

		logger.info("Click on Women.");
		NavBar navBar = new NavBar(driver);
		navBar.clickWomen();

		logger.info("Click on stole.");
		ProductsPage products = new ProductsPage(driver);
		products.clickStoleOption();

//		logger.info("Set the price range from 0 to 500.");
		products.enterToPrice(dataUtilities.readPropertyFile("toPrice"));
		products.submitToPrice();

		logger.info("No result text should be visible.");
		assertEquals(products.getNoResultFoundText(), dataUtilities.readPropertyFile("noResult"));

		logger.info("Click on reset filter button.");
		products.clickResetFilterBtn();

		logger.info("Set the range from 500 to 2000.");
		products.enterFromPrice(dataUtilities.readPropertyFile("fromPrice"));
		products.enterToPrice(dataUtilities.readPropertyFile("updatedPrice"));
		products.submitToPrice();

		logger.info("Click on the first product.");
		products.clickFirstProduct();

		logger.info("Click on the wishlist of the product.");
		ProductDetailsPage prodcutDetails = new ProductDetailsPage(driver);
		prodcutDetails.clickAddToWishListIcon(driver);

		logger.info("Verify the popup Text");
		assertEquals(prodcutDetails.getPopupVerificationText(), dataUtilities.readPropertyFile("emailPopup"));

		logger.info("Check the product is added to wishlist");
		prodcutDetails.enterPopupEmail(dataUtilities.readPropertyFile("emailId"));
		prodcutDetails.submitPopupEmail();

		assertEquals(prodcutDetails.getAddToWishListVerficationText(),
				dataUtilities.readPropertyFile("verifyProductWishlist"));

	}
}
