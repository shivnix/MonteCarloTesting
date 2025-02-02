package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MonteCarloHomepage {
	@FindBy(xpath = "//span[contains(text(), 'Login/Signup')]")
	private WebElement login_signup_button;

	public MonteCarloHomepage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void loginPageButton() {
		login_signup_button.click();
	}

}
