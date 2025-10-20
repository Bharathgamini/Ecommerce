package testcases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import PageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class Login_TestCase extends BaseClass{
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, 
			priority = 2)
	public void loginpagetest(String Email, String pass)
	{
	
	
	LoginPage lp = new LoginPage(driver);
	lp.expandericon();
	//lp.signoutclick();
	lp.signinclick();
	lp.enteremail(Email);
	lp.ctnbttn();
	lp.enterpass(pass);
	lp.signinbutton();
	
	}
	@AfterSuite
	public void signoutapplication()
	{
		LoginPage lp = new LoginPage(driver);
		lp.expandericon();
		lp.signoutclick();
	}
	

}
