package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
//	@FindBy(xpath = "//span[contains(text(), 'Login/Signup')]")
//	private WebElement login_signup_button;

	@FindBy(id = "customer[email]")
	private WebElement emailAddress;

	@FindBy(id = "customer[password]")
	private WebElement password;

	@FindBy(xpath = "//span[text()='Login']")
	private WebElement loginBtn;

	@FindBy
	private WebElement LoginPageTitle;

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

//	public void loginPageButton() {
//		login_signup_button.click();
//	}

	public void emailAddressTextBox(String email) {
		emailAddress.sendKeys(email);
	}

	public void passwordTextBox(String pwd) {
		password.sendKeys(pwd);
	}

	public void clickLoginButton() {
		loginBtn.click();
	}

	public String getLoginPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

}
