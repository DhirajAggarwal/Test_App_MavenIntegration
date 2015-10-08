package foo;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AppTest {

	AndroidDriver driver;
	DesiredCapabilities capability = new DesiredCapabilities();

	@BeforeClass
	public void setup() throws MalformedURLException {

		capability.setCapability(MobileCapabilityType.APP_PACKAGE,
				"com.google.android.apps.maps");
		capability.setCapability(MobileCapabilityType.APP_ACTIVITY,
				"com.google.android.maps.MapsActivity");
		capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capability.setCapability(MobileCapabilityType.PLATFORM_VERSION, "19");
		capability.setCapability(MobileCapabilityType.DEVICE_NAME,
				"0cbbdc0a032f9efa");
		capability
				.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
				capability);
	}

	@Test
	public void test1_Start() {
		WebElement Search = driver.findElement(By
				.id("com.google.android.apps.gmm:id/search_omnibox_text_box"));
		//com.google.android.apps.maps:id/textbox //com.google.android.apps.gmm:id/search_omnibox_text_box//com.google.android.apps.gmm:id/search_omnibox_text_box
		Search.sendKeys("Delhi");
		driver.pressKeyCode(AndroidKeyCode.ENTER);

	}
}
