package baseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;



public class Base {

	public static WebDriver driver;
	public static Properties configurations = new Properties();
    public static File or = new File(Constatnts.OR_path);
	public static String browser;
	public static WebDriverWait wait;
	public static String isClearCookies;
	public static Document document;
	public static ExcelReader excel;
	public ExtentTest test;  

	/*
	 * initializer module
	 */
	public static ExtentReports rep;
	@BeforeSuite
	public void init()
	{
		rep = ExtenReportManager.getInstance();
		  
	}
	
	@AfterSuite
	public void cleanUp()
	{
		ExcelReporter.reportFlush();
	}
	@BeforeTest
	public static void driverSetUp() {
		
		excel = new ExcelReader("C:\\Users\\Lenovo\\Desktop\\data\\DataSheet.xlsx");
		 //System.out.println(excel.getCellData("Driver", "TESTCASE NAME", 2));
		try {
			configurations.load(new FileInputStream(new File(Constatnts.configuration_file)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        SAXReader orReader = new SAXReader();
        try {
			document=orReader.read(or);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		browser = configurations.getProperty("browser").trim();
		switch (browser.toUpperCase()) {
		case "CHROME":
			//DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
			//chromeCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

			System.setProperty("webdriver.chrome.driver", Constatnts.chromePath);
			driver = new ChromeDriver();
			break;
		case "IE":
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			System.setProperty("webdriver.ie.driver", Constatnts.iePath);
			driver = new InternetExplorerDriver();
			break;
		case "FIREFOX":
			DesiredCapabilities firefoxCapabilities = DesiredCapabilities.firefox();
			firefoxCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			System.setProperty("webdriver.gecko.driver", Constatnts.fireFoxPath);
			driver = new FirefoxDriver();
			break;
		default:
			break;
		}

		isClearCookies = configurations.getProperty("cookies").trim();
		if (isClearCookies.equalsIgnoreCase("ALL")) {
			driver.manage().deleteAllCookies();
		} else if (!(isClearCookies.equals("ALL")) && isClearCookies.length() > 0 && (!isClearCookies.equals("NO"))) {
			driver.manage().deleteCookieNamed(isClearCookies);
		} else {
			System.out.println("No cookies clearance ");
		}

		// Wait interface implementation calls
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(configurations.getProperty("implicite.timeout")),
				TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
		driver.manage().window().maximize();
		driver.get(configurations.getProperty("url"));

	}
	
	public static String getElement(String location)
	{
		System.out.println(document.selectSingleNode(location).getText());
		return document.selectSingleNode(location).getText();
	}

	@AfterTest
	public static void tearDown() {
		if (driver != null) {
			//driver.quit();
		}
	}
}
