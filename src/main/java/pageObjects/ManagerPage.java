package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import baseTest.Base;
import baseTest.KeyWords;

public class ManagerPage {
	
	WebElement addCustomer;
	WebElement OpenAccount;
	WebElement customers;
	WebElement Home;
	WebDriver driver;
	
	public ManagerPage(WebDriver driver)
	{
	try{
	  this.driver=driver;	
	  addCustomer = driver.findElement(By.xpath(Base.getElement("//addCustomer//xpath")));
	  OpenAccount = driver.findElement(By.xpath(Base.getElement("//openAccount//xpath")));
	  customers = driver.findElement(By.xpath(Base.getElement("//customers//xpath")));
	  Home = driver.findElement(By.xpath(Base.getElement("//Home//xpath")));
	   }
	catch(Exception e){};
	}
	
	
	public void addCustomer()
	{
		KeyWords.click(addCustomer);
	}
	
	public void openAccount()
	{
		
	}
	public void customer()
	{
		
	}

}


