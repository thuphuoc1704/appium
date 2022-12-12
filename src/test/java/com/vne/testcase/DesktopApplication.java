package com.vne.testcase;

import org.aspectj.lang.annotation.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;

public class DesktopApplication {

    private WindowsDriver<WebElement> winDriver = null;

    @BeforeClass
    public void setup() throws IOException {
    	Desktop desktop = Desktop.getDesktop();
    	desktop.open(new File ("C:\\Program Files (x86)\\Windows Application Driver\\WinAppDriver.exe"));
    	
        
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("app", "C:/MISA JSC/MISA Mimosa.NET 2022/Bin/MISA Mimosa.NET 2022.exe");
//            capabilities.setCapability("app", "C:\\Windows\\System32\\notepad.exe");
            
            //capabilities.setCapability("deviceName", "ST-GiangNH66");
            winDriver = new WindowsDriver<WebElement>(new URL("http://127.0.0.1:4723"), capabilities);
            winDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            sleepInSecond(15);
            System.out.println("title form login: "+winDriver.getTitle());
            //CalculatorResult = CalculatorSession.findElementByAccessibilityId("CalculatorResults");
            //Assert.assertNotNull(CalculatorResult);
            
    }

//    @AfterClass(alwaysRun = true)
    public void TearDown() throws IOException
    {
    	System.out.println("Close windows app");
    	Runtime.getRuntime().exec("taskkill /F /IM WinAppDriver.exe");
        if (winDriver != null) {
            winDriver.quit();
        }
        winDriver = null;
    }

    @Test
    public void Addition()
    {
//    	System.out.println("title: "+winDriver.getTitle());
//        sleepInSecond(30);
//    	winDriver.findElement(By.name("Đóng")).click();
//    	sleepInSecond(5);
//    	winDriver.findElement(By.name("  Tạo dữ liệu mẫu")).click();
    	sleepInSecond(12);
    	String parent=winDriver.getWindowHandle();
    	System.out.println("cha: "+parent);
    	System.out.println("title form login: "+winDriver.getTitle());
//    	sleepInSecond(12);
//    	Set<String>s=winDriver.getWindowHandles();
//    	Iterator<String> I1= s.iterator();
//    	System.out.println("size: "+s.size());
//    	while(I1.hasNext()){
//    	String child_window=I1.next();
//    	System.out.println("con: "+child_window);
//	    	if(!parent.equals(child_window)){
//	    		System.out.println("dddd: "+winDriver.switchTo().window(child_window).getTitle());
//	    		winDriver.findElement(By.name("  Mở dữ liệu kế toán")).click();
//	    		sleepInSecond(2);
//	    		winDriver.close();
//	    	}
//    	}
//    	winDriver.findElementByAccessibilityId("txtPassword_EmbeddableTextBox").sendKeys("thuphuoc");
//    	Assert.assertTrue(winDriver.findElementByXPath("//*[@AutomationId='frmLogin']//*[@AutomationId='Close']").isDisplayed());
//    	winDriver.findElementByXPath("//*[@AutomationId='frmLogin']//*[@AutomationId='Close']").click();
//    	sleepInSecond(10);
//    	Assert.assertTrue(winDriver.findElementByAccessibilityId("frmLogin").isDisplayed());
//    	winDriver.findElementByXPath("//*[@AutomationId='cboDatabase']//*[@AutomationId='1001']").sendKeys("thuphuoc");
//    	System.out.println("title form login: "+winDriver.getTitle());
//    	sleepInSecond(2);
//    	winDriver.findElement(By.name("Mở rộng    ")).click();
//    	sleepInSecond(2);
//    	winDriver.findElementByXPath("//*[@AutomationId='txtPassword_EmbeddableTextBox']").sendKeys("thuphuoc");
//    	winDriver.findElementByAccessibilityId("btnOk").click();
//    	sleepInSecond(5);
    	
    }

	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}