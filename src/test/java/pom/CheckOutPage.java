package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtilities;

public class CheckOutPage {
	
	private WebDriverUtilities utilities = new WebDriverUtilities();

	public CheckOutPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//button[contains(@class,'_1m2hr9ge _1m2hr9gd _1fragemss _1fragemlj _1fragemnk _1fragem2i _1fragems6')])[3]")
	private WebElement useDifferentAdd;

	public void useDiffAddBtn(WebDriver driver) {
		utilities.waitElement(useDifferentAdd, driver);
		useDifferentAdd.click();
	}
	
	@FindBy(xpath = "//div[@role='cell']//p")
	private WebElement productName;
	
	public String getProductName() {
		return productName.getText();
	}
	
	@FindBy(xpath = "//input[@id='shipping-address1']")
	private WebElement addressField;
	
	public void enterAddress(String add, WebDriver driver) {
		utilities.waitElement(addressField, driver);
		addressField.sendKeys(add);
	}
	
	@FindBy(xpath = "//ul[@id='shipping-address1-options']/li")
	private List<WebElement> addressList;
	
	public void getAddress(int index, WebDriver driver) {
		WebElement ele = addressList.get(index-1);
		utilities.waitElement(ele, driver);
		ele.click();
	}


}
