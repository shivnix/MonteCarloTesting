package genericLibraries;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class WebDriverUtilities {
	public WebDriver driver;

	public void mouseHover(WebElement ele, WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.moveToElement(ele).perform();
	}

	public void rightClick(WebElement ele) {
		Actions actions = new Actions(driver);
		actions.contextClick(ele).perform();
	}

	public void doubleClick(WebElement ele) {
		Actions actions = new Actions(driver);
		actions.doubleClick(ele).perform();
	}

	public void dropDown(WebElement ele, String text) {
		Select select = new Select(ele);
		select.selectByVisibleText(text);
	}

	public void frameWithWebElementAddress(WebElement ele, WebDriver driver) {
		driver.switchTo().frame(ele);
	}

	public void frameWithIndex(int index, WebDriver driver) {
		driver.switchTo().frame(index);
	}

	public void switchingbackFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void alertAcceptPopup(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void alertDismissPopup(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public void alertPopupText(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		Reporter.log(alert.getText(), true);
	}

	public void scrollbarTopToBottom(WebDriver driver) {
		JavascriptExecutor javascript = (JavascriptExecutor) driver;
		javascript.executeScript("window.scrollBy(0,5000)");
	}

	public void scrollbarBottomToTop(WebDriver driver) {
		JavascriptExecutor javascript = (JavascriptExecutor) driver;
		javascript.executeScript("window.scrollBy(0,-5000)");
	}

	public void scrollBar(int x, int y, WebDriver driver) {
		JavascriptExecutor javascript = (JavascriptExecutor) driver;
		javascript.executeScript("window.scrollBy(" + x + "," + y + ")");
	}

	public void scrollToElement(WebElement ele, WebDriver driver) {
		JavascriptExecutor javascript = (JavascriptExecutor) driver;
		javascript.executeScript("arguments[0].scrollIntoView({block: 'center'});", ele);
	}

	public void switchChildWindow(WebDriver driver) {
		Set<String> child = driver.getWindowHandles();

		for (String ch : child) {
			driver.switchTo().window(ch);
		}
	}

	public void multipleTabs(int index, WebDriver driver) {
		Set<String> child = driver.getWindowHandles();
		List<String> tabs = new ArrayList<>(child);
		driver.switchTo().window(tabs.get(index));
	}

	public void waitElement(WebElement ele, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void waitElementClick(WebElement ele, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public void waitRefreshed(List<WebElement> ele, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(ele)));
	}

}
