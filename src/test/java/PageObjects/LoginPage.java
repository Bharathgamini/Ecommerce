package PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{
	
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//button[@aria-label=\"Expand Account and Lists\"]")
	WebElement expander;
	
	@FindBy(xpath="//li//a[@id='nav-item-signout']")
	WebElement signout;
	
	@FindBy(xpath="//a[contains(@class, 'nav-action-signin-button')]")
	WebElement signin;
	
	
	@FindBy(xpath="//input[@id='ap_email_login']")
	WebElement enterEmail;
	
	
	@FindBy(xpath="//input[@id='ap_password']")
	WebElement enterpassword;
	
	@FindBy(xpath="//input[@class='a-button-input']")
	WebElement Continue;
	
	@FindBy(xpath = "//input[@id='signInSubmit']")
	WebElement signinbtn;
	
	// need to handle 
	
	public void expandericon()
	{
		expander.click();
	}
	public void signoutclick()
	{
		
		boolean b = signout.isDisplayed();
		if(b)
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfAllElements(signout));
			signout.click();
		}
	}
	public void signinclick()
	{
		boolean c = signin.isDisplayed();
		if(c)
		{
			signin.click();
		}
	}
	public void enteremail(String email)
	{
		enterEmail.sendKeys(email);
	}
	public void enterpass(String pass)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(enterpassword));
		
		enterpassword.sendKeys(pass);
	}
	
	public void ctnbttn()
	{
		Continue.click();
	}
	
	public void signinbutton()
	{
		signinbtn.click();
	}
	
	
}
