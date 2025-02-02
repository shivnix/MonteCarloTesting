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

//		3. Click on Women.
		NavBar navBar = new NavBar(driver);
		navBar.clickWomen();

//		4. Click on stole.
		ProductsPage products = new ProductsPage(driver);
		products.clickStoleOption();

//		5. Set the price range from 0 to 500.
		products.enterToPrice(dataUtilities.readPropertyFile("toPrice"));
		products.submitToPrice();

//		6. No result text should be visible.
		assertEquals(products.getNoResultFoundText(), dataUtilities.readPropertyFile("noResult"));

//		7.Click on reset filter button.
		products.clickResetFilterBtn();

//		8.Set the range from 500 to 2000.
		products.enterFromPrice(dataUtilities.readPropertyFile("fromPrice"));
		products.enterToPrice(dataUtilities.readPropertyFile("updatedPrice"));
		products.submitToPrice();

//		9.Click on the first product.
		products.clickFirstProduct();

//		10.Click on the wishlist of the product.
		ProductDetailsPage prodcutDetails = new ProductDetailsPage(driver);
		prodcutDetails.clickAddToWishListIcon();

//		11.Verify the popup Text
		assertEquals(prodcutDetails.getPopupVerificationText(), dataUtilities.readPropertyFile("emailPopup"));

//		12.Check the product is added to wishlist
		prodcutDetails.enterPopupEmail(dataUtilities.readPropertyFile("emailId"));
		prodcutDetails.submitPopupEmail();

		assertEquals(prodcutDetails.getAddToWishListVerficationText(),
				dataUtilities.readPropertyFile("verifyProductWishlist"));

	}
}
