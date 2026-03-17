package com.scripted.web.POMClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.scripted.web.WebHandlers;


public class NewPage1 {
	
	@SuppressWarnings("unused")
	private WebDriver driver;
	
	@FindBy(xpath = "//*[@id='user-name']")
	private WebElement inputUsername;
	
	@FindBy(xpath = "//*[@id='password']")
	private WebElement inputPassword;
		
	@FindBy(xpath = "//*[@id='login-button']")
	private WebElement btnSubmit;
	
	@FindBy(xpath = "//div[@id='inventory_container' and @class='inventory_container']/div/div[1]/div[3]/button")
	private WebElement firstElementaddtocart;
	
	
	@FindBy(css = "button#add-to-cart-sauce-labs-backpack")
	private WebElement addtocart;
	
	@FindBy(css = "div#shopping_cart_container>a")
	private WebElement icon;
	
	
	@FindBy(xpath = "//a[@class='btn_action checkout_button']")
	private WebElement buttoncheckout;
	
	//scripts for clientPerf
	@FindBy(xpath = "//devsite-tabs[@class='upper-tabs']/nav/tab[3]/a")
	private WebElement solutions;
	

	public NewPage1(WebDriver driver) {
		this.driver = driver;
	}

	
	public void login (String username, String password ) throws InterruptedException {
		 Thread.sleep(3000);
        WebHandlers.enterText(inputUsername,username );
        WebHandlers.enterText(inputPassword,password );
        WebHandlers.click(btnSubmit);     
    }

    public void add_Product () throws InterruptedException {
        Thread.sleep(3000);
        WebHandlers.click(firstElementaddtocart);      
    }

}


