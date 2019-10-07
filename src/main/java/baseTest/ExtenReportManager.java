package baseTest;

import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;


public class ExtenReportManager {
private static ExtentReports extent;
	
	
	public static ExtentReports getInstance(){
		
		if(extent==null){
			
			
			extent = new ExtentReports("C:\\Users\\Lenovo\\Desktop\\data\\extent.html",false,DisplayOrder.OLDEST_FIRST);
			//extent.loadConfig(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\extentconfig\\ReportsConfig.xml"));
			
		}
		
		return extent;
		
	}

}
