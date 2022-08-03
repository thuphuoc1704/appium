package actions;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class BaseTestPage {
	private AndroidDriver<MobileElement>driver;
	private WebDriverWait wait;
	public AndroidDriver<MobileElement> connectApp() throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "a11");
        caps.setCapability("udid", "emulator-5554"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "11.0");
        caps.setCapability("skipUnlock", "true");
        caps.setCapability("appPackage", "fr.playsoft.vnexpress");
        caps.setCapability("appActivity", "fr.playsoft.vnexpress.ActivityMain");
        caps.setCapability("noReset", "false");
        return driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
//        wait = new WebDriverWait(driver, 10);
	}
}
