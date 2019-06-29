package driver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;

public class SeleniumDrivers {
	
	public static WebDriver webDriver;
	
	@BeforeTest
	@Parameters({"browser","url"})
	public static void initializeDriver(String browser,String url)
	{
		Reporter.log("Test will execute in :"+browser,true);
		if(browser.equals("Mozila"))
		{
			System.setProperty("webdriver.firefox.marionette","./BrowserDriver/geckodriver.exe");
			webDriver = new FirefoxDriver();
		}else
		{
			if(browser.equals("Chrome"))
			{
				System.setProperty("webdriver.chrome.driver", "./BrowserDriver/chromedriver.exe");
				webDriver=new ChromeDriver();
			}
		}
		webDriver.manage().window().maximize();
		webDriver.get(url);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Reporter.log(browser+"Browser is launched",true);
	}
	
	public static WebDriver getDriver()
	{
		return webDriver;
	}
	
	@AfterTest
	public void quitDriver()
	{
		Reporter.log("Quiting the current driver session",true);
		webDriver.quit();
	}
}
