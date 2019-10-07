package baseTest;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class KeyWords extends Base {

	public static boolean verifyTitle(String ExpectedTitle) {
		try {
			
			wait.until(ExpectedConditions.titleContains("Protractor practice website - Banking App"));
			Assert.assertEquals(driver.getTitle(), ExpectedTitle);
			return true;
		} catch (Exception e) {
			
			return false;
		}
	}

	public static Boolean isElementPresent(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void type(WebElement element, String value) {
		wait.until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(value);
	}

	public static void click(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}
	
	public static  String alertHandle()
	{
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert =driver.switchTo().alert();
		String text =alert.getText();
		alert.accept();
		return text;
		
	}

}
