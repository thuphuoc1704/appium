package com.vne.common;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.vne.enums.CommonEnums.MOBILEPLATFORMNAME;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
//BaseTest chứa các hàm như open browser, closed browser, verify, random number, get day, month, year
public class BaseTest {
	private AndroidDriver<MobileElement>driver;
	private WebDriverWait wait;
	protected final Logger log ;
	
	protected BaseTest() {
		log = Logger.getLogger(getClass());
	}
	
	public AndroidDriver<MobileElement> initDevice(String platformName) throws MalformedURLException {
		MOBILEPLATFORMNAME platform= MOBILEPLATFORMNAME.valueOf(platformName.toUpperCase());
		DesiredCapabilities caps = new DesiredCapabilities();
		if(platform.equals(MOBILEPLATFORMNAME.ANDROID)) {
	        caps.setCapability("deviceName", "a11");
	        caps.setCapability("udid", "emulator-5554");
	        caps.setCapability("platformName", "Android");
	        caps.setCapability("platformVersion", "11.0");
	        caps.setCapability("skipUnlock", "true");
	        caps.setCapability("appPackage", "fr.playsoft.vnexpress");
	        caps.setCapability("appActivity", "fr.playsoft.vnexpress.ActivityMain");
	        caps.setCapability("noReset", "false");
	        return driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		}else {
	        caps.setCapability("deviceName", "iPhone 7");
	        caps.setCapability("udid", "emulator-5554");
	        caps.setCapability("platformName", "Android");
	        caps.setCapability("platformVersion", "11.0");
	        caps.setCapability("skipUnlock", "true");
	        caps.setCapability("appPackage", "fr.playsoft.vnexpress");
	        caps.setCapability("appActivity", "fr.playsoft.vnexpress.ActivityMain");
	        caps.setCapability("noReset", "false");
	        return driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		}
	}
	
	public AndroidDriver<MobileElement> initBrowser(String platformName) throws MalformedURLException {
		MOBILEPLATFORMNAME platform= MOBILEPLATFORMNAME.valueOf(platformName.toUpperCase());
		DesiredCapabilities caps = new DesiredCapabilities();
		if(platform.equals(MOBILEPLATFORMNAME.ANDROID)) {
			WebDriverManager.chromedriver().driverVersion("83.0.4103.39").setup();
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
			caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
			caps.setCapability("chromedriverExecutable", WebDriverManager.chromedriver().driverVersion("83.0.4103.39").getDownloadedDriverPath());
			return driver=new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		}else {
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.2");
			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
			caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 11");
			return driver=new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		}
	}
	
	protected boolean verifyTrue(boolean condition, String mess) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition, mess);
			log.info(mess);
			Allure.step(mess+ " | PASSED", Status.PASSED);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(mess);
			log.info(" -------------------------- FAILED -------------------------- ");
			Allure.step(mess+ " | FAILED", Status.FAILED);
			pass = false;

			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}
	
	protected boolean verifyFalse(boolean condition, String mess) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition, mess);
			log.info(mess);
			Allure.step(mess+ " | PASSED", Status.PASSED);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(mess);
			Allure.step(mess+ " | FAILED", Status.FAILED);
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}
	
	protected boolean verifyEquals(Object actual, Object expected, String mess) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected, mess);
			log.info("actual: " + actual + " == expected: " + expected);
			Allure.step(mess + " | PASSED", Status.PASSED);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info("actual: " + actual + " != expected: " + expected);
			Allure.step(mess+ " | FAILED", Status.FAILED);
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}
	
	public void deleteAllFilesInReportAllure() {
		System.out.println("---------- START delete file in folder ----------");
		deleteAllFileInFolder();
		System.out.println("---------- END delete file in folder ----------");
	}
	
	public void deleteAllFileInFolder() {
		try {
			String pathFolderDownload = GlobalConstance.PROJECT_PATH + "/allure-results";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for(int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					System.out.println(listOfFiles[i].getName());
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
}

