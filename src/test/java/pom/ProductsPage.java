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

public class ProductsPage {
	public DataUtilities dataUtilities = new DataUtilities();
	public WebDriverUtilities utilities = new WebDriverUtilities();

	// size
	@FindBy(xpath = "//button[text()='Size']")
	private WebElement sizeButton;

	@FindBy(xpath = "//label[contains(text(), 'Single')]")
	private WebElement singleOption;

	@FindBy(xpath = "//label[contains(text(), '38/M')]")
	private WebElement size38Option;

	public void clickSize38Product(WebDriver driver) {
		utilities.waitElement(size38Option, driver);
		size38Option.click();
	}

	// color
	@FindBy(xpath = "//button[text()='Color']")
	private WebElement colorButton;

	public void clickColorBtn(WebDriver driver) {
		utilities.waitElement(colorButton, driver);
		colorButton.click();
	}

	@FindBy(xpath = "//label[@class='color-swatch__item']")
	private WebElement colorOption;

	public void clickColorOption(WebDriver driver) {
		utilities.waitElement(colorOption, driver);
		colorOption.click();
	}

	// brand
	@FindBy(xpath = "//button[text()='Brand']")
	private WebElement brandButton;

	public void clickBrandBtn(WebDriver driver) {
		utilities.waitElement(brandButton, driver);
		brandButton.click();
	}

	@FindBy(xpath = "//label[contains(text(),'Monte Carlo')]")
	private WebElement monteCarloOption;

	public void clickBrandOption(WebDriver driver) {
		utilities.waitElement(monteCarloOption, driver);
		monteCarloOption.click();
	}

	@FindBy(xpath = "//label[contains(text(),'Rock.it')]")
	private WebElement rockItOption;

	// availability
	@FindBy(xpath = "//button[text()='Availability']")
	private WebElement availabilityButton;

	public void clickAvailabilityBtn(WebDriver driver) {
		utilities.waitElement(availabilityButton, driver);
		availabilityButton.click();
	}

	@FindBy(xpath = "//label[contains(text(),'In stock')]")
	private WebElement inStockOption;

	public void clickInStockOption(WebDriver driver) {
		utilities.waitElement(inStockOption, driver);
		inStockOption.click();
	}

	// sleeve
	@FindBy(xpath = "//button[text()='Sleeve']")
	private WebElement sleeveButton;

	public void clickSleeveBtn(WebDriver driver) {
		utilities.waitElement(sleeveButton, driver);
		sleeveButton.click();
	}

	@FindBy(xpath = "//label[contains(text(),'Full Sleeve')]")
	private WebElement fullSleeveOption;

	public void clickFullSleeveOption(WebDriver driver) {
		utilities.waitElement(fullSleeveOption, driver);
		fullSleeveOption.click();
	}

	// first product
	@FindBy(xpath = "(//div[@class='title-wish'])[1]")
	private WebElement firstProduct;

	// choose product by index
	@FindBy(xpath = "//div[@id='facet-main']//product-item[@class='product-item  hhh Byee']")
	private List<WebElement> listOfProducts;

	public void selectAnyProduct(int index, WebDriver driver) throws FileNotFoundException, IOException {
		utilities.waitRefreshed(listOfProducts, driver);
		WebElement product = driver
				.findElement(By.xpath("(//div[@id='facet-main']//product-item[@class='product-item  hhh Byee'])["
						+ index + "]//div[@class='title-wish']/a"));

		utilities.scrollToElement(product, driver);
		utilities.waitElement(product, driver);
		String productName = product.getText();
		dataUtilities.writePropertyFile("productName", productName);
		product.click();
	}

	// first product
	@FindBy(xpath = "//*[@id=\"shopify-section-template--24170044031264__c29bfcea-d30e-4c65-986d-878a5f6594b5\"]/div/div/div/div/div[5]/a/img")
	private WebElement stoleOption;

	//
	@FindBy(xpath = "(//input[@aria-label='To price'])[2]")
	private WebElement toPriceOption;

	public void enterToPrice(String price) {
		toPriceOption.sendKeys(price);
	}

	public void submitToPrice() {
		toPriceOption.submit();
	}

	//
	@FindBy(xpath = "(//input[@aria-label='From price'])[2]")
	private WebElement fromPriceOption;

	public void enterFromPrice(String price) {
		fromPriceOption.sendKeys(price);
	}

	//
	@FindBy(xpath = "//h3[text()='No results']")
	private WebElement noResultFound;

	public String getNoResultFoundText() {
		return noResultFound.getText();
	}

	//
	@FindBy(xpath = "//div[@class='popover-container']")
	private WebElement sortByButton;

	public void clickSortByBtn() {
		sortByButton.click();
	}

	//
	@FindBy(xpath = "//span[text()='Date, new to old']")
	private WebElement dateOldToNewOption;

	public void selectDateOldToNewOption() {
		dateOldToNewOption.click();
	}

	//
	@FindBy(xpath = "//span[text()='Price, high to low']")
	private WebElement highToLowOption;

	public void selectHighToLowOption() {
		highToLowOption.click();
	}

	//
	@FindBy(xpath = "//*[@id=\"facet-main\"]/div/div/a")
	private WebElement resetFilterButton;

	public void clickResetFilterBtn() {
		resetFilterButton.click();
	}

	//
	@FindBy(xpath = "//h1[text()='Results for \"women hooded jackets\"']")
	private WebElement searchResultVerfication;

	public String getSearchResultVerfication() {
		return searchResultVerfication.getText();
	}

	// add to wish list
//	@FindBy(xpath = "(//a[@data-tippy-content='Add to Wishlist'])[1]")
//	private WebElement addToWishListButton;

	// Constructor
	public ProductsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Size
	public void clickSizeBtn(WebDriver driver) {
		utilities.waitElement(sizeButton, driver);
		sizeButton.click();
	}

	public void clickSingleOption(WebDriver driver) {
		utilities.waitElement(singleOption, driver);
		singleOption.click();
	}

	public void clickRockItBrandOption(WebDriver driver) {
		utilities.waitElement(rockItOption, driver);
		rockItOption.click();
	}

	// Sleeve

	public void clickFirstProduct() {
		firstProduct.click();
	}

	public void clickStoleOption() {
		stoleOption.click();
	}

	// add to wish list button
//	public void clickAddToWishListBtn() {
//		addToWishListButton.click();
//	}

}
