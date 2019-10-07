package dataPool;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import baseTest.ExcelReader;

public class DataProviders {
	
	@DataProvider(name ="Data")
	
	public static Object[][] getDataSuite1(Method m)
	{
		ExcelReader excel = new ExcelReader(Constants.DataSuit);
		String testcase =m.getName();
		System.out.println(testcase);
		return DataUtil.getData(Constants.BankManagerSheet, testcase, excel);
	}

}
