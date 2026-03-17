package com.scripted.runners;




import com.scripted.webBase.BrowserDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "Features/web/sauceDemo.feature", plugin = { "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm","json:target/cucumber.json","html:target/cucumber.html" }, glue = { "com.scripted.webTestscripts" }, monochrome = true, tags = "@saucedemo")
public class WebRunner extends AbstractTestNGCucumberTests {
	
	
}
