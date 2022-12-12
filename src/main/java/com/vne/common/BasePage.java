package com.vne.common;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vne.enums.CommonEnums.DIRECTION;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class BasePage {
	@SuppressWarnings("rawtypes")
	TouchAction action;
	@SuppressWarnings("static-access")
	public By getLocator(String locator) {
		By by = null;
		if (locator.startsWith("id=")) {
			by = by.id(locator.substring(3));
		} else if (locator.startsWith("name=")) {
			by = by.name(locator.substring(5));
		} else if (locator.startsWith("class=")) {
			by = by.className(locator.substring(6));
		} else if (locator.startsWith("css=")) {
			by = by.cssSelector(locator.substring(4));
		} else if (locator.startsWith("xpath=")) {
			by = by.xpath(locator.substring(6));

		} else {
			throw new RuntimeException("Locator invalid");
		}
		return by;
	}
	
	public String getLocatorDynamic(String variableLocator, String... dynamicValues) {
		if (variableLocator.startsWith("xpath=")) {
			variableLocator = String.format(variableLocator, (Object[]) dynamicValues);
		}
		return variableLocator;
	}
	
	public WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(getLocator(locator));
	}
	
	public WebElement getElementDynamic(WebDriver driver, String locator, String... dynamicLocator) {
		locator = getLocatorDynamic(locator, dynamicLocator);
		return driver.findElement(getLocator(locator));
	}
	
	public List<WebElement> getListElement(WebDriver driver, String locator) {
		return driver.findElements(getLocator(locator));
	}
	
	public List<WebElement> getListElementDynamic(WebDriver driver, String locator, String... dynamicLocator) {
		return driver.findElements(getLocator(getLocatorDynamic(locator, dynamicLocator)));
	}
	
	public int getElementSize(WebDriver driver, String locator) {
		return getListElement(driver, locator).size();
	}

	public int getElementSizeDynamic(WebDriver driver, String locator, String... dynamicLocator) {
		locator = getLocatorDynamic(locator, dynamicLocator);
		return getListElementDynamic(driver, locator).size();
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String locator, String textItem) {
		Select select = new Select(getElement(driver, locator));
		select.selectByValue(textItem);
	}

	public void selectItemDefaultDropdownDynamic(WebDriver driver,String locator, String textValue, String... dynamicLocator) {
		Select select = new Select(getElementDynamic(driver, locator, dynamicLocator));
		select.selectByVisibleText(textValue);
	}
	
	public void clickToElement(WebDriver driver, String locator) {
//		waitForClickToElement(driver, locator);
		getElement(driver, locator).click();
	}
	
	public void clickToElementDynamic(WebDriver driver, String locator,String... dynamicLocator) {
//		waitForClickToElementDynamic(driver, locator, dynamicLocator);
		getElementDynamic(driver, locator,dynamicLocator).click();
	}
	
	public void senkeyToElement(WebDriver driver, String locator, String textSenkey) {
		WebElement element = getElement(driver, locator);
		element.clear();
		getElement(driver, locator).sendKeys(textSenkey);
	}
	
	public void senkeyToElementDynamic(WebDriver driver, String textSenkey,String locator,String... dynamicLocator) {
		WebElement element = getElementDynamic(driver, getLocatorDynamic(locator, dynamicLocator));
		element.clear();
		getElementDynamic(driver, locator, dynamicLocator).sendKeys(textSenkey);
	}
	
	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')",
				getElement(driver, locator));
	}
	
	public void scrollToElement(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
	}
	
	public String getElementText(WebDriver driver, String locator) {
		return getElement(driver, locator).getText();
	}

	public String getElementTextDynamic(WebDriver driver, String variableLocator, String... dynamicLocator) {
		variableLocator = getLocatorDynamic(variableLocator, dynamicLocator);
		return getElement(driver, variableLocator).getText().trim();
	}
	
	public void openPageUrl(WebDriver driver, String url) {
		driver.get(url);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSourcerCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshpage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 3);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public void getAlertText(WebDriver driver) {
		waitForAlertPresence(driver).getText();
	}

	public void sendkeyToAlert(WebDriver driver, String textSenkey) {
		waitForAlertPresence(driver).sendKeys(textSenkey);
	}

	public boolean isElementDisplay(WebDriver driver, String locator) {
		waitForELementVisible(driver, locator);
		return getElement(driver, locator).isDisplayed();
	}

	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}
	
	public boolean isElSelected(WebDriver driver, String locator) {
		return getElement(driver, locator).isSelected();
	}

	public boolean isElEnable(WebDriver driver, String locator) {
		return getElement(driver, locator).isEnabled();
	}

	public void switchToFrameIframe(WebDriver driver, String locator) {
		driver.switchTo().frame(getElement(driver, locator));
	}

	public void switchToDefaultContent(WebDriver driver, String locator) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(driver, locator)).perform();
	}
	

	public void waitForELementVisible(WebDriver driver, String locator) {
		WebDriverWait explicitwait = new WebDriverWait(driver, 30);
		explicitwait.until(ExpectedConditions.visibilityOfElementLocated(getLocator(locator)));
	}

	public void waitForELementVisible(WebDriver driver, String variableLocator, String... dynamicLocator) {
		WebDriverWait explicitwait = new WebDriverWait(driver, 30);
		explicitwait.until(ExpectedConditions
				.visibilityOfElementLocated(getLocator(getLocatorDynamic(variableLocator, dynamicLocator))));
	}

	public void waitForAllELementVisible(WebDriver driver, String locator) {
		WebDriverWait explicitwait = new WebDriverWait(driver, 30);
		explicitwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getLocator(locator)));
	}

	public void waitForELementInVisible(WebDriver driver, String locator) {
		WebDriverWait explicitwait = new WebDriverWait(driver, 30);
		explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(getLocator(locator)));
	}

	public void waitForAllELementInVisible(WebDriver driver, String locator) {
		WebDriverWait explicitwait = new WebDriverWait(driver, 30);
		explicitwait.until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver, locator)));
	}

	public void waitForClickToElement(WebDriver driver, String locator) {
		WebDriverWait explicitwait = new WebDriverWait(driver, 30);
		explicitwait.until(ExpectedConditions.elementToBeClickable(getLocator(locator)));
	}

	public void waitForClickToElementDynamic(WebDriver driver, String variableLocator, String... dynamicLocator) {
		WebDriverWait explicitwait = new WebDriverWait(driver, 30);
		explicitwait.until(ExpectedConditions
				.elementToBeClickable(getLocator(getLocatorDynamic(variableLocator, dynamicLocator))));
	}
	
	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getElement(driver, locator));
	}
	
	public void sleepSecond(int second) {
		try {
			Thread.sleep(second *1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void swipeScreenToElementDynamic(PerformsTouchActions driver, DIRECTION direction,String listElement, String...dynamicLocator) {
		((WebDriver) driver).manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		int sizeList=getElementSizeDynamic((WebDriver) driver, listElement,dynamicLocator);
		while(sizeList==0) {
			swipe(driver, direction, 1000);
			sizeList=getElementSizeDynamic((WebDriver) driver, listElement,dynamicLocator);
		}
		((WebDriver) driver).manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	public void swipeScreenToElement(PerformsTouchActions driver, DIRECTION direction,String listElement) {
		((WebDriver) driver).manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		int sizeList=getElementSize((WebDriver) driver, listElement);
		while(sizeList==0) {
			swipe(driver, direction, 1000);
			sizeList=getElementSize((WebDriver) driver, listElement);
		}
		((WebDriver) driver).manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	@SuppressWarnings("rawtypes")
	public void dropAndDrag(PerformsTouchActions driver, String el1, String el2) {
		action = new TouchAction(driver);
		action.longPress(new LongPressOptions().withElement(new ElementOption()
				.withElement(getElement((WebDriver) driver, el1))))
		.waitAction()
		.moveTo(new ElementOption().withElement(getElement((WebDriver) driver, el2))).perform();
	}
	
	@SuppressWarnings("rawtypes")
	public void dropAndDragToPoint(PerformsTouchActions driver, String el1, int x, int y) {
		action = new TouchAction(driver);
		action.longPress(new LongPressOptions().withElement(new ElementOption()
			  .withElement(getElement((WebDriver) driver, el1))))
			  .waitAction()
			  .moveTo(PointOption.point(x, y)).perform();
	}
	
	@SuppressWarnings("rawtypes")
	public void swipe(PerformsTouchActions driver, DIRECTION direction, long duration) {
        int startX = 0;
        int endX = 0;
        int startY = 0;
        int endY = 0;
        int height = getScreenHeight((WebDriver) driver);
        int width = getScreenWidth((WebDriver) driver);
        switch (direction) {
            case LEFT:
                startY = (int) (height / 2);
                startX = (int) (width * 0.90);
                endX = (int) (width * 0.05);
                new TouchAction(driver)
                        .press(PointOption.point(startX, startY))
                        .waitAction(new WaitOptions().withDuration(Duration.ofMillis(duration)))
                        .moveTo(PointOption.point(endX, startY))
                        .release()
                        .perform();
                break;
            case RIGHT:
                startY = (int) (height / 2);
                startX = (int) (width * 0.05);
                endX = (int) (width * 0.90);
                new TouchAction(driver)
		                .press(PointOption.point(startX, startY))
		                .waitAction(new WaitOptions().withDuration(Duration.ofMillis(duration)))
		                .moveTo(PointOption.point(endX, startY))
		                .release()
		                .perform();
                break;
            case UP:
                endY = (int) (height * 0.80);
                startY = (int) (height * 0.20);
                startX = (width / 2);
                new TouchAction(driver)
		                .press(PointOption.point(startX, startY))
		                .waitAction(new WaitOptions().withDuration(Duration.ofMillis(duration)))
		                .moveTo(PointOption.point(startX, endY))
		                .release()
		                .perform();
                break;
            case DOWN:
                startY = (int) (height * 0.80);
                endY = (int) (height * 0.20);
                startX = (width / 2);
                new TouchAction(driver)
		                .press(PointOption.point(startX, startY))
		                .waitAction(new WaitOptions().withDuration(Duration.ofMillis(duration)))
		                .moveTo(PointOption.point(startX, endY))
		                .release()
		                .perform();
                break;
        }
    }

    public void srollHorizonFromEl1ToEl2(PerformsTouchActions driver, WebElement el1, WebElement el2) {
    	int midOfY =el2.getLocation().y +(el2.getSize().height/2);
		int fromXLocation=el2.getLocation().x;
		int toXLocation=el1.getLocation().x;						
		@SuppressWarnings("rawtypes")
		TouchAction  action =new TouchAction(driver);
		action.press(PointOption.point(fromXLocation, midOfY))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
		.moveTo(PointOption.point(toXLocation, midOfY))
		.release()
		.perform();
    }
    
    public void pullToRefreshPage(PerformsTouchActions driver, Float device ) {
    	int deviceWidth = ((WebDriver) driver).manage().window().getSize().getWidth();
		int deviceHeight = ((WebDriver) driver).manage().window().getSize().getHeight();
		int midX = (deviceWidth / 2);
		int midY = (deviceHeight / 2);
		int bottomEdge = (int) (deviceHeight * device);
		@SuppressWarnings("rawtypes")
		TouchAction  action =new TouchAction(driver);
		action.press(PointOption.point(midX, midY))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
		.moveTo(PointOption.point(midX, bottomEdge))
		.release().perform(); 
    }
	 
  public int getScreenWidth(WebDriver driver) {
        return driver.manage().window().getSize().getWidth();
    }

    public int getScreenHeight(WebDriver driver) {
        return driver.manage().window().getSize().getHeight();
    }
    
	@SuppressWarnings("rawtypes")
	public void tapPoint(PerformsTouchActions driver,int x, int y) {
		action =new TouchAction(driver);
		action.tap(PointOption.point(x,y))
		.perform();
	}
}
