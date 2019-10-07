package baseTest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.apache.velocity.texen.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

public class TestUtils extends Base {

	@DataProvider(name = "DataTable")

	public Object[][] getData(String sheetName,Method m) {

		String tabName = m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		int rowfound=0;
		for(int i=0;i<rows;i++)
		{
			String data =excel.getCellData("Data", 0, i);
			if(data.equalsIgnoreCase(tabName))
			{
				rowfound=i;break;
			}
		}
		int size=0;
        for(int i=rowfound;i<rows;i++)
        {
        	String data2 =excel.getCellData("Data", 0, i);
        	if(data2.equals(""))
        	{
        		size=i;break;
        	}
        }
		Object[][] data = new Object[rows - 1][1];

		Hashtable<String, String> table = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

			table = new Hashtable<String, String>();

			for (int colNum = 0; colNum < cols; colNum++) {

				// data[0][0]
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;
			}

		}

		return data;

	}
	 public static String getScreenhot() throws Exception {
		 String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		 String screenshotName = dateName.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		 TakesScreenshot ts = (TakesScreenshot) driver;
		 File source = ts.getScreenshotAs(OutputType.FILE);
		                //after execution, you could see a folder "FailedTestsScreenshots" under src folder
		 String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots1/"+dateName+".png";
		 File finalDestination = new File(destination);
		 FileUtils.copyFile(source, finalDestination);
		 return destination;
		 }
}
