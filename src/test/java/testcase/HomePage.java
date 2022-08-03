package testcase;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.internal.BaseTestMethod;

import actions.BaseTestPage;

public class HomePage extends BaseTestPage {
	private AndroidDriver<MobileElement> driver;
	private WebDriverWait wait;

	@BeforeClass
	public void launchApp() throws MalformedURLException {
		driver = connectApp();
	}

	@Test
	public void clickPosts() throws InterruptedException {
		MobileElement el = driver.findElement(By.id("fr.playsoft.vnexpress:id/tab_your_news"));
		el.click();
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}
}