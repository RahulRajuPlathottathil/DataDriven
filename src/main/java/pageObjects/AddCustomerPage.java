package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import baseTest.Base;
import baseTest.KeyWords;

public class AddCustomerPage {

	WebDriver driver;

	public AddCustomerPage(WebDriver driver) {
      this.driver=driver;
	}

	String firstName = Base.getElement("//firstName//xpath");
	String lastName = Base.getElement("//lastName//xpath");
	String postCode = Base.getElement("//postCode//xpath");
	String addcustomer =Base.getElement("//buttonAddcustomer//xpath");
	
	public void AddCusomer(String fName,String lName,String SpostCode)
	{
		WebElement E_fname =driver.findElement(By.xpath(firstName));
		WebElement E_lname =driver.findElement(By.xpath(lastName));
		WebElement E_postCode =driver.findElement(By.xpath(postCode));
		WebElement E_addcustomer =driver.findElement(By.xpath(addcustomer));
		
        KeyWords.type(E_fname, fName);
        KeyWords.type(E_lname, lName);
        KeyWords.type(E_postCode, SpostCode);
        KeyWords.click(E_addcustomer);
	}
	
	public void alertHandles()
	{
		String alertText =KeyWords.alertHandle();
		String customerID =(alertText.split(":"))[1];
		System.out.println(customerID);
	}
}
