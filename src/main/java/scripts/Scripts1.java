package scripts;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.Test;

import applicationMethods.HomePage;
import applicationMethods.MyVideosPage;
import driver.SeleniumDrivers;
import jsonReader.JsonReader;

public class Scripts1 extends SeleniumDrivers {
	/*
	 * @author Ankit Jain Discovery Take Home Assessment Mark some recommended
	 * content as favorite and verify same program is showing in my video page
	 */

	public static WebDriver webdriver = null;

	@Test(invocationCount = 1)
	public void script1() throws Exception {
		webdriver = SeleniumDrivers.getDriver();
		JSONObject jObject = JsonReader.jsoncreater("s1.json");
		HomePage home = new HomePage();
		home.scrollToRibbon(jObject.get("HomePageRibbon").toString());
		ArrayList<String> programNames = home
				.makeprogramFav(Integer.parseInt(jObject.get("numberofProgram").toString()));
		home.navigateTo(jObject.get("myvideoUrl").toString());
		MyVideosPage myvideoPage = new MyVideosPage();
		myvideoPage.scrollToRibbon(jObject.get("MyVideoRibbon").toString());
		myvideoPage.isProgramPresent(programNames.get(0));
		TimeUnit.SECONDS.sleep(5);
		myvideoPage.isProgramPresent(programNames.get(1));
	}
}
