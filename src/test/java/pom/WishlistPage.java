package pom;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.DataUtilities;
import genericLibraries.WebDriverUtilities;

public class WishlistPage {
	private DataUtilities dataUtilies = new DataUtilities();
	private WebDriverUtilities utilies = new WebDriverUtilities();

	public WishlistPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[contains(@class,'flits-box')]//p[contains(@class,'flits-product-name')]")
	private WebElement wishListedItem;

	public WebElement getWishListedItem() {
		return wishListedItem;
	}

	@FindBy(xpath = "//div[contains(@class,'flits-box')]/div")
	private WebElement firstWishListedItem;

	public WebElement hoverFirstWishlistedItem() {
		return firstWishListedItem;
	}

	@FindBy(xpath = "//div[contains(@class,'flits-box')]//div//div//div//button[text()='Add to Cart']")
	private WebElement addFirstItemToCartButton;

	public void clickAddToCartButton() {
		addFirstItemToCartButton.click();
	}

	@FindBy(xpath = "//button[contains(@class, 'flits-remove-product')]")
	private WebElement removeFirstItemFromWishlistButton;

	public void clickRemoveFromWishListButton() {
		removeFirstItemFromWishlistButton.click();
	}

	@FindBy(xpath = "//div[contains(@class, 'flits-wishlist-card')]")
	private List<WebElement> wishListItems;

	public boolean wishListProductVerification(WebDriver driver) throws FileNotFoundException, IOException {
		for (int i = 1; i <= wishListItems.size() / 2; i++) {
			WebElement productElement = driver
					.findElement(By.xpath("(//div[contains(@class, 'flits-wishlist-card')])[" + i + "]//p"));
			utilies.waitElement(productElement, driver);
			String productName = productElement.getText();
			if (productName.equals(dataUtilies.readPropertyFile("productName"))) {
				return true;
			}
		}

		return false;
	}

	public void addSelectedItemToCart(WebDriver driver) throws FileNotFoundException, IOException {
		for (int i = 1; i <= wishListItems.size(); i++) {
			WebElement productElement = driver
					.findElement(By.xpath("(//div[contains(@class, 'flits-wishlist-card')])[" + i + "]//p"));
//			utilies.waitRefreshed(productElement, driver);
			String productName = productElement.getText();
			if (productName.equals(dataUtilies.readPropertyFile("productName"))) {
				WebElement addToCart = driver.findElement(
						By.xpath("(//div[contains(@class, 'flits-wishlist-card')])["+ i +"]//button[text()='Add to Cart']"));
				addToCart.click();
				break;
			}
		}
	}

}
