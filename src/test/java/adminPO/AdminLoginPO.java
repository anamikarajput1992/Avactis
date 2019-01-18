package adminPO;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.ConfigProperties;

public class AdminLoginPO extends LoadableComponent<AdminLoginPO>
{
	WebDriver driver;
	WebDriverWait wait;
	@FindBy(xpath = "//input[@name='AdminEmail' and @type = 'text']")
	private WebElement adminEmail;
	
	@FindBy(xpath = "//input[@name='Password' and @type = 'password']")
	private WebElement adminPassword;
	
	@FindBy(xpath = "//input[@name='RememberEmail' and @type = 'checkbox']")
	private WebElement rememberMe;
	
	@FindBy(xpath = "//button[@type = 'submit' and @class = 'btn blue pull-right' ]")
	private WebElement adminSignin;
	
	
	public AdminLoginPO()
	{
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		PageFactory.initElements(driver, this);	
		wait = new WebDriverWait(driver,30,250);
		
	}
	
	public void adminSignin()
	{
		ConfigProperties.loadPropties();
		System.out.println("in the admin signin page");
		adminEmail.sendKeys(ConfigProperties.getProperty("ADMIN_USERNAME"));
		adminPassword.sendKeys(ConfigProperties.getProperty("ADMIN_PASSWORD"));
		if(!rememberMe.isSelected())
		rememberMe.click();
		adminSignin.click();
	}
	
	public boolean loginVerification()
	{
		assertEquals(driver.getTitle(),ConfigProperties.getProperty("ADMIN_LOGINPAGE_TITLE"),"Login is not successfull");
		return false;
	}
	
	public void closeBrowser()
	{
		driver.close();
	}
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		ConfigProperties.loadPropties();
		driver.get(ConfigProperties.getProperty("ADMIN_URL"));	
	}

	@Override
	protected void isLoaded() throws Error
	{
		// TODO Auto-generated method stub
		assertEquals(driver.getTitle(),ConfigProperties.getProperty("ADMIN_HOMEPAGE_TITLE"),"Admin page not loaded successfully");
		
	}

}
