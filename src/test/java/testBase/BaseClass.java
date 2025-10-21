package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
	public static WebDriver driver;
	public Properties p;
	@BeforeSuite()
	//@Parameters({"browser"})
	public void setup() throws IOException
	{
		
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p= new Properties();
		p.load(file);
		
		// TODO Auto-generated method stub
		
		//String ch = null;
		String browsers = p.getProperty("browser");
		switch(browsers)
		{
		case "Chrome":
			System.out.println("ChromeDriver");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			options.addArguments("--disable-webauthn");
			options.addArguments("--disable-features=WebAuthentication");
			options.addArguments("--disable-credential-storage");
			options.addArguments("--disable-autofill-keyboard-accessory-view");
			options.addArguments("--disable-save-password-bubble");
			driver = new ChromeDriver(options);
			break;
		case "Safari":
			System.out.println("Safari Driver");
			break;
		case "Mozilla":
			System.out.println("MozillaDriver");
			break;
		default:
			System.out.println("Invaild Driver");
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();


	}
	@AfterSuite
	public void close()
	{
		driver.quit();
	}

}
