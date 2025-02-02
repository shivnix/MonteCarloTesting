package pom;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.DataUtilities;
import genericLibraries.WebDriverUtilities;

public class ProductDetailsPage {
	private DataUtilities dataUtilities = new DataUtilities();
	private WebDriverUtilities utilities = new WebDriverUtilities();

	public ProductDetailsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='product_wishlist']//a")
	private WebElement addToWishListIcon;

	public void clickAddToWishListIcon() {
		addToWishListIcon.click();
	}

	@FindBy(id = "pincode")
	private WebElement pinCodeBox;

	@FindBy(id = "check-pincode")
	private WebElement checkPincodeBtn;

	@FindBy(xpath = "(//button[contains(@class, 'accordion-btn-s')])[1]")
	private WebElement productDetailsAndDescription;

	@FindBy(xpath = "//div[@class='yash-blog-text shreyh']/h2[text()='Manufacturer Address']/following::p")
	private WebElement manufacturerAddress;

	@FindBy(linkText = "Our Stores")
	private WebElement outStoresButton;

	@FindBy(xpath = "(//p[@class='flits-h2 flits-tingle-modal-popup-header-title'])[1]")
	private WebElement popupVerification;

	@FindBy(xpath = "(//p[@class='flits-h2 flits-tingle-modal-popup-header-title'])[2]")
	private WebElement addToWishListVerfication;

	@FindBy(name = "email")
	private WebElement popupEmailBox;

	@FindBy(id = "AddToCart")
	private WebElement addToCartButton;

	@FindBy(xpath = "//div[@class='block-swatch']")
	private WebElement selectSize;

	@FindBy(xpath = "(//button[@class='quantity-selector__button'])[2]")
	private WebElement incCount;

	@FindBy(name = "checkout")
	private WebElement initialteCheckoutButton;

	@FindBy(xpath = "//h1[@class='product-meta__title heading h3']")
	private WebElement productTitle;

	public String getProductTitle() {
		return productTitle.getText();
	}

	public void enterPinCode(String pincode) {
		pinCodeBox.sendKeys(pincode);
	}

	public void clickCheckPincodeBtn() {
		checkPincodeBtn.click();
	}

	public void clickProductDetailsDescription() {
		productDetailsAndDescription.click();
	}

	public String getManufacturerAddress(WebDriver driver) throws FileNotFoundException, IOException {
		utilities.waitElement(manufacturerAddress, driver);
		String address = manufacturerAddress.getText();

		String[] parts = address.split("[,\\s-]+");
		for (String part : parts) {
			if (part.length() == 6 && part.matches("\\d{6}")) {
				dataUtilities.writePropertyFile("pinCode", part);
			}
		}
		return address;
	}

	public void clickOurStoresBtn() {
		outStoresButton.click();
	}

	public void selectSize() {
		selectSize.click();
	}

	public void clickIncCountBtn() {
		incCount.click();
	}

	public void clickAddToCartBtn() {
		addToCartButton.click();
	}

	public void clickInitialteCheckoutBtn() {
		initialteCheckoutButton.click();
	}

	public String getPopupVerificationText() {
		return popupVerification.getText();
	}

	public String getAddToWishListVerficationText() {
		return addToWishListVerfication.getText();
	}

	public void enterPopupEmail(String mail) {
		popupEmailBox.sendKeys(mail);
	}

	public void submitPopupEmail() {
		popupEmailBox.submit();
	}

	public void extractPinCode(String address) throws FileNotFoundException, IOException {
		String[] parts = address.split("[,\\s-]+");
		for (String part : parts) {
			if (part.length() == 6 && part.matches("\\d{6}")) {
				dataUtilities.writePropertyFile("pinCode", part);
			}
		}
	}

}
