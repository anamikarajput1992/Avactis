package adminPO;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class AdminTest 
{
  AdminLoginPO admin = new AdminLoginPO();
  
  @Test
  public void adminLogin() 
  {
	  admin.adminSignin();
/* if(admin.loginVerification());
	  {
		  System.out.println("Login successfull");
	  }*/
  }
  @BeforeMethod
  public void beforeMethod()
  {
	  AdminLoginPO admin = new AdminLoginPO();
	  admin.get();
  }

  @AfterMethod
  public void afterMethod() 
  {
	 //admin.closeBrowser();
  }

}
