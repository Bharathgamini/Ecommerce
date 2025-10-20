package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderBar extends BasePage{
	
	
	public HeaderBar(WebDriver driver)
	{
		super(driver);
	
	}

	
	@FindBy(xpath="//a[@id='nav-logo-sprites']")
	WebElement logo;
	
	
	
	public boolean logo()
	{
	boolean b =	logo.isDisplayed();
	return b;
	}

}
