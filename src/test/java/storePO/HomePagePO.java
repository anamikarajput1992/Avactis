package storePO;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import model.RegistrationData;
import utility.ConfigProperties;

public class HomePagePO extends LoadableComponent<HomePagePO>
{
	
	private WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//a[text()='Sign In']")
	private WebElement signin;
	
	@FindBy(xpath = "//button[@class='btn btn-default']")
	private WebElement register;
	
	@FindBy(name = "customer_info[Customer][Email]")
	private WebElement email;
	
	@FindBy(name = "customer_info[Customer][Password]")
	private static WebElement password;
	
	@FindBy(name = "customer_info[Customer][RePassword]")
	private static WebElement rePassword;
	
	@FindBy(name = "customer_info[Customer][FirstName]")
	private static WebElement firstName;
	
	@FindBy(name = "customer_info[Customer][LastName]")
	private WebElement lastName;
	
	@FindBy(id = "customer_info_Customer_Country")
	private WebElement country;
	
	@FindBy(id = "customer_info_Customer_State")
	private WebElement state;
	
	@FindBy(name = "customer_info[Customer][ZipCode]")
	private WebElement Zip;
	
	@FindBy(name = "customer_info[Customer][City]")
	private WebElement city;
	
	@FindBy(name = "customer_info[Customer][Streetline1]")
	private WebElement addressLine1;
	
	@FindBy(name = "customer_info[Customer][Streetline2]")
	private WebElement addressLine2;
	
	@FindBy(name = "customer_info[Customer][Phone]")
	private WebElement contactPhone;
	
	@FindBy(xpath = "//input[@value='Register']")
	private WebElement submit;
	
	@FindBy(xpath = "//a[text()='Sign Out']")
	private WebElement SignOut;
	
	public HomePagePO()
	{
	System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
	driver = new ChromeDriver();
	PageFactory.initElements(driver, this);	
	wait = new WebDriverWait(driver,30,250);
	}
	
	public HomePagePO Register() throws InterruptedException
	{
		signin.click();
		register.click();
		RegistrationData registration = new RegistrationData();
		registration.setEmialId(ConfigProperties.getProperty("REG_EMAIL"));
		registration.setPassword(ConfigProperties.getProperty("REG_PASSWORD"));
		registration.setRetypePassword(ConfigProperties.getProperty("REG_RETYPEPASSWORD"));
		registration.setFirstName(ConfigProperties.getProperty("REG_FIRSTNAME"));
		registration.setLastName(ConfigProperties.getProperty("REG_LASTNAME"));
		registration.setCountry(ConfigProperties.getProperty("REG_COUNTRY"));
		registration.setState(ConfigProperties.getProperty("REG_STATE"));
		registration.setZip(ConfigProperties.getProperty("REG_ZIP"));
		registration.setCity(ConfigProperties.getProperty("REG_CITY"));
		registration.setAddressLine1(ConfigProperties.getProperty("REG_ADDRESSLINE1"));
		registration.setAddressLine2(ConfigProperties.getProperty("REG_ADDRESSLINE2"));
		registration.setContactPhone(ConfigProperties.getProperty("REG_CONTACTPHONE"));
		if(fillRegistrationDetails(registration))
		{
			assertTrue(true,"Registration is successfull");
		}
		else
		{
			fail("Registration fail,Probabably data provided is already present");
		}
		return this;
	}
	
	public Boolean fillRegistrationDetails(RegistrationData registration) throws InterruptedException
	{
		email.sendKeys(registration.getEmialId());
		password.sendKeys(registration.getPassword());
		rePassword.sendKeys(registration.getRetypePassword());
		firstName.sendKeys(registration.getFirstName());
		lastName.sendKeys(registration.getLastName());
		country.sendKeys(registration.getCountry());
		state.sendKeys(registration.getState());
		Zip.sendKeys(registration.getZip());
		city.sendKeys(registration.getCity());
		addressLine1.sendKeys(registration.getAddressLine1());
		addressLine2.sendKeys(registration.getAddressLine2());
		contactPhone.sendKeys(registration.getContactPhone());
		submit.click();
		Thread.sleep(2000);
		return true;
		
	}
	
	public void Signout()
	{
		
		SignOut.click();
		
	}
	
	public void browserClose()
	{
		driver.close();
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
