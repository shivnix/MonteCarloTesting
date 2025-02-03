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

public class CartPage {
	private DataUtilities dataUtilities = new DataUtilities();
	private WebDriverUtilities utilities = new WebDriverUtilities();

	public CartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[contains(@class,'product-item-meta__title hidden-phone')]")
	private WebElement cartItem;

	public WebElement getCartItem() {
		return cartItem;
	}

	@FindBy(name = "note")
	private WebElement noteBox;

	public void enterNote(String note) {
		noteBox.sendKeys(note);
	}

	@FindBy(xpath = "//button[@name='checkout']")
	private WebElement checkOutButton;

	public void clickCheckOutBtn() {
		checkOutButton.click();
	}

	@FindBy(xpath = "//tr[@class='line-item']")
	private List<WebElement> cartItems;

	//

	public boolean cartProductVerification(WebDriver driver) throws FileNotFoundException, IOException {
		for (int i = 1; i <= cartItems.size(); i++) {
			WebElement productElement = driver.findElement(
					By.xpath("(//tr[@class='line-item'])[" + i + "]//div[@class='line-item__info']//a[2]"));
			utilities.waitElement(productElement, driver);
			String productName = productElement.getText();
			if (productName.equals(dataUtilities.readPropertyFile("productName"))) {
				return true;
			}
		}

		return false;
	}

}
