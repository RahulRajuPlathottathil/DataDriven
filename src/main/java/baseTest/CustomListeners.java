package baseTest;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;


public class CustomListeners extends Base implements ITestListener {

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		test =rep.startTest(arg0.getName().toUpperCase());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub		
		try{
			
			//String screencatch= TestUtils.captureScreenshot();
			
	String Scree =TestUtils.getScreenhot();
	System.out.println(Scree);
	test.log(LogStatus.FAIL, test.addScreenCapture(Scree));
		test.log(LogStatus.FAIL, arg0.getThrowable());
		ExcelReporter.ExcelReport(arg0.getName().trim().toUpperCase(), "123", "12344444", "2002121", "RAH", "RAJ", "FAIL");
		rep.endTest(test);
		rep.flush();
		}
	catch(Exception e){e.printStackTrace();}	
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		test.log(LogStatus.SKIP, arg0.getName().toUpperCase());
		rep.endTest(test);
		rep.flush();
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		test.log(LogStatus.PASS, arg0.getName().toUpperCase());
		rep.endTest(test);
		rep.flush();
		ExcelReporter.ExcelReport(arg0.getName().trim().toUpperCase(), arg0.getName().trim().toUpperCase(), "12344444", "2002121", "RAH", "RAJ", "PASS");
	}

}
