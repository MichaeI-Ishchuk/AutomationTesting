package home4;



import home4.model.ProductData;
import home4.utils.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions {
    private WebDriver driver;
    private WebDriverWait wait;
   private ProductData newProduct;

    private By pass =  By.cssSelector("#passwd");
    private  By loginId = By.cssSelector("#email");
    private  By sub = By.cssSelector("#login_form [name=submitLogin]");
    private By main =  By.cssSelector(".page-head");
    private By mainProduct =  By.id("main-div");
    private  By catalog = By.cssSelector("#subtab-AdminCatalog");
    private  By products = By.cssSelector("#subtab-AdminProducts");
    private By addProduct =  By.id("page-header-desc-configuration-add");
    private  By adminProducts = By.cssSelector(".adminproducts");
    private By nameProduct =  By.id("form_step1_name_1");
    private By form =  By.cssSelector(".nav-item");
    private By quantity =  By.id("form_step3_qty_0");
    private By price =  By.id("form_step2_price_ttc");
    private By switchInput =  By.cssSelector(".switch-input");
    private By growl =  By.cssSelector(".growl-message");
    private By save =  By.cssSelector(".btn.btn-primary.js-btn-save");
    private By allProducts =  By.cssSelector(".pull-md-right.h4");
    private By allProducts2 =  By.cssSelector(".h3.product-title");
    private By productPage =  By.id("product");




    public GeneralActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);

    }

    /**
     * Logs in to Admin Panel.
     * @param login
     * @param password
     */

    public void login(String login, String password) {

       driver.navigate().to(Properties.getBaseAdminUrl());
        WebElement loginId = driver.findElement(this.loginId);
        WebElement pass = driver.findElement(this.pass);
        loginId.sendKeys(login);
        pass.sendKeys(password);

        WebElement submit = driver.findElement(this.sub);
        submit.click();

        waitForContentLoad(this.main);

    }

    public void createProduct(ProductData newProduct) {

        this.newProduct=newProduct;
        waitForContentLoad(this.catalog);
        WebElement catalog= driver.findElement(this.catalog);
        WebElement products = driver.findElement(this.products);
        Actions action = new Actions(driver);
        action.moveToElement(catalog).moveToElement(products).click().build().perform();

        waitForContentLoad(mainProduct);

        WebElement addProduct = driver.findElement(this.addProduct);
        addProduct.click();
        waitForContentLoad(adminProducts);
        WebElement nameProduct = driver.findElement(this.nameProduct);
        nameProduct.click();
        nameProduct.sendKeys(newProduct.getName());

        List<WebElement> elementList = driver.findElements(this.form);

        System.out.println(elementList.get(4).toString());

        elementList.get(4).click();
        WebElement quantity = driver.findElement(this.quantity);
        WebElement price = driver.findElement(this.price);
        quantity.click();
        quantity.sendKeys(newProduct.getQty().toString());

        elementList.get(6).click();
        price.click();
        price.sendKeys(newProduct.getPrice());

        WebElement switchInput = driver.findElement(this.switchInput);
        switchInput.click();
        waitForContentLoad(growl);
        WebElement saveProduct = driver.findElement(this.save);
        saveProduct.click();


    }

    public void checkProduct() {

        driver.navigate().to(Properties.getBaseUrl());

        WebElement allProducts = driver.findElement(this.allProducts);

        JavascriptExecutor js= (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();"
                ,allProducts);

        allProducts.click();

        List<WebElement> productlist = driver.findElements(this.allProducts2);

        productlist.get(productlist.size()-1).click();
        waitForContentLoad(productPage);


        Assert.assertTrue(driver.findElement(By.cssSelector("h1.h1")).getText().contains(newProduct.getName().toUpperCase()), "Product added successfull");
        Assert.assertTrue(driver.findElement(By.cssSelector(".product-quantities")).getText().contains(newProduct.getQty().toString()), "Quantity is corrected");
        Assert.assertTrue(driver.findElement(By.cssSelector(".current-price")).getText().contains(newProduct.getPrice()), "Price is corrected");
    }

    /**
     * Waits until page loader disappears from the page
     */
    public void waitForContentLoad(By locator) {

        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
