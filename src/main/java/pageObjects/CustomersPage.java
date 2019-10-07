package pageObjects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import baseTest.Base;
import baseTest.KeyWords;

public class CustomersPage {
	
	public CustomersPage(WebDriver driver)
	{
		this.driver=driver;
	}
	WebDriver driver;
	
	String Customer = Base.getElement("//customers//xpath");
	String header   = Base.getElement("//header//xpath");
	String customerTable =Base.getElement("//customerTable//xpath");
	String Search        =Base.getElement("//Search//xpath");
	
	
	public boolean Search(String CustomerName,String Action)
	{
		List<String> data= new ArrayList();
		KeyWords.click(driver.findElement(By.xpath(Customer)));
		List cusList =driver.findElements(By.xpath(customerTable));
		System.out.println("Elements = "+cusList.size());
		for(int i=0;i<cusList.size();i++)
		{
			List<WebElement> subTable = driver.findElements(By.xpath("(//tr[contains(@ng-repeat,'cust in Customers')])["+(i+1)+"]/td"));
			System.out.println(subTable.size());
			
			for(int j=0;j<subTable.size();j++)
			{
					
				data.add(driver.findElement(By.xpath("(//tr[contains(@ng-repeat,'cust in Customers')])["+(i+1)+"]/td["+(j+1)+"]")).getText());
			    
			}
			 
		}
		System.out.println(data);
		if(data.contains(CustomerName))
		{
			System.out.println(CustomerName);
		   return true;
		}
		else
		{
			System.out.println("Not Found");
			return false;
		}
	}
	
	public void SearchForCust(String Name)
	{
		driver.findElement(By.xpath("//Search//xpath")).sendKeys(Name);
	}

}
