package com.scripted.web;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.scripted.webBase.BrowserDriver;

public class WebHandlers {

	public static Logger LOGGER = LogManager.getLogger(WebHandlers.class);

	/**
	 * Clicks a given element
	 * 
	 * @param locator Element locator
	 */
	public static void click(WebElement locator) {
		try {
			locator.click();
			LOGGER.info("Click action completed successfully for the locator: " + locator);
		} catch (Exception e) {
			LOGGER.error("Error while performing the click action for the locator: " + locator + "Exception :" + e);
			Assert.fail("Error while performing the click action for the locator: " + locator + "Exception :" + e);
		}

	}

	public static void enterText(WebElement locator, String value) {
		try {
			locator.clear();
			locator.sendKeys(value);
		} catch (Exception e) {
			LOGGER.error("Error while enterting the text for the locator: " + locator + "Exception :" + e);
			Assert.fail("Error while entering the text for the locator: " + locator + "Exception :" + e);

		}
	}

	public static void waitForElementPresence(By byEle, int time, WebElement locator) {
		try {
			WebDriverWait wait = new WebDriverWait(BrowserDriver.getLthDriver(), Duration.ofSeconds(time),
					Duration.ofSeconds(10));
			wait.until(ExpectedConditions.presenceOfElementLocated(byEle));
		} catch (Exception e) {
			e.printStackTrace();

		}

	}
	
	public static void waitForElementvisible(By byEle, int time, WebElement locator) {
		try {
			WebDriverWait wait = new WebDriverWait(BrowserDriver.getLthDriver(), Duration.ofSeconds(time),
					Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(locator));
		} catch (Exception e) {
			e.printStackTrace();

		}

	}
	
	public static void pageWait() {
		try {
			BrowserDriver.getLthDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
			BrowserDriver.getLthDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(60));
			BrowserDriver.getLthDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error occurred while page wait" + "Exception :" + e);
			Assert.fail("Error occurred while page wait" + "Exception :" + e);
		}
	}
}
