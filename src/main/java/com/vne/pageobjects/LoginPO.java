package com.vne.pageobjects;

import org.openqa.selenium.WebDriver;

import com.vne.common.BasePage;
import com.vne.interfaces.BasePageUI;
import com.vne.interfaces.LoginPageUI;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;

public class LoginPO extends BasePage{
	private WebDriver driver;
//	private MobileDriver<MobileElement>driver;
	public LoginPO(WebDriver driver) {
		this.driver = driver;
	}

	public void LoginWithUserNameAndPassword(String userName, String password) throws InterruptedException {
		clickToElement(driver, BasePageUI.ICON_MENU);
		clickToElement(driver, LoginPageUI.LOGIN_REGISTER);
		senkeyToElement(driver, LoginPageUI.TXT_EMAIL, userName);
		senkeyToElement(driver, LoginPageUI.TXT_PASSWORD, password);
		clickToElement(driver, LoginPageUI.BTN_LOGIN);
	}
}
