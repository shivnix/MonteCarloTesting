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

public class NavBar {
	private DataUtilities dataUtilities = new DataUtilities();
	private WebDriverUtilities utilities = new WebDriverUtilities();

	@FindBy(xpath = "//li[@data-item-title='HOME FURNISHING']")
	private WebElement homeFurnishing;

	public WebElement homeFurnishingOption() {
		return homeFurnishing;
	}

	@FindBy(xpath = "//li[@data-item-title='MEN']")
	private WebElement mens;

	public WebElement hoverMens() {
		return mens;
	}

	@FindBy(xpath = "//li[@data-item-title='WOMEN']")
	private WebElement womens;

	public void clickWomen() {
		womens.click();
	}

	@FindBy(xpath = "//a[@aria-label='Cart']")
	private WebElement cartButton;

	@FindBy(xpath = "//li[@class='linklist__item']//a[text()='AC Comforter']")
	private WebElement acComforter;

	public void clickAcComforter(WebDriver driver) throws FileNotFoundException, IOException {
		utilities.waitElement(acComforter, driver);
		String navBarOption = acComforter.getText();
		dataUtilities.writePropertyFile("navBarOption", navBarOption);
		acComforter.click();
	}

	@FindBy(xpath = "//li[@class='linklist__item']//a[text()='Round Neck Sweaters']")
	private WebElement roundNeckSweaters;

	@FindBy(xpath = "//a[@aria-label='Search']")
	private WebElement searchField;

	@FindBy(xpath = "//a[@class='button button--primary']")
	private WebElement startShoppingButton;

	@FindBy(name = "q")
	private WebElement searchBox;

	@FindBy(xpath = "//*[@id='search-drawer']/footer/button")
	private WebElement viewAllResultButton;

	@FindBy(id = "order-note-toggle")
	private WebElement addOrderNoteButton;

	public WebElement getAddOrderNoteButton() {
		return addOrderNoteButton;
	}

	@FindBy(id = "cart[note]")
	private WebElement addNoteBox;

	@FindBy(xpath = "//button[text()='Save']")
	private WebElement saveNoteButton;

	@FindBy(xpath = "//button[@data-action=\"close\"]")
	private WebElement closeCartDrawerButton;

	@FindBy(xpath = "//li[contains(@class,'predictive-search__product')]")
	private List<WebElement> searchSuggestedProducts;

	public NavBar(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void clickRoundNeckSweaters() {
		roundNeckSweaters.click();
	}

	public void clickCartButton() {
		cartButton.click();
	}

	public void clickSeachFieldButton() {
		searchField.click();
	}

	public void clickViewAllResultBtn() {
		viewAllResultButton.click();
	}

	public void clickStartShoppingBtn() {
		startShoppingButton.click();
	}

	public void clickAddOrderNoteBtn() {
		addOrderNoteButton.click();
	}

	public void clickSaveNoteBtn() {
		saveNoteButton.click();
	}

	public void clickCloseCartDrawerBtn() {
		closeCartDrawerButton.click();
	}

	public void searchBoxField(String name) {
		searchBox.sendKeys(name);
	}

	public void enterAddNote(String note) {
		addNoteBox.sendKeys(note);
	}

	public void enterSearchBox() {
		searchBox.submit();
	}

	public void chooseSearchSuggestedProducts(int index, WebDriver driver) throws FileNotFoundException, IOException {
		utilities.waitRefreshed(searchSuggestedProducts, driver);
		WebElement product = driver.findElement(By.xpath("(//li[contains(@class,'predictive-search__product')])["
				+ index + "]//div[@class='line-item__info']//span"));
		utilities.scrollToElement(product, driver);
		utilities.waitElement(product, driver);
		String productName = product.getText();
		dataUtilities.writePropertyFile("productName", productName);
		searchSuggestedProducts.get(index-1).click();
	}

	// Verification
	public String getRoundNeckSweatersTitle() {
		return roundNeckSweaters.getText();
	}

}
