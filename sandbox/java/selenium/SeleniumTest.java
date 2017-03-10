package selenium;

import com.thoughtworks.selenium.*;

import org.openqa.selenium.server.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

// https://automatictester.wordpress.com/2015/06/11/basic-cucumberjvm-selenium-webdriver-test-automation-framework/
public class SeleniumTest {
	public static Selenium selenium;
	public static SeleniumServer seleniumserver;

	@BeforeClass
	public static void setUp() throws Exception {
		RemoteControlConfiguration remoteConfiguration = new RemoteControlConfiguration();
		remoteConfiguration.setPort(4465); 

		// create and start proxy server
		seleniumserver = new SeleniumServer(remoteConfiguration);
		seleniumserver.start();

		// create and start browser.
		selenium = new DefaultSelenium("localhost", 4465, "*firefox", "http://www.google.com");
		selenium.start();
	}
	
	@Test
	public void testDefaultTNG() throws Exception {
		selenium.open("http://www.google.com/");
		selenium.windowMaximize();
	}

	@AfterClass
	public static void tearDown() throws InterruptedException {
		// stop browser then server
		selenium.stop();
		seleniumserver.stop();
	}
}