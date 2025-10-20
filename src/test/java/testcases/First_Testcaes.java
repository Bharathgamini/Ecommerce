package testcases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class First_Testcaes {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		ChromeDriver driver = new ChromeDriver(options);
		
		
		driver.get("https://selectorshub.com/xpath-practice-page/");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
//		By locater = By.xpath("//div[@id='tablepress-1_wrapper']//tbody//tr[td[contains(text(), 'windows')] and td[contains(text(), 'chrome')] and td[contains(text(), 'Samsun')] and td[contains(text(), 'India')]]");
//		
//		
//		 List<WebElement> list= wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locater));
//		
//		 int size = list.size();
//		 
//		 System.out.println(size);
//		// Thread.sleep(5000);
//
//		// WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(
//				  //  By.xpath("//tr[@class='row-2']//input[@type='checkbox']")));
//		 
//		 
//		 driver.findElements(By.xpath("//div[@id='tablepress-1_wrapper']//tbody//tr/td/input[@type='checkbox']"))
//	      .forEach(checkbox -> ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox));
//
//		 //((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
//
//		// ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", checkbox);
//		// checkbox.click();
//		 
//		// checkbox.click();
//		 
//		 //System.out.print(b);
//		 //driver.findElements(By.xpath("//div[@id='tablepress-1_wrapper']//tbody//tr/td/input[@type= 'checkbox']")).forEach(WebElement::click);
//		 
//		 Thread.sleep(5000);
//		
		
		By locator = By.xpath("//div[@id='tablepress-1_wrapper']//tbody//tr[td[contains(text(), 'windows')] and td[contains(text(), 'chrome')] and td[contains(text(), 'Samsun')] and not(td[contains(text(), 'United States')])]");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		List<WebElement> rows = driver.findElements(locator);
		
		for(WebElement element: rows)
		{
			WebElement checkbox = element.findElement(By.xpath(".//td/input[@type='checkbox']"));
	        js.executeScript("arguments[0].click();", checkbox);
		}
//				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator))    
//		.forEach(row -> {
//		        WebElement checkbox = row.findElement(By.xpath(".//td/input[@type='checkbox']"));
//		        js.executeScript("arguments[0].click();", checkbox);
//		    });

		Thread.sleep(5000);
		
		driver.close();
		
		
		

	}

}
