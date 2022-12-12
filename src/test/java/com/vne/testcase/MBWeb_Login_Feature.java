package com.vne.testcase;

import java.net.MalformedURLException;

import org.openqa.selenium.support.ui.Sleeper;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vne.common.BaseTest;
import com.vne.common.GeneratorManagerPO;
import com.vne.enums.CommonEnums.DIRECTION;
import com.vne.interfaces.BasePageUI;
import com.vne.pageobjects.LoginPO;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class MBWeb_Login_Feature extends BaseTest {
	private AndroidDriver<MobileElement> driver;
	LoginPO loginPage;

	@BeforeClass
	public void launchApp() throws MalformedURLException {
		deleteAllFilesInReportAllure();
		driver = initBrowser("ANDROID");
		loginPage=GeneratorManagerPO.getLoginPage(driver);
	}
	
	@Test
	public void Login_01_OpenPage() throws InterruptedException {
		loginPage.openPageUrl(driver, "https://vnexpress.net");
		loginPage.clickToElementDynamic(driver, BasePageUI.WEB_MENU_IN_FOLDER,"Thời sự");
		loginPage.sleepSecond(10);
		loginPage.clickToElementDynamic(driver, BasePageUI.WEB_SUB_MENU_IN_FOLDER_LV1,"Chính trị");
		loginPage.sleepSecond(10);
		loginPage.selectItemDefaultDropdownDynamic(driver, BasePageUI.WEB_SUB_MENU_IN_FOLDER_LV2, "Giao thông", "Chính trị");
		loginPage.sleepSecond(5);
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}
}
