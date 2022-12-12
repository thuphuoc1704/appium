package com.vne.common;

import org.openqa.selenium.WebDriver;

import com.vne.pageobjects.LoginPO;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;

public class GeneratorManagerPO {
	public static LoginPO getLoginPage(WebDriver driver) {
		return new LoginPO(driver);	
	}
}
