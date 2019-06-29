package applicationMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import driver.SeleniumDrivers;
import sun.java2d.pipe.hw.AccelDeviceEventListener;

public class MyVideosPage {

	public  WebDriver webdriver = null;
	
	String videosULikeRibbonXpath="//div[text()='Videos You Might Like']/../following-sibling::div";
	String rightarrowRibbonXpath="//div[contains(@class,'right-arrow')]";
	String programNameXpath="//div[contains(@class,'thumbnailTile__title')]";
	
	public MyVideosPage()
	{
		webdriver=SeleniumDrivers.getDriver();
	}
	
	/*@author Ankit Jain
	 * scroll to the given ribbon
	*/
	public void scrollToRibbon(String ribbonName) throws Exception
	{
		if(ribbonName.equals("Videos You Might Like"))
		{
		WebElement videoULikeRibbon = webdriver.findElement(By.xpath(videosULikeRibbonXpath));
		((JavascriptExecutor) webdriver).executeScript("arguments[0].scrollIntoView(true);", videoULikeRibbon);
		}else
		{
			Reporter.log("Please provide proper ribbon Name",true);
			throw new Exception("Please provide proper ribbon Name");
		}
	}
	
	/*@author Ankit Jain
	 * verify given program is present in the video you like ribbon
	*/
	public void isProgramPresent(String programName) throws Exception
	{
		List<WebElement> AllLikedProgram = webdriver.findElement(By.xpath(videosULikeRibbonXpath)).findElements(By.xpath(programNameXpath));
		boolean ispresent = false;
		int count=0;
		while(true)
		{
		for(int i=0;i<AllLikedProgram.size();i++)
		{
			if(AllLikedProgram.get(i).getText().equals(programName))
			{
				
				Reporter.log("Given Program is present : "+programName, true);
				//To reset ribbon state 
				webdriver.navigate().refresh();
				webdriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				ispresent= true;
				break;
			}
		}
			if(ispresent==true)
			{
				Reporter.log("Program is present so breaking the while loop", true);
				break;
			}
			if(count>=20 && ispresent==false)
			{
				Reporter.log("Given program is not present in Video u like ribbon. Program Name :"+programName, true);
				//To reset ribbon state 
				webdriver.navigate().refresh();
				throw new Exception("Given program is not present in Video u like ribbon");
			}
			if(count<=20 && ispresent==false)
			{
				webdriver.findElement(By.xpath(videosULikeRibbonXpath)).findElement(By.xpath(rightarrowRibbonXpath)).click();
			}
		count++;
		}
		
	}
	
	
}
