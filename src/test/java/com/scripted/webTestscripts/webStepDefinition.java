package com.scripted.webTestscripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.scripted.web.POMClasses.NewPage1;
import com.scripted.webBase.BrowserDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm;




public class webStepDefinition {
	
	WebDriver driver;
	
	NewPage1 objLoginPage;
	
	@Given("I am on the login page")
	public void saveMART1() throws Throwable {
		objLoginPage = PageFactory.initElements(BrowserDriver.getLthDriver(), NewPage1.class);
		BrowserDriver.launchWebURL("https://www.saucedemo.com/v1/");
	}

	@When("I login with username and password")
	public void user_pass() throws Throwable {
		BrowserDriver.captureAndAttachScreenshot(driver, "HomePage");
       objLoginPage.login("standard_user", "secret_sauce");
		         
	}

	@Then("User add product to the cart")
	public void add_product() throws InterruptedException {
		objLoginPage.add_Product();

	}
	
	@After("@saucedemo")
	public void after(Scenario scenario) {
		
        System.out.println("-----------------------------------------");
        System.out.println("Completed Scenario with tags -> ");
        scenario.getSourceTagNames().stream().forEach(System.out::println);
        System.out.println("-----------------------------------------");    
        driver.quit();
	}
	

	@Before("@saucedemo")
	public void invokeBrowser() {
		
		BrowserDriver.funcGetWebdriver();
		driver = BrowserDriver.getLthDriver();
		
	}

}
