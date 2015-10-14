package foo;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AppTest {

	AndroidDriver driver;
	String System_time, Latest_Trip_Time;

	DesiredCapabilities capabilities = new DesiredCapabilities();

	@BeforeClass
	public void setup() throws MalformedURLException {

		capabilities
				.setCapability(
						"app",
						"C:\\Users\\dhiraj01\\Downloads\\smartdriver-android-1.6-SNAPSHOT[staging644].apk");
		capabilities.setCapability("appPackage", "com.tower.smartdriver");
		capabilities.setCapability("appActivity", ".activity.WelcomeActivity");
		capabilities.setCapability("deviceName", "HT489WM03504");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appWaitActivity",
				".activity.WelcomeActivity");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);
	}

	@Test
	public void test1_Landing() throws InterruptedException {

		WebElement Landing_Login_link = driver.findElement(By
				.id("com.tower.smartdriver:id/welcome_login"));

		Landing_Login_link.click();
	}

	@Test
	public void test2_Login() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By
				.id("android:id/action_bar_title")));

		WebElement Login_email_textbox = driver.findElement(By
				.id("com.tower.smartdriver:id/email_field"));

		WebElement Login_password_textbox = driver.findElement(By
				.id("com.tower.smartdriver:id/password_field"));

		Login_email_textbox.sendKeys("nexus@tower.com");
		Login_password_textbox.sendKeys("Test@1234");
		driver.navigate().back();

		WebElement Login_Submit_button = driver.findElement(By
				.id("com.tower.smartdriver:id/submit_btn"));
		Login_Submit_button.click();
	}

	@Test
	public void test3_NavigateToMenu() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 90);

		wait.until(ExpectedConditions.elementToBeClickable(By
				.id("android:id/alertTitle")));

		WebElement Continue_button = driver.findElement(By
				.id("android:id/button1"));
		Continue_button.click();

		wait.until(ExpectedConditions.elementToBeClickable(By
				.id("com.tower.smartdriver:id/start_trip_btn")));

		WebElement Navigation_button = driver.findElement(By
				.id("android:id/up"));
		Navigation_button.click();

		List<WebElement> Navigation_Menu = driver.findElements(By
				.id("com.tower.smartdriver:id/navigation_item_name"));
		Navigation_Menu.get(0).click();

	}

	@Test
	public void test5_NavigateToHome() throws InterruptedException,
			MalformedURLException {
		WebDriverWait wait = new WebDriverWait(driver, 180);

		WebElement Navigation_button = driver.findElement(By
				.id("android:id/up"));
		Navigation_button.click();

		List<WebElement> Navigation_Menu = driver.findElements(By
				.id("com.tower.smartdriver:id/navigation_item_name"));
		Navigation_Menu.get(5).click();

		WebElement AutoStart_Toggle = driver.findElement(By
				.id("com.tower.smartdriver:id/toggle_button1"));
		AutoStart_Toggle.click();

		wait.until(ExpectedConditions.elementToBeClickable(By
				.id("android:id/message")));

		WebElement AutoStart_Toggle_Success_Dialog = driver.findElement(By
				.id("android:id/button1"));
		AutoStart_Toggle_Success_Dialog.click();

		Navigation_button.click();
		Navigation_Menu.get(0).click();

		wait.until(ExpectedConditions.elementToBeClickable(By
				.id("com.tower.smartdriver:id/start_trip_btn")));

		driver.openNotifications();
		wait.until(
				ExpectedConditions.elementToBeClickable(By
						.id("android:id/action0"))).click();
		driver.navigate().back();

		if (wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.id("com.tower.smartdriver:id/start_trip_btn"))) != null) {

			driver.findElement(By.id("com.tower.smartdriver:id/start_trip_btn"))
					.click();

		}

		wait.until(ExpectedConditions.textToBePresentInElement(
				driver.findElement(By
						.id("com.tower.smartdriver:id/log_trip_distance_display")),
				"2.2"));

		/*
		 *  * driver.openNotifications();
		 * wait.until(ExpectedConditions.elementToBeClickable(By
		 * .id("com.android.systemui:id/backgroundNormal"))); List<WebElement>
		 * Play_Stop = driver.findElements(By .id("android:id/action0"));
		 * Play_Stop.get(1).click(); driver.navigate().back();
		 */

		driver.findElement(By.id("com.tower.smartdriver:id/stop_trip_btn_anim"))
				.click();

		wait.until(ExpectedConditions.elementToBeClickable(By
				.id("com.tower.smartdriver:id/distance_driven_label")));
		// WebElement Navigation_button = driver.findElement(By
		// .id("android:id/action_bar_title"));
		Navigation_button.click();

		// List<WebElement> Navigation_Menu = driver.findElements(By
		// .id("com.tower.smartdriver:id/navigation_item_name"));
		Navigation_Menu.get(0).click();

	}

	@Test
	public void test6_NavigateToTripHistory() throws InterruptedException {

		WebElement Navigation_button = driver.findElement(By
				.id("android:id/action_bar_title"));
		Navigation_button.click();

		List<WebElement> Navigation_Menu = driver.findElements(By
				.id("com.tower.smartdriver:id/navigation_item_name"));
		Navigation_Menu.get(1).click();

		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.elementToBeClickable(By
				.id("android:id/list")));

		List<WebElement> Trip_History_Detail = driver.findElements(By
				.className("android.widget.LinearLayout"));
		Trip_History_Detail.get(4).click();

		wait.until(ExpectedConditions.elementToBeClickable(By
				.id("android:id/action_bar_title")));

		driver.navigate().back();
		driver.navigate().back();
	}

	@Test
	public void test7_NavigateToLeaderBoard() throws InterruptedException,
			IOException {

		WebElement Navigation_button = driver.findElement(By
				.id("android:id/action_bar_title"));
		Navigation_button.click();

		List<WebElement> Navigation_Menu = driver.findElements(By
				.id("com.tower.smartdriver:id/navigation_item_name"));
		Navigation_Menu.get(2).click();

		WebDriverWait wait = new WebDriverWait(driver, 90);

		Thread.sleep(4000);

		List<WebElement> all_NewZealand = driver.findElements(By
				.className("android.widget.RelativeLayout"));

		all_NewZealand.get(0).click();

		wait.until(ExpectedConditions.elementToBeClickable(By
				.id("com.tower.smartdriver:id/pinned_header")));

		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("D:\\screenshot.png"));

		driver.navigate().back();
		driver.navigate().back();
	}

	@Test
	public void test8_NavigateToSettings() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 90);

		WebElement Navigation_button = driver.findElement(By
				.id("android:id/action_bar_title"));
		Navigation_button.click();

		List<WebElement> Navigation_Menu = driver.findElements(By
				.id("com.tower.smartdriver:id/navigation_item_name"));
		Navigation_Menu.get(5).click();

		WebElement AutoStart_Toggle = driver.findElement(By
				.id("com.tower.smartdriver:id/toggle_button1"));
		AutoStart_Toggle.click();

		wait.until(ExpectedConditions.elementToBeClickable(By
				.id("android:id/message")));

		WebElement AutoStart_Toggle_Success_Dialog = driver.findElement(By
				.id("android:id/button1"));
		AutoStart_Toggle_Success_Dialog.click();

		Navigation_button.click();
		Navigation_Menu.get(0).click();
	}

}
