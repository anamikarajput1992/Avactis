package com.avactis.test.integration.storepageobjects;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.*;

import com.avactis.test.integration.model.Address;
import com.avactis.test.integration.utility.ConfigProperties;
import com.avactis.test.integration.utility.WaitTool;
//import com.gargoylesoftware.htmlunit.javascript.host.Console;

public class CheckoutSteptwo extends LoadableComponent<CheckoutSteptwo> {
	private static Logger Log = Logger.getLogger(CheckoutSteptwo.class.getName());
	
	@FindBy(how= How.XPATH, using="//input[@class,'payment_method_name']/input[text(),'CashOnDelivery']") 
	WebElement paymentMethod;
	
	@FindBy(how= How.XPATH, using="//div[@class ='shipping_method_name']/label/input[text()='Ground Shipping']")
	WebElement shippingMethod;
			
	@FindBy(how= How.XPATH, using="//div[@class='checkout_buttons']/input[contains(@onclick,'submitStep(2)')]")
	WebElement continueCheckoutStepThree;
	
	// Fields for Authrize.Net (Credit Card) - (Start)
	//The value attribute is constant across various themes for 
	//a particular payment method.
	//For Authorized.Net (Credit Card) method the value is 3A3B380E-2CE1-A5B5-E6D5-6A8735B28219
	@FindBy(how = How.XPATH, using="//input[@value='3A3B380E-2CE1-A5B5-E6D5-6A8735B28219']/parent::div/following-sibling::div[1]/div/div[1]/div/select")
	WebElement 	authorizeDotNetCardType;
	
	@FindBy(how = How.XPATH, using="//input[@value='3A3B380E-2CE1-A5B5-E6D5-6A8735B28219']/parent::div/following-sibling::div[1]/div/div[4]/div/span/input")
	WebElement 	authorizeDotNetCardNumber;
	
	@FindBy(how = How.XPATH, using="//input[@value='3A3B380E-2CE1-A5B5-E6D5-6A8735B28219']/parent::div/following-sibling::div[1]/div/div[6]/div/span/input")
	WebElement 	authorizeDotNetCardVerificationCode;
	
	@FindBy(how = How.XPATH, using="//input[@value='3A3B380E-2CE1-A5B5-E6D5-6A8735B28219']/parent::div/following-sibling::div[1]/div/div[8]/div/select")
	WebElement 	authorizeDotNetCardExpirationMonth;

	@FindBy(how = How.XPATH, using="//input[@value='3A3B380E-2CE1-A5B5-E6D5-6A8735B28219']/parent::div/following-sibling::div[1]/div/div[10]/div/select")
	WebElement 	authorizeDotNetCardExpirationYear;

	@FindBy(how = How.NAME, using="creditCardInfoA76A1B62_DAC2_409D_97B2_0DDA6710E55F[CreditCardType]")
	WebElement 	stripeCardType;
	
	@FindBy(how = How.XPATH, using="//input[@value='A76A1B62-DAC2-409D-97B2-0DDA6710E55F']/parent::div/following-sibling::div[1]/div/div[4]/div/span/input")
	WebElement 	stripeCardNumber;
		
	@FindBy(how = How.XPATH, using="//input[@value='A76A1B62-DAC2-409D-97B2-0DDA6710E55F']/parent::div/following-sibling::div[1]/div/div[6]/div/span/input")
	WebElement 	stripeCardVerificationCode;
	
	@FindBy(how = How.XPATH, using="//input[@value='A76A1B62-DAC2-409D-97B2-0DDA6710E55F']/parent::div/following-sibling::div[1]/div/div[8]/div/select")
	WebElement 	stripeCardExpirationMonth;

	@FindBy(how = How.XPATH, using="//input[@value='A76A1B62-DAC2-409D-97B2-0DDA6710E55F']/parent::div/following-sibling::div[1]/div/div[10]/div/select")
	WebElement 	stripeCardExpirationYear;
	//Fields for Authrize.Net (Credit Card) - (End)
	
	  public CheckoutSteptwo(){
		  PageFactory.initElements(Browser.driver(), this);    
		  get();
	  }
	
