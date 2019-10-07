package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import baseTest.Base;
import baseTest.KeyWords;

public class HomePage {

	WebElement customerLogin_btn;
	WebElement bankManagerLogin_btn;
	WebElement Title;
	WebElement Home;
	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
	this.driver=driver;	
	customerLogin_btn = driver.findElement(By.xpath(Base.getElement("//CustomerLogin//xpath")));
    bankManagerLogin_btn = driver.findElement(By.xpath(Base.getElement("//BankManagerLogin//xpath")));
    Title = driver.findElement(By.xpath(Base.getElement("//Title//xpath")));
	Home = driver.findElement(By.xpath(Base.getElement("//Home//xpath")));
	}
	
	public void verifyTitle(String ExpectedTitle)
	{
		KeyWords.verifyTitle(ExpectedTitle);
	}
	
	public void isHomePresent()
	{
		KeyWords.isElementPresent(Home);
	}
	public void isCustomerLogin()
	{
		KeyWords.isElementPresent(customerLogin_btn);
	}
	public void isBankManagerLogin()
	{
		KeyWords.isElementPresent(bankManagerLogin_btn);
	}
	
	public void HomePage(String ExpectedTitle)
	{

		//KeyWords.verifyTitle(ExpectedTitle);
		KeyWords.click(bankManagerLogin_btn);
	}

}
