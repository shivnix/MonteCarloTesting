package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StoresPage {
	@FindBy(id = "searchInput")
	private WebElement searchBox;

	@FindBy(xpath = "//div[@class='store-card']")
	private List<WebElement> stores;

	@FindBy(xpath = "//a[normalize-space()='Get Directions']")
	private WebElement clickGetDirectionBtn;

	public StoresPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void enterInSearchBox(String search) {
		searchBox.sendKeys(search);
	}

	public void clickGetDirectionBtn() {
		clickGetDirectionBtn.click();
	}

	public boolean pinCodeVerification() {
		if (stores.size() == 1) {
			return true;
		} else {
			return false;
		}
	}

}
