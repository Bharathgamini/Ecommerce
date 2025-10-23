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

public class OptimizedAddingitem_tocart extends BasePage {

	private String prodcutname; // Stores selected product name globally for later verification

    public OptimizedAddingitem_tocart(WebDriver driver) {
        super(driver);
    }

    // ------------------- WebElements -------------------

    @FindBy(xpath = "//h2[text()='Results']")
    WebElement findingtheresults;

    @FindBy(xpath = "//a[@aria-label='Exit this panel and return to the product page.']")
    WebElement addtocartclose;

    @FindBy(xpath = "//a[@id='nav-cart']")
    WebElement addtocartverify;

    @FindBy(xpath = "//h2[normalize-space()= 'Shopping Cart']")
    WebElement cart;

    @FindBy(xpath = "//span/a[text()='1']")
    WebElement pages;


    // ------------------- Methods -------------------

    // Captures product name and adds the product to cart
    public String addingproduct(String productName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until product title becomes visible
        prodcutname = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='productTitle']"))
        ).getText().trim();

        System.out.println("Captured product: " + prodcutname);

        // (Condition always true, but kept same for logical consistency)
        if (prodcutname.equalsIgnoreCase(prodcutname)) {
            // Get all add-to-cart buttons (there can be multiple)
            List<WebElement> buttons = driver.findElements(By.xpath("//input[@id='add-to-cart-button']"));
            for (WebElement button : buttons) {
                if (button.isDisplayed()) { // Click first visible button
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

    // Verifies if search results are displayed
    public void matchingresults() {
        if (findingtheresults.isDisplayed()) {
            System.out.println("Result found");
        } else {
            System.out.println("No search results displayed");
        }
    }

    // Closes add-to-cart popup modal (if appears)
    public void addtocartmodal() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // Wait until close icon is visible
            wait.until(ExpectedConditions.visibilityOfAllElements(addtocartclose));
            if (addtocartclose.isDisplayed()) {
                addtocartclose.click();
                System.out.println("Closed add-to-cart modal");
            }
        } catch (Exception e) {
            System.out.println("No modal appeared");
        }
    }

    // Verifies product is present in the cart after adding
    public void addtocartcheck() throws InterruptedException {
        Thread.sleep(2000); // Small wait for cart update
        addtocartverify.click(); // Navigate to cart page

        boolean b = cart.isDisplayed();
        System.out.println("Shopping cart page is opened" + b);

        // Fetch list of items inside the cart
        List<WebElement> listofitems =
                driver.findElements(By.xpath("//span[contains(@class, 'a-truncate')]"));

        System.out.println(listofitems.size());
        boolean found = false;

        for (WebElement item : listofitems) {
            String cartitem = item.getText().trim();
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

    // Selects the cheapest product from all pages for the given product name
    public void selectProduct(String productName) {
        String parentWindow = driver.getWindowHandle();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        int currentPage = 1;
        int globalMinPrice = Integer.MAX_VALUE; // To store globally lowest price
        String globalCheapestProduct = null;    // To store globally cheapest product name
        int globalCheapestPage = 1;             // To store page number where cheapest product found

        // Loop through all result pages
        while (true) {
            System.out.println("Processing page " + currentPage + "...");

            // Find all product links containing product name
            List<WebElement> products = driver.findElements(
                    By.xpath("//a[.//span[contains(text(), '" + productName + "')]]"));

            int pageMinPrice = Integer.MAX_VALUE;  // Lowest price for current page
            String pageCheapestProduct = null;     // Product name with lowest price on current page

            for (WebElement product : products) {
                try {
                    // Get product price element
                    WebElement priceElement = product.findElement(By.xpath("//span[@class='a-price-whole']"));
                    String value = priceElement.getText().replaceAll("[^0-9]", "");
                    int price = Integer.parseInt(value);
                    String name = product.getText().trim();

                    // Compare price to find lowest in current page
                    if (price < pageMinPrice) {
                        pageMinPrice = price;
                        System.out.println(pageMinPrice);
                        pageCheapestProduct = name;
                    }

                } catch (Exception e) {
                    System.out.println("Skipping product due to missing price: " + e.getMessage());
                }
            }

            // Compare current page lowest price with global lowest
            if (pageCheapestProduct != null) {
                System.out.println("Page " + currentPage + " cheapest price: " + pageMinPrice);
                if (pageMinPrice < globalMinPrice) {
                    globalMinPrice = pageMinPrice;
                    globalCheapestProduct = pageCheapestProduct;
                    globalCheapestPage = currentPage;
                }
            }

            // Try moving to next page
            try {
                WebElement nextPage = driver.findElement(
                        By.xpath("//span[@class='a-list-item']/a[contains(@aria-label, 'next')]"));
                nextPage.click();
                //wait.until(ExpectedConditions.stalenessOf(products.get(0)));
                currentPage++;
            } catch (Exception e) {
                System.out.println("No more pages found, ending loop.");
                break;
            }
        }

        // After checking all pages, navigate to the page having cheapest product
        System.out.println("Cheapest product found on page " + globalCheapestPage + " with price " + globalMinPrice);

        try {
            WebElement targetPage = driver.findElement(
                    By.xpath("//a[contains(@aria-label,'Go to page " + globalCheapestPage + "')]"));
            targetPage.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[.//span[contains(text(), '" + productName + "')]]")));
        } catch (Exception e) {
            System.out.println("Could not navigate directly to page " + globalCheapestPage + ": " + e.getMessage());
        }

        // Click the cheapest product
        try {
            WebElement productToClick = driver.findElement(
                    By.xpath("//a[.//span[contains(text(), '" + globalCheapestProduct + "')]]"));
            wait.until(ExpectedConditions.elementToBeClickable(productToClick));
            productToClick.click();

            // Switch to the new window if opened
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            Set<String> allWindows = driver.getWindowHandles();
            for (String window : allWindows) {
                if (!window.equals(parentWindow)) {
                    driver.switchTo().window(window);
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("Failed to click cheapest product: " + e.getMessage());
        }
    }
}


/*  ------------------- LOGIC EXPLANATION HIGHLIGHTS -------------------

1️⃣ while(true):
   - Used because the total number of result pages is unknown.
   - The loop continues until no “Next” page button is found, then breaks.

2️⃣ globalMinPrice & pageMinPrice:
   - pageMinPrice → tracks the cheapest price within a single page.
   - globalMinPrice → compares prices across all pages to find the overall cheapest product.

3️⃣ Why comparing prices:
   - For every product found on a page, price text is extracted.
   - Only numeric characters are kept using regex [^0-9].
   - Parse it into integer and compare to find the minimum.

4️⃣ globalCheapestProduct & globalCheapestPage:
   - Store the product name and page number where the lowest price was found.
   - Used later to revisit that specific page after scanning all pages.

5️⃣ ExpectedConditions.stalenessOf (commented):
   - Useful to wait until the previous page’s DOM is refreshed before proceeding.
   - Prevents StaleElementReferenceException when moving between pages.

6️⃣ The period (.) in XPath:
   - When used like `.//span[@class='a-price-whole']`, it searches relative to the current WebElement.
   - Without `.`, Selenium searches the entire DOM again, which may cause wrong matches or duplicates.

7️⃣ Switching Windows:
   - After clicking a product, Amazon often opens it in a new tab.
   - numberOfWindowsToBe(2) waits until the new tab opens.
   - Then the driver switches focus to that new tab.

8️⃣ addtocartcheck():
   - Opens the cart and matches the previously stored product name (prodcutname) with cart items.
   - Ensures product addition was successful.

9️⃣ addtocartmodal():
   - Handles the post-cart modal that appears after adding an item.
   - Closes it safely without interrupting execution.

--------------------------------------------------------------- */