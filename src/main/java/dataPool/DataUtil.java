package dataPool;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseTest.ExcelReader;

public class DataUtil {
	
	/*public static boolean isTestRunnable(String testName)
	{
		ExcelReader excel = new ExcelReader(Constants.TestDataSuit);
		String sheetName =Constants.TestDataSuit;
		
		
	}*/
	
	
	
	public static boolean isSuitRunnable(String suitName)
	{
		ExcelReader excel = new ExcelReader(Constants.TestDataSuit);
		String sheetName =Constants.SuiteSheet;
		int rowCount =excel.getRowCount(sheetName);
		
		for(int i=2;i<=rowCount;i++)
		{
			String Data = excel.getCellData(sheetName, Constants.SuiteName, i);
			
			if(Data.equalsIgnoreCase(suitName))
			{
				if(excel.getCellData(sheetName, Constants.RunMode, i).equalsIgnoreCase(Constants.RunMode_Yes))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	@DataProvider
	
	public static Object[][] getData(String SheetName,String testcase,ExcelReader excel)
	{
		
		//ExcelReader excel = new ExcelReader("C:\\Users\\Lenovo\\Desktop\\data\\DataSheet.xlsx");
		int rows =excel.getRowCount(SheetName);
		
		int testCaseRowNum=1;
		
		for(testCaseRowNum=1;testCaseRowNum<rows;testCaseRowNum++)
		{
			String testName =excel.getCellData(SheetName, 0, testCaseRowNum);
			 
			if(testName.equalsIgnoreCase(testcase))
			{
				System.out.println("Found");
				break;
			}
			
			
		}
		System.out.println("TestCase starts from :"+testCaseRowNum);
		
		int dataStartRowNum=testCaseRowNum+2;
		int testRows=0;
		while(!excel.getCellData(SheetName, 0, dataStartRowNum+testRows).equals(""))
		{
			testRows++;
		}
		System.out.println("Test Data count ="+testRows);
		int testColNumber =testCaseRowNum+1;
		int testcols=0;
		while(!excel.getCellData(SheetName, testcols, testColNumber).equals(""))
		{
			testcols++;
		}
		
		System.out.println("Test columns ="+testcols);
		
		Object[][] data =new Object[testRows][1];
		int p=0;
		for(int a =dataStartRowNum;a<(dataStartRowNum+testRows);a++)
			
		{
			Hashtable<String, String> table = new Hashtable<>();
			
			for(int b=0;b<testcols;b++)
			{
				String testData = excel.getCellData(SheetName, b, a);
				String colNames =excel.getCellData(SheetName, b, testColNumber);
				System.out.println(colNames+"  "+testData);
				table.put(colNames, testData);
			}
			data[p][0]=table;
			p++;
		}
		return data;
	}

}
