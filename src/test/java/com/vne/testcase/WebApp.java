package com.vne.testcase;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebApp {
	static AppiumDriver<MobileElement> driverAndroid;
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		WebDriverManager.chromedriver().driverVersion("74.0.3729.6").setup();
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		cap.setCapability("chromedriverExecutable", WebDriverManager.chromedriver().driverVersion("74.0.3729.6").getDownloadedDriverPath());
		cap.setCapability("chromeOptions", ImmutableMap.of("w3c", false));
		driverAndroid = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		driverAndroid.get("https://vnexpress.net/");
		Thread.sleep(5000);
	}
}
