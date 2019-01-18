package com.avactis.test.integration.storepageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avactis.test.integration.model.Address;
//import com.gargoylesoftware.htmlunit.javascript.host.Console;

public class CheckoutStepthree {
	
	@FindBy(how= How.XPATH, using="//input[contains(@class,'button_place_order')]")	
	WebElement orderPlaced;
	
	 public CheckoutStepthree(){
		  PageFactory.initElements(Browser.driver(), this);    
	    
	  }
	
	 public void placeOrder(){
		 WebDriverWait waitForOrderPlacedButton = new WebDriverWait(Browser.driver(),20);
		 waitForOrderPlacedButton.until(ExpectedConditions.elementToBeClickable(orderPlaced));
		 orderPlaced.click();
	 }
}