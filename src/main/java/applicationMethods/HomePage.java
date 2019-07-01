package applicationMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import driver.SeleniumDrivers;

public class HomePage {

	public WebDriver webdriver = null;

	String recommendedRibbonXpath = "//div[text()='Recommended']";
	String recommendedRibbontileXpath = "//div[text()='Recommended']/../following-sibling::div//div[contains(@class,'carousel-tile-wrapper')]";
	String tileNameXpath = "//div[text()='Recommended']/../following-sibling::div//h3/div";
	String favButtonXpath = "//div[text()='Recommended']/../following-sibling::div//i[contains(@class,'flipIconCore')]";

	public HomePage() {
		webdriver = SeleniumDrivers.getDriver();
	}

	/*
	 * @author Ankit Jain scroll to the given ribbon
	 */
	public void scrollToRibbon(String ribbonName) throws Exception {
		if (ribbonName.equals("recommended")) {
			WebElement recommendedRibbon = webdriver.findElement(By.xpath(recommendedRibbonXpath));
			((JavascriptExecutor) webdriver).executeScript("arguments[0].scrollIntoView(true);", recommendedRibbon);
			Reporter.log("scroll to recommended ribbon", true);
		} else {
			Reporter.log("Please provide proper ribbon Name", true);
			throw new Exception("Please provide proper ribbon Name");
		}
	}

	/*
	 * @author Ankit In Recommended ribbon mark given number of program as
	 * favorite
	 */
	public ArrayList<String> makeprogramFav(int numberOfProgram) throws Exception {
		ArrayList<String> ProgramName = new ArrayList<String>();
		List<WebElement> recommendedRibbontile = webdriver.findElements(By.xpath(recommendedRibbontileXpath));
		List<WebElement> recommendedRibbontileName = webdriver.findElements(By.xpath(tileNameXpath));
		List<WebElement> recommendedRibbontileFavButton = webdriver.findElements(By.xpath(favButtonXpath));

		for (int i = 0; i < numberOfProgram; i++) {
			Actions act = new Actions(webdriver);
			act.moveToElement(recommendedRibbontile.get(i)).perform();
			TimeUnit.SECONDS.sleep(5);
			String Program = recommendedRibbontileName.get(i).getText();
			Reporter.log("Given Program is mark as Fav " + Program, true);
			if (Program.equals(null)) {
				Reporter.log("Program name is not present", true);
				throw new Exception("Program name is not present");
			}
			ProgramName.add(Program);
			recommendedRibbontileFavButton.get(i).click();
			TimeUnit.SECONDS.sleep(5);
		}
		return ProgramName;
	}

	/*
	 * @author Ankit Jain This method will navigate to the given URl
	 */
	public void navigateTo(String url) throws Exception {
		webdriver.get(url);

	}
}
