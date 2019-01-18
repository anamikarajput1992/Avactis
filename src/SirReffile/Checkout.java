package com.avactis.test.integration.storepageobjects;

import static org.testng.Assert.assertTrue;

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
import com.avactis.test.integration.utility.ConfigProperties;
//import com.avactis.test.integration.utility.Constant;
//import com.gargoylesoftware.htmlunit.javascript.host.Console;

import org.openqa.selenium.support.ui.LoadableComponent;

public class Checkout extends LoadableComponent<Checkout>{
	@FindBy(how= How.NAME, using="billingInfo[Firstname]")	
	private WebElement billingFirstName;
	
	@FindBy(how= How.NAME, using="billingInfo[Lastname]")	
	private WebElement billingLastName;
	
	@FindBy(how= How.NAME, using="billingInfo[Email]")	
	private WebElement billingEmail;
	
	@FindBy(how= How.NAME, using="billingInfo[Country]")	
	private WebElement billingCountry;
	
	@FindBy(how= How.NAME, using="billingInfo[Postcode]")	
	private WebElement billingPostcode;
	
	@FindBy(how= How.NAME, using="billingInfo[Statemenu]")
	private WebElement billingStatemenu;
	
	@FindBy(how= How.NAME, using="billingInfo[City]")
	private WebElement billingCity;
	
	@FindBy(how= How.NAME, using="billingInfo[Streetline1]")
	private WebElement billingStreetline1;
	
	@FindBy(how= How.NAME, using="billingInfo[Streetline2]")
	private WebElement billingStreetline2;
	
	@FindBy(how= How.NAME, using="billingInfo[Phone]")
	private WebElement billingPhone;
	
	@FindBy(how= How.NAME, using="checkbox_shipping_same_as_billing")
	private WebElement chShippingAsBilling;
	
	@FindBy(how= How.XPATH, using="//div[@class='checkout_buttons']/input[contains(@class,'button_continue_checkout')]")
	WebElement continueCheckout;

	//private static String title = Constant.HomePageTitle;
	public Checkout() {
		PageFactory.initElements(Browser.driver(), this);	 
		    
	}
	
	@Override
    public void load() {
        //
	}
	
	@Override
    public void isLoaded() {
		//assertTrue(Browser.driver().getTitle().equals(title));
    }
	
	
	public void fillBillingAddress() {
	//	WebDriverWait waitForFirstFiledPresent = new WebDriverWait(Browser.driver(),30);
	//	waitForFirstFiledPresent.until(ExpectedConditions.elementToBeSelected(billingFirstName));
		billingFirstName.sendKeys(ConfigProperties.getProperty("BA_FIRST_NAME"));
		billingLastName.sendKeys(ConfigProperties.getProperty("BA_LAST_NAME"));
		billingEmail.sendKeys(ConfigProperties.getProperty("BA_EMAIL_ID"));
		billingCountry.sendKeys(ConfigProperties.getProperty("BA_COUNTRY"));
		billingPostcode.sendKeys(ConfigProperties.getProperty("BA_ZIP"));
		billingStatemenu.sendKeys(ConfigProperties.getProperty("BA_STATE"));
		billingCity.sendKeys(ConfigProperties.getProperty("BA_CITY"));
		billingStreetline1.sendKeys(ConfigProperties.getProperty("BA_ADD1"));
		billingStreetline2.sendKeys(ConfigProperties.getProperty("BA_ADD2"));
		billingPhone.sendKeys(ConfigProperties.getProperty("BA_CONTACT_PHONE"));
		
	}
	
	public void fillShippingAddress(){
	
		chShippingAsBilling.click();	
	}

	public CheckoutSteptwo continueCheckout(boolean pleasefillbillingAndShipping) {
		if (pleasefillbillingAndShipping)
		{
			fillBillingAddress();
			fillShippingAddress();
		}
		continueCheckout.click();
		CheckoutSteptwo checkoutsteptwo = new CheckoutSteptwo();
		  return checkoutsteptwo;
						
	}
}
