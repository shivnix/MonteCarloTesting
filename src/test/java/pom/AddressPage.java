package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddressPage {
	@FindBy(xpath = "//button[text()='Add a new address']")
	private WebElement addNewAddressButton;

	@FindBy(id = "address-new[first_name]")
	private WebElement firstNameBox;

	@FindBy(id = "address-new[last_name]")
	private WebElement lastNameBox;

	@FindBy(id = "address-new[phone]")
	private WebElement phoneBox;

	@FindBy(id = "address-new[company]")
	private WebElement companyBox;

	@FindBy(id = "address-new[address1]")
	private WebElement add1Box;

	@FindBy(id = "address-new[address2]")
	private WebElement add2Box;

	@FindBy(id = "address-new[city]")
	private WebElement cityBox;

	@FindBy(id = "address-new[zip]")
	private WebElement zipBox;

	@FindBy(id = "address-new[default]")
	private WebElement defaultCheckBox;

	@FindBy(xpath = "//span[text()='Add a new address']")
	private WebElement addNewAddressSideBar;

	@FindBy(xpath = "//div[@class='account__address']")
	private List<WebElement> addressList;
	
	@FindBy(xpath = "//button[text()='Delete']")
	private WebElement deleteAddressButton;

	public AddressPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void clickAddNewAddressBtn() {
		addNewAddressButton.click();
	}

	public void enterFirstName(String name) {
		firstNameBox.sendKeys(name);
	}

	public void enterLastName(String name) {
		lastNameBox.sendKeys(name);
	}

	public void enterCompany(String text) {
		companyBox.sendKeys(text);
	}

	public void enterPhone(String text) {
		phoneBox.sendKeys(text);
	}

	public void enterAddressOne(String text) {
		add1Box.sendKeys(text);
	}

	public void enterAddressTwo(String text) {
		add2Box.sendKeys(text);
	}

	public void enterCity(String text) {
		cityBox.sendKeys(text);
	}

	public void enterZip(String text) {
		zipBox.sendKeys(text);
	}

	public void clickDefaultCheckBox() {
		defaultCheckBox.click();
	}

	public void clickAddNewAddressSideBar() {
		addNewAddressSideBar.click();
	}

	public int getAddressOneText() {
		return addressList.size();
	}

	public void clickDeleteAddressBtn() {
		deleteAddressButton.click();
	}

	public List<WebElement> getAddressList() {
		return addressList;
	}

//	public boolean checkAddress(String str) {
//		for (int i = 1; i <= addressList.size(); i++) {
//			WebElement childElements = addressList.get(i - 1).findElement(By.xpath("(//div[@class='account__address'])[" + i + "]//p"));
//			return childElements.getText().contains(str);
//
//		}
//		return false;
//	}

}
