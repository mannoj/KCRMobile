import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class KPMobileAutomation {

	public KPMobileAutomation() {
	}

	@Before
	public void setUp() throws Exception {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("appium-version", "1.0");
				capabilities.setCapability("platformName", "iOS");
				capabilities.setCapability("platformVersion", "8.3");
				capabilities.setCapability("deviceName", "iPhone 6 Plus");
				AppiumDriver wd = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
				wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				wd.close();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		
		fail("Not yet implemented");
	}

}
