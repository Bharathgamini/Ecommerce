package testcases;

import org.testng.annotations.Test;

import PageObjects.OptimizedAddingitem_tocart;
import testBase.BaseClass;
import utilities.DataProviders;

public class Addingitem_toCart extends BaseClass{
	
	@Test(dataProvider="SearchData", dataProviderClass=DataProviders.class, 
			priority = 4)
	public void addingtocart(String searchProduct) throws InterruptedException
	{
		//Addingitem_tocart ac = new Addingitem_tocart(driver);
		OptimizedAddingitem_tocart ac = new OptimizedAddingitem_tocart(driver);
		ac.matchingresults();
		ac.selectProduct(searchProduct);
		ac.addingproduct(searchProduct);
		ac.addtocartmodal();
		ac.addtocartcheck();
		
		
	}

}
