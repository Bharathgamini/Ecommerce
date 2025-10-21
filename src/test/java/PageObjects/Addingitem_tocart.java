package PageObjects;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Addingitem_tocart extends BasePage {

    private String prodcutname; // Class-level variable

    public Addingitem_tocart(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h2[text()='Results']")
    WebElement findingtheresults;

    @FindBy(xpath = "//a[@aria-label='Exit this panel and return to the product page.']")
    WebElement addtocartclose;

    @FindBy(xpath = "//a[@id='nav-cart']")
    WebElement addtocartverify;
    
    @FindBy(xpath="//h2[normalize-space()= 'Shopping Cart']")
    WebElement cart;

    // Selecting the product from search results
    public void selectProduct(String productName) {
        String parentwindow = driver.getWindowHandle();

        // Find the product dynamically
        WebElement product = driver.findElement(
                By.xpath("//a[.//span[contains(text(), '"+productName+"')]][1]")
        );

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(product));
        System.out.println("Clicking the product element...");
        product.click();

        // Wait for new window/tab and switch
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(parentwindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    // Capture product name and add to cart
    public String addingproduct(String productName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        prodcutname = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='productTitle']"))
        ).getText().trim();

        System.out.println("Captured product: " + prodcutname);

        if (prodcutname.equalsIgnoreCase(prodcutname)) {
            List<WebElement> buttons = driver.findElements(By.xpath("//input[@id='add-to-cart-button']"));
            for (WebElement button : buttons) {
                if (button.isDisplayed()) {
                    button.click();
                    System.out.println("Clicked Add to Cart button for: " + prodcutname);
                    break;
                }
            }
        } else {
            System.out.println("Expected product does not match actual product.");
            
        }

        return prodcutname;
    }

    // Check if search results are displayed
    public void matchingresults() {
        if (findingtheresults.isDisplayed()) {
            System.out.println("Result found");
        } else {
            System.out.println("No search results displayed");
        }
    }

    // Close add-to-cart modal if visible
    public void addtocartmodal() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(
                ExpectedConditions.visibilityOfAllElements(addtocartclose));
        try {
            if (addtocartclose.isDisplayed()) {
                addtocartclose.click();
                System.out.println("Closed add-to-cart modal");
            }
        } catch (Exception e) {
            System.out.println("No modal appeared");
        }
    }

    // Verify product is in the cart
    public void addtocartcheck() throws InterruptedException {
        Thread.sleep(2000);
    	addtocartverify.click();
    	boolean b = cart.isDisplayed();
    	System.out.println("Shopping cart page is opened" + b);
    	if(b)
    	{
 //       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        List<WebElement> listofitems = wait.until(
//                ExpectedConditions.visibilityOfAllElementsLocatedBy(
//                        By.xpath("//span[contains(@class, 'a-truncate') and contains(@class,'a-truncate-full a-offscreen')]")
//                )
//        );
        
        List<WebElement> listofitems = 
                        driver.findElements(By.xpath("//span[contains(@class, 'a-truncate')]"));
    	System.out.println(listofitems.size());
        boolean found = false;
        for (WebElement item : listofitems) {
            String cartitem = item.getText().trim();
            
           //System.out.println(prodcutname);
            System.out.println(cartitem);
            if (cartitem.equalsIgnoreCase(prodcutname)) {
                System.out.println("Product has been added to the cart: " + cartitem);
                found = true;
                break;
            }
        }
    	
        if (!found) {
            System.out.println("Product not found in the cart!");
        }
    }
}
}
