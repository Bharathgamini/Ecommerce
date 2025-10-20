package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Searching extends BasePage{
	
	public Searching(WebDriver driver)
	{
		super(driver);
	}


	@FindBy(xpath = "//input[@id='twotabsearchtextbox']")
	WebElement productsearch;
	
	@FindBy(xpath="//input[@id='nav-search-submit-button']")
	WebElement searchbuttonclick;
	
	
	public void searchtext(String searchword)
	{
		productsearch.sendKeys(searchword);
	}
	
	public void searchbutton()
	{
		searchbuttonclick.click();
	}

}
