package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header {

	@FindBy(xpath = "//span[contains(text(), 'Wishlist')]")
	private WebElement wishListButton;

	@FindBy(xpath = "//span[contains(text(),'Login/')]")
	private WebElement loginSignupButton;
	
	@FindBy(xpath = "(//span[@class='header__logo'])[3]")
	private WebElement montheCarloHomeLogo;

	public Header(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void clickWishListButton() {
		wishListButton.click();
	}

	public void clickLoginSignupButton() {
		loginSignupButton.click();
	}
	
	public void clickMonteCarloHomeLogoButton() {
		montheCarloHomeLogo.click();
	}

}
