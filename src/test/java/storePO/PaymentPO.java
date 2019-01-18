package storePO;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.ConfigProperties;

public class PaymentPO extends  LoadableComponent<PaymentPO>
{
	WebDriver driver;
	WebDriverWait wait;
	PurchasePO mypurchase;

	@FindBy(xpath = "//a[text()='Checkout']")
	private WebElement checkoutButton;
	
	@FindBy(xpath ="//div[@class='shipping_same_as_billing checkbox']")
	private WebElement billingSameAsShipping;
	
	@FindBy(xpath ="//input[@class='en btn btn-primary button_continue_checkout']")
	private WebElement continueCheckout;
	
	@FindBy(name = "paymentModule[method_code]")
	private WebElement billingOptions;
	
	@FindBy(xpath = "//label[text()=' Ground Shipping']")
	private WebElement shippingOptions;
	
	@FindBy(xpath ="//span[@class='header_wel']")
	private WebElement userNameMsg;
	
	
    public PaymentPO()
	{
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		PageFactory.initElements(driver, this);	
		get();
		wait = new WebDriverWait(driver,30,250);
		
	}
	
	public PaymentPO(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	}
	
	public void checkOutProducts()
	{
	    	System.out.println("in the string comparision");
	    	checkoutButton.click();
	    	if(!billingSameAsShipping.isSelected())
			billingSameAsShipping.click();
	    	continueCheckout.click();
	    	if(!billingOptions.isSelected())
				billingOptions.click();
			if(!shippingOptions.isSelected())
				shippingOptions.click();
			    checkoutButton.click();
	}
	@Override
	protected void load()
	{
		// TODO Auto-generated method stub
		ConfigProperties.loadPropties();
		driver.get(ConfigProperties.getProperty("URL"));
		
	}

	@Override
	protected void isLoaded() throws Error 
	{
		// TODO Auto-generated method stub
		assertEquals(driver.getTitle(),ConfigProperties.getProperty("HOMEPAGE_TITLE"),"Page Not Loaded.");
		
	}
	
}
