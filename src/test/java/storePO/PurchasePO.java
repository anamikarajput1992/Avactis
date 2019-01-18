package storePO;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.ConfigProperties;

public class PurchasePO extends LoadableComponent<PurchasePO>
{
	private WebDriver driver;
	WebDriverWait wait;
	
	String CommonPath = "//li[@class='dropdown dropdown-megamenu']//a[text()='";
	String EndPart = "']";
	String productPath="//div[@class='product_name']//h3[text()='";
	String CommonPathHeader="//div[@class='header-navigation']//a[text()='";

	@FindBy(xpath = "//input[@class='en btn btn-primary button_add_to_cart input_submit']")
	private WebElement addtoCart;
	
	@FindBy(xpath = "//a[text()='Sign In']")
	private WebElement signin;
	
	@FindBy(id = "account_sign_in_form_email_id")
	private WebElement emailId;
	
	@FindBy(id = "account_sign_in_form_passwd_id")
	private WebElement passwordId;
	
	@FindBy(xpath = "//input[@name='remember_me']")
	private WebElement CheckboxTick;
	
	@FindBy(xpath = "//input[@class ='btn btn-primary input_submit']")
	private WebElement SignInSubmit;
	
	
	
	public PurchasePO()
	{
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		PageFactory.initElements(driver, this);	
		get();
		wait = new WebDriverWait(driver,30,250);
		
	}
	
	public PurchasePO(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		//isLoaded();	
	}
	
	public PurchasePO SignInMethod()
	{
		signin.click();
		emailId.sendKeys(ConfigProperties.getProperty("REG_EMAIL"));
		passwordId.sendKeys(ConfigProperties.getProperty("REG_PASSWORD"));
		if(!CheckboxTick.isSelected())
			CheckboxTick.click();
		SignInSubmit.click();
		return this;
	}
	
	public PaymentPO SelectProduct(String category,String subcategory,String productName)
	{
			if(category.equalsIgnoreCase("Computers") || category.equalsIgnoreCase("DVD"))
			{
				if(driver.getCurrentUrl().equalsIgnoreCase(ConfigProperties.getProperty("URL")))
				{
					SignInMethod();
					commonProductWithSubcategoty(category,subcategory,productName);
				}
				else
				{
					commonProductWithSubcategoty(category,subcategory,productName);
				}
			}
			else
			{
				if(category.equalsIgnoreCase("Apparel") || category.equalsIgnoreCase("Furniture") || category.equalsIgnoreCase("Sport") || category.equalsIgnoreCase("Digital Distribution") && subcategory == null)
				{
					if(driver.getCurrentUrl().equalsIgnoreCase(ConfigProperties.getProperty("URL")))
						{
						 	SignInMethod();
						 	commonProductWithoutSubcategoty(category,productName);
						}
					else
						{
							commonProductWithoutSubcategoty(category,productName);
						}
				}
			 }
			return new PaymentPO(driver);
		}
	
	public void commonProductWithSubcategoty(String category,String subcategory,String productName)
	{
		WebElement CategoryComp=driver.findElement(By.xpath(CommonPath+category+EndPart));
		Actions builder=new Actions(driver);
		builder.moveToElement(CategoryComp).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CommonPath+subcategory+EndPart))).click();
		System.out.println(CommonPath+subcategory+EndPart);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(productPath+productName+EndPart))).click();
		System.out.println(productPath+productName+EndPart);
		addtoCart.click();
		
		
	}
	
	public void commonProductWithoutSubcategoty(String category,String productName)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CommonPathHeader+category+EndPart))).click();
		System.out.println(CommonPathHeader+category+EndPart);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(productPath+productName+EndPart))).click();
		System.out.println(productPath+productName+EndPart);
		addtoCart.click();
		
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
		assertEquals(driver.getTitle(),ConfigProperties.getProperty("HOMEPAGE_TITLE"),"Page Not Loaded.");
		// TODO Auto-generated method stub
		
	}

}