		public void selectPaymentMethod(String paymentMethodName)
		{
			boolean paymentMethodNotAvailable = false;
			List<WebElement> paymentMethods = WaitTool.waitForListElementsPresent(Browser.driver(),By.xpath("//div[@class ='payment_method_name']"),30);
		//	System.out.println("No. of payment methods - " + paymentMethods.size());
			Log.info("No. of payment methods - " + paymentMethods.size());
			for (WebElement paymentMethod:paymentMethods)
			{			
				if (paymentMethod.getText().equalsIgnoreCase(paymentMethodName))
				{
					paymentMethodNotAvailable = true;
					//System.out.println("Inside payment Method click " + paymentMethod.getText());
					WebElement selectedPaymentMethod = paymentMethod.findElement(By.tagName("input"));
					selectedPaymentMethod.click();
					if (selectedPaymentMethod.isSelected())
					{
			//			System.out.println("payment method selected is " + paymentMethod.getText());
						Log.info("payment method selected is " + paymentMethod.getText());
					}
					else
					{	
				//		System.out.println("payment method did not get selected " + paymentMethod.getText());
						Log.info("payment method did not get selected " + paymentMethod.getText());
					}
				} 
				
			}
			if (!paymentMethodNotAvailable)
			{
				System.out.println("Payment method - '" + paymentMethodName + "' not available as an option");
				Log.info("Payment method - '" + paymentMethodName + "' not available as an option");
				assertTrue(paymentMethodNotAvailable);
			}
			
		}

		public void selectShippingMethod(String shippingMethodName){
			
			List<WebElement> shippingMethods = Browser.driver().findElements(By.xpath("//div[@class ='shipping_method_name']/label"));
			
			for (WebElement shippingMethod:shippingMethods)
			{
		//		System.out.println("Shipping Method" + shippingMethod.getText());
				Log.info("Shipping Method" + shippingMethod.getText());
				if (shippingMethod.getText().equalsIgnoreCase(shippingMethodName))
				{
					//System.out.println("Insite shipping Method click " + shippingMethod.getText());
					Log.info("Inside shipping Method -" + shippingMethod.getText());
					shippingMethod.click();
				}
				
			}
		}

		public CheckoutStepthree continueNextCheckout() {
			continueCheckoutStepThree.click();
			CheckoutStepthree checkoutstepthree = new CheckoutStepthree();
		    return checkoutstepthree;							
		}
		
		public void authorizedDotNetCreditCardFillDetails()
		{
			Log.debug("Filling up Authorized .NET payment Details");
			authorizeDotNetCardType = Browser.driver().findElement(By.xpath("//input[@value='3A3B380E-2CE1-A5B5-E6D5-6A8735B28219']/parent::div/following-sibling::div[1]/div/div[1]/div/select"));
			Select cardType1 = new Select(authorizeDotNetCardType);
			cardType1.selectByValue(ConfigProperties.getProperty("AUTHNET_CARD_TYPE"));
			authorizeDotNetCardNumber.sendKeys(ConfigProperties.getProperty("AUTHNET_CARD_NO"));
			authorizeDotNetCardVerificationCode.sendKeys(ConfigProperties.getProperty("AUTHNET_CARD_VERI_NO"));
			Select expiratioMonth1 = new Select(authorizeDotNetCardExpirationMonth);
			expiratioMonth1.selectByValue(ConfigProperties.getProperty("AUTHNET_CARD_EXP_MONTH"));
			Select expiratioYear1 = new Select(authorizeDotNetCardExpirationYear);
			System.out.println(ConfigProperties.getProperty("AUTHNET_CARD_EXP_YEAR"));
			//expiratioYear1.selectByValue(ConfigProperties.getProperty("AUTHNET_CARD_EXP_YEAR"));
			expiratioYear1.selectByValue("2018");
		}

		public void stripeCreditCardFillDetails()
		{
			Log.debug("Filling up Stripe Card payment Details");
			Select cardType = new Select(stripeCardType);
			cardType.selectByValue(ConfigProperties.getProperty("STRIPE_CARD_TYPE"));
			WaitTool.waitForElementPresent(Browser.driver(),stripeCardNumber,30);
			stripeCardNumber.sendKeys(ConfigProperties.getProperty("STRIPE_CARD_NO"));
			stripeCardVerificationCode.sendKeys(ConfigProperties.getProperty("STRIPE_CARD_VERI_NO"));
			Select expiratioMonth = new Select(stripeCardExpirationMonth);
			expiratioMonth.selectByValue(ConfigProperties.getProperty("STRIPE_CARD_EXP_MONTH"));
			Select expiratioYear = new Select(stripeCardExpirationYear);
	//		System.out.println(ConfigProperties.getProperty("STRIPE_CARD_EXP_YEAR"));
			//expiratioYear1.selectByValue(ConfigProperties.getProperty("AUTHNET_CARD_EXP_YEAR"));
			expiratioYear.selectByValue("2018");
		}
		@Override
		protected void isLoaded() throws Error {
			// TODO Auto-generated method stub
			Log.debug("Waiting for checkout button to appear so which confirms the page is loaded");
			WebDriverWait wait = new WebDriverWait(Browser.driver(),30);
			continueCheckoutStepThree = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='checkout_buttons']//input[contains(@onclick,'submitStep(2);')]")));	
		}

		@Override
		protected void load() {
			Log.debug("Load kept blank intentionally as page is loaded through application action");
			// TODO Auto-generated method stub
			
		}
	  
	  
}