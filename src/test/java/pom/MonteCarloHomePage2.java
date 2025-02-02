package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MonteCarloHomePage2 {
	@FindBy(xpath = "//a[contains(text(), 'SHOP Blankets')]")
	private WebElement shopBlanketButton;

	public MonteCarloHomePage2(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void clickShopBlanketBtn() {
		shopBlanketButton.click();
	}
	
	public WebElement getShopBlanketElement() {
		return shopBlanketButton;
	}
	
}
