package testCases;

import java.util.HashMap;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.Base;
import baseTest.ExcelReporter;
import baseTest.TestUtils;
import pageObjects.AddCustomerPage;
import pageObjects.CustomersPage;
import pageObjects.HomePage;
import pageObjects.ManagerPage;

public class BankManagerLogin extends Base {
	
	@Test(dataProviderClass=TestUtils.class,dataProvider="DataTable")
	public void BankManagerLogin(Hashtable<String, String> data)
	{
		;
		System.out.println(data.get("firstname"));
		Assert.assertTrue(false);
		/*AddCustomerPage customerPage = new AddCustomerPage(driver);
		CustomersPage customers = new CustomersPage(driver);
		HomePage homePage       = new HomePage(driver);	
		homePage.HomePage("XYZ Bank");
		
		ManagerPage managerPage = new ManagerPage(driver);
		managerPage.addCustomer();
		customerPage.AddCusomer("Rahul", "Raju", "12356");
		customerPage.alertHandles();
		customers.SearchForCust("Rahul");*/
		
	}

}
