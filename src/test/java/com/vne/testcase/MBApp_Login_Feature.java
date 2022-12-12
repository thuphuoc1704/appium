package com.vne.testcase;

import java.net.MalformedURLException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vne.common.BaseTest;
import com.vne.common.GeneratorManagerPO;
import com.vne.enums.CommonEnums.DIRECTION;
import com.vne.interfaces.BasePageUI;
import com.vne.interfaces.LoginPageUI;
import com.vne.pageobjects.LoginPO;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class MBApp_Login_Feature extends BaseTest {
	private AndroidDriver<MobileElement> driver;
	LoginPO loginPage;

	@BeforeClass
	public void launchApp() throws MalformedURLException {
		deleteAllFilesInReportAllure();
		driver = initDevice("ANDROID");
		loginPage=GeneratorManagerPO.getLoginPage(driver);
	}

	@Test
	public void Login_01_InvalidEmail() throws InterruptedException {
		driver.resetApp();
		loginPage.clickToElement(driver, BasePageUI.ICON_MODE_READ);
		loginPage.clickToElement(driver, BasePageUI.ICON_CLOSE_MODE_READ);
		loginPage.LoginWithUserNameAndPassword("ptestvne", "123456");
		verifyEquals(loginPage.getElementText(driver, LoginPageUI.MESSAGE_INVALID_EMAIL), "Email không hợp lệ", "Check invalid email");
	}
	
	@Test
	public void Login_02_InvalidPass() throws InterruptedException {
		driver.resetApp();
		loginPage.LoginWithUserNameAndPassword("ptestvne@gmail.com", "123453");
		verifyEquals(loginPage.getElementText(driver, LoginPageUI.MESSAGE_INVALID_EMAIL), "Sai mật khẩu. Bạn", "Check invalid password");
	}
	
	@Test
	public void Login_03_ValidEmailAndPass() throws InterruptedException {
		driver.resetApp();
		loginPage.LoginWithUserNameAndPassword("ptestvne@gmail.com", "123456");
		verifyTrue(loginPage.isElementDisplay(driver, LoginPageUI.NAME_SUCESS_LOGIN),"Login is sucessfull");
	}
	
	@Test
	public void Login_04_ScrollDownToGiaoDuc() {
		driver.resetApp();
		loginPage.clickToElement(driver, BasePageUI.ICON_MENU);
		loginPage.swipeScreenToElementDynamic(driver,DIRECTION.DOWN, BasePageUI.MENU_IN_ICON_MENU_DYNAMIC,"Giáo dục");
		loginPage.clickToElementDynamic(driver, BasePageUI.MENU_IN_ICON_MENU_DYNAMIC,"Giáo dục");
		loginPage.sleepSecond(3);
	}
	
	@Test
	public void Login_05_ScrollUpToThoiSu() {
		driver.resetApp();
		loginPage.clickToElement(driver, BasePageUI.ICON_MENU);
		loginPage.swipeScreenToElementDynamic(driver,DIRECTION.DOWN, BasePageUI.MENU_IN_ICON_MENU_DYNAMIC,"Xem nhiều");
		loginPage.swipeScreenToElementDynamic(driver,DIRECTION.UP, BasePageUI.MENU_IN_ICON_MENU_DYNAMIC,"Thời sự");
		loginPage.clickToElementDynamic(driver, BasePageUI.MENU_IN_ICON_MENU_DYNAMIC,"Thời sự");
		loginPage.sleepSecond(5);
	}
	
	@Test
	public void Login_06_ScrollLeftToThoiSu() {
		driver.resetApp();
		loginPage.swipeScreenToElementDynamic(driver,DIRECTION.LEFT, BasePageUI.MENU_IN_FOLDER_DYMAMIC,"Tâm sự");
		loginPage.clickToElementDynamic(driver, BasePageUI.MENU_IN_FOLDER_DYMAMIC,"Tâm sự");
		loginPage.sleepSecond(5);
	}

	@Test
	public void Login_07_SrollHorizonFolderInMenu() {
		driver.resetApp();
		loginPage.clickToElement(driver, BasePageUI.ICON_MODE_READ);
		loginPage.clickToElement(driver, BasePageUI.ICON_CLOSE_MODE_READ);
		int sizeKinhDoanh= loginPage.getElementSizeDynamic(driver, BasePageUI.MENU_IN_FOLDER_DYMAMIC,"Sức khỏe");
		while (sizeKinhDoanh==0) {
			sizeKinhDoanh= loginPage.getElementSizeDynamic(driver, BasePageUI.MENU_IN_FOLDER_DYMAMIC,"Sức khỏe");
			loginPage.srollHorizonFromEl1ToEl2(driver, loginPage.getElementDynamic(driver, BasePageUI.LIST_MENU, "1"),
					loginPage.getElementDynamic(driver, BasePageUI.LIST_MENU, "2"));
		}
		loginPage.clickToElementDynamic(driver, BasePageUI.MENU_IN_FOLDER_DYMAMIC,"Sức khỏe");
		loginPage.sleepSecond(5);
	}
	
	@Test
	public void Login_08_PullToRefreshPage() {
		driver.resetApp();
		loginPage.clickToElementDynamic(driver, BasePageUI.MENU_IN_FOLDER_DYMAMIC, "Podcasts");
		loginPage.swipeScreenToElementDynamic(driver, DIRECTION.DOWN, BasePageUI.PODCAST_PROGRAM,"Tài chính cá nhân");
		loginPage.pullToRefreshPage(driver, 8.5f);
	}
	
	@Test
	public void Login_09_DragAndDrop() {
		driver.resetApp();
		loginPage.clickToElement(driver, BasePageUI.ICON_MODE_READ);
		loginPage.dropAndDragToPoint(driver, BasePageUI.ICON_CIRCLE_MODE_READ,439,374);
		loginPage.sleepSecond(3);
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}
}
