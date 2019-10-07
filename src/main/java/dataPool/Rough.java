package dataPool;

import java.util.Hashtable;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseTest.Constatnts;

public class Rough {
	
	@Test(dataProviderClass=DataProviders.class,dataProvider="Data")
	
	public void TestData1(Hashtable<String, String> table)
	{
		System.out.println(table.get("TC_ID"));
	}

	
	
	
}
