package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserAccountPage {
	@FindBy(linkText = "Addresses")
	private WebElement addressesButton;

	public UserAccountPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void clickAddressesBtn() {
		addressesButton.click();
	}
	
}
