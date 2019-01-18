package storePO;

import org.testng.annotations.Test;

import utility.ExcelReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class HomePageTest 
{
	HomePagePO homepage; 
	PurchasePO purchase;
 
  //@Test
  public void RegisterPage() throws InterruptedException
  {
	  homepage.Register()
	  	      .Signout();    
  }
  
  @Test(dataProvider="dataProviderMethod" , dataProviderClass = ExcelReader.class)
  public void aapurchaseTest(String category,String subcategory,String productName)
  {
	  purchase.SelectProduct(category, subcategory, productName)
	          .checkOutProducts();
  }
  
 @BeforeClass
  public void BeforeClass()
  {
	 homepage = new HomePagePO();
	 homepage.get();
	 purchase = new PurchasePO();
	  	  
  }

  @AfterClass
  public void AfterClass() 
  {
	// homepage.browserClose();
	 
  }

}
