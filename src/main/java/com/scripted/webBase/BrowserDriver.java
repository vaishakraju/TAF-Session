package com.scripted.webBase;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import java.io.ByteArrayInputStream;

import com.scripted.dataload.PropertyDriver;

import io.qameta.allure.Allure;

public class BrowserDriver {

	public static ThreadLocal<WebDriver> lthdriver = new ThreadLocal<>();
	public static String strBrowserName = null;
	public static Logger LOGGER = LogManager.getLogger(BrowserDriver.class);

	public static void funcGetWebdriver() {
		try {

			PropertyDriver p = new PropertyDriver();
				p.setPropFilePath("src/main/resources/Web/Properties/Browser.properties");
				strBrowserName = PropertyDriver.readProp("browserName");
			if (strBrowserName == null || strBrowserName.equals(" ")) {
				LOGGER.info("Browser name is null, please check the value of browserName in config.properties");
				System.exit(0);
			} else {
				LOGGER.info("Browser : " + strBrowserName);
				strBrowserName = strBrowserName.toLowerCase();

				switch (strBrowserName) {

				case "chrome":
					// Set path to chromedriver
					System.setProperty("webdriver.chrome.driver", "C:\\Users\\188162\\Downloads\\chromedriver-win64 (4)\\chromedriver-win64\\chromedriver.exe");

					// Create driver object
					WebDriver chromedriver = new ChromeDriver();
					lthdriver.set(chromedriver);
					break;

				case "edge":

					// Set EdgeDriver path
					System.setProperty("webdriver.edge.driver", "C:\\drivers\\msedgedriver.exe");

					// Create Edge driver
					WebDriver edgedriver = new EdgeDriver();
					lthdriver.set(edgedriver);
					break;
				}
			}

			// }
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error occurred while configuring webdrivers" + "Exception :" + e);
			Assert.fail("Webdriver initialisation issues" + "Exception :" + e);
			Thread.currentThread().interrupt();
		}
		if (lthdriver != null) {
			lthdriver.get().manage().deleteAllCookies();
			lthdriver.get().manage().window().maximize();
			lthdriver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
			lthdriver.get().manage().timeouts().scriptTimeout(Duration.ofSeconds(60));
			lthdriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		}
	}

	public static WebDriver getLthDriver() {
		if (lthdriver != null) {
			return lthdriver.get();
		} else {
			LOGGER.error("Driver value is null");
			return null;
		}
	}

	public static void launchWebURL(String strURL) {
		try {

			getLthDriver().get(strURL);
			LOGGER.info("Application launched successfully");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error occurred while launching Web URL" + "Exception :" + e);
			Assert.fail("Error occurred while launching Web URL" + "Exception :" + e);
		}
	}

	public static void closeBrowser() {
		try {
			getLthDriver().close();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error occurred while closing browser" + "Exception :" + e);
			Assert.fail("Error occurred while closing browser" + "Exception :" + e);
		}
	}

	public static void quitBrowser() {
		try {
			getLthDriver().quit();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error occurred while quit browser" + "Exception :" + e);
			Assert.fail("Error occurred while quit browser" + "Exception :" + e);
		}
	}
	
	   public static void captureAndAttachScreenshot(WebDriver driver, String attachmentName) {
	        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	        Allure.addAttachment(attachmentName, new ByteArrayInputStream(screenshotBytes));
	    }
	   
}
