package testcases;

import org.testng.annotations.Test;

import PageObjects.HeaderBar;
import testBase.BaseClass;

public class Amazonlaunching extends BaseClass{
	
	@Test(priority=1)
	public void test()
	{
		HeaderBar hb = new HeaderBar(driver);
		//hb.logo();
		System.out.println(hb.logo());
		
		
		
	}

}
