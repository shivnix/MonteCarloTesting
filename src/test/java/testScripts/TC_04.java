package testScripts;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import genericLibraries.BaseClass;
import pom.Header;
import pom.LoginPage;
import pom.NavBar;
import pom.ProductDetailsPage;
import pom.ProductsPage;

public class TC_04 extends BaseClass {
	@Test
	public void testCase4() throws EncryptedDocumentException, IOException, InterruptedException {
//		4. click on signin
		Header header = new Header(driver);
		String homepageTitle = dataUtilities.readPropertyFile("homepageTitle");
		assertEquals(driver.getTitle(), homepageTitle);

		header.clickLoginSignupButton();

//		5. Enter the username
		LoginPage loginPage = new LoginPage(driver);
		String loginTitle = dataUtilities.readPropertyFile("loginTitle");
		assertEquals(driver.getTitle(), loginTitle);

		loginPage.emailAddressTextBox(dataUtilities.readExcelFile("Sheet1", 1, 1));

//		6. Enter the password
		loginPage.passwordTextBox(dataUtilities.readExcelFile("Sheet1", 1, 2));

//		7. click on login
		loginPage.clickLoginButton();

//		8. click on search bar and search for women hooded jackets
		String accountTitle = dataUtilities.readPropertyFile("accountTitle");
		assertEquals(driver.getTitle(), accountTitle);

		NavBar navBar = new NavBar(driver);

		navBar.clickSeachFieldButton();
		navBar.searchBoxField(dataUtilities.readPropertyFile("womenHoddedJackets"));

//		9. click on view all results
		navBar.clickViewAllResultBtn();

//		10. click on any product
		ProductsPage products = new ProductsPage(driver);
		boolean checkSearchResult = products.getSearchResultVerfication()
				.contains(dataUtilities.readPropertyFile("womenHoddedJackets"));
		assertTrue(checkSearchResult, "Search result incorrect!");

		products.selectAnyProduct(5, driver);

//		11. select the size
		ProductDetailsPage productDetails = new ProductDetailsPage(driver);
		assertEquals(productDetails.getProductTitle(), dataUtilities.readPropertyFile("productName"));

		productDetails.selectSize();

//		12. Increase the quantity to 2
		productDetails.clickIncCountBtn();

//		13. Add the product to cart
		productDetails.clickAddToCartBtn();

//		14. intiate checkout
		productDetails.clickInitialteCheckoutBtn();

	}

}
