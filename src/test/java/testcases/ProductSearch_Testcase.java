package testcases;

import org.testng.annotations.Test;

import PageObjects.Searching;
import testBase.BaseClass;
import utilities.DataProviders;

public class ProductSearch_Testcase extends BaseClass{
	
	@Test(dataProvider="SearchData", dataProviderClass=DataProviders.class, 
			priority = 3)
	public void productsearch(String searchdata)
	{
		Searching s = new Searching(driver);
		s.searchtext(searchdata);
		s.searchbutton();
	}

}
