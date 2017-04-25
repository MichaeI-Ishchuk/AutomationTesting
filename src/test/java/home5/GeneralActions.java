package home5;


import home4.utils.Properties;
import home4.utils.logging.EventHandler;
import home5.model.ProductData;
import home5.utils.DataConverter;
import home5.utils.logging.CustomReporter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.Random;


/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions {
    private WebDriver driver;
    private WebDriverWait wait;
    private EventFiringWebDriver driver2;
    public ProductData product;
    private int ran;

    private  int qty;


    private By allProducts =  By.cssSelector(".pull-md-right.h4");
    private By allProducts2 =  By.cssSelector(".h3.product-title");
    private By productPage =  By.id("product");
    private By addCart =  By.cssSelector(".add-to-cart");
    private By gotoCart =  By.cssSelector("a.btn.btn-primary");
    private By makeOrder =  By.cssSelector("a.btn.btn-primary");

    public int getQty() {
        return qty;
    }

    private By firstName =  By.cssSelector("input[name=firstname]");
    private By lastName =  By.cssSelector("input[name=lastname]");
    private By email =  By.cssSelector("input[name=email]");
    private By continue1 =By.cssSelector("button[name=continue]");

    private By address1 =  By.cssSelector("input[name=address1]");
    private By postcode =  By.cssSelector("input[name=postcode]");
    private By city =  By.cssSelector("input[name=city]");
    private By continue2 =By.cssSelector("button[name=confirm-addresses]");
    private By continue3 =By.cssSelector("button[name=confirmDeliveryOption]");

    private By payment =  By.className("ps-shown-by-js");
    private By messageConfirm =By.cssSelector("h3.h1.card-title");

    private By aboutProduct= By.xpath("//a[text()=\"Подробнее о товаре\"]");
    private By qtyProduct =By.cssSelector(".product-quantities span");


    public GeneralActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
        driver2 = new EventFiringWebDriver(driver);
        driver2.register(new EventHandler());
    }

    public void openRandomProduct() {

        Random random = new Random();
        List<WebElement> productlist = driver.findElements(this.allProducts2);
        ran = random.nextInt(productlist.size()-1);
        productlist.get(ran).click();
        waitForContentLoad(productPage);
   }


    public void fieldsHandler()
    {
        waitForContentLoad(firstName);
        WebElement firstName= driver.findElement(this.firstName);
        WebElement lastName= driver.findElement(this.lastName);
        WebElement email= driver.findElement(this.email);
       List<WebElement> continue1 = driver.findElements(this.continue1);

        firstName.sendKeys("Vasy");
        lastName.sendKeys("Pupkin");
        email.sendKeys("vasy@com.ua");
        continue1.get(0).click();

        waitForContentLoad(address1);
        WebElement address1= driver.findElement(this.address1);
        WebElement zip= driver.findElement(this.postcode);
        WebElement city= driver.findElement(this.city);
        WebElement continue2= driver.findElement(this.continue2);
        address1.sendKeys("Turgenyvskiy st.");
        zip.sendKeys("12345");
        city.sendKeys("Kiyv");
        continue2.click();

        waitForContentLoad(continue3);
        WebElement continue3= driver.findElement(this.continue3);
        continue3.click();

        waitForContentLoad(payment);
        List<WebElement> paylist = driver.findElements(this.payment);
        paylist.get(0).click();
        paylist.get(2).click();
        paylist.get(3).click();

    }

    public void checkSiteVersion()
    {

        driver.navigate().to(Properties.getBaseUrl());
       if (DataConverter.parseStockValue(driver.manage().window().getSize().toString())<450)
        {
            CustomReporter.logAction("Mobile Version");
        }else CustomReporter.logAction("Desktop Version");

    }

    public void createOrder()
    {

        WebElement allProducts = driver.findElement(this.allProducts);

        JavascriptExecutor js= (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();"
                ,allProducts);
       allProducts.click();

       openRandomProduct();

       product=getOpenedProductInfo();

        qty=getQtyProduct();
        System.out.println("ok");
        waitForContentLoad(this.addCart);
        WebElement addCart = driver.findElement(this.addCart);
        addCart.click();
        waitForContentLoad(gotoCart);
        WebElement gotoCart = driver.findElement(this.gotoCart);
        gotoCart.click();
        waitForContentLoad(makeOrder);

///////////////// check product
        List<WebElement> elementList = driver.findElements(By.cssSelector(".product-line-info"));
        Assert.assertTrue(elementList.get(0).getText().toUpperCase().equals(product.getName()), "Title is corrected");
                CustomReporter.logAction("Title is corrected");
        float pr = DataConverter.parsePriceValue2(elementList.get(1).getText());
        Assert.assertTrue(pr==product.getPrice(), "Price is corrected");
        System.out.println(pr);
        CustomReporter.logAction("Price is corrected");
        int i =Integer.parseInt(driver.findElement(By.cssSelector(".js-cart-line-product-quantity.form-control")).getAttribute("value"));
        Assert.assertTrue(i==product.getQty(), "Qty is corrected");
          CustomReporter.logAction("Qty is corrected");

        WebElement makeOrder =driver.findElement(this.makeOrder);
        makeOrder.click();

        fieldsHandler();
        waitForContentLoad(messageConfirm);
    }


   public void checkValueInStock()

   {
       driver.navigate().to(Properties.getBaseUrl());
       WebElement allProducts = driver.findElement(this.allProducts);

       JavascriptExecutor js= (JavascriptExecutor)driver;
       js.executeScript("arguments[0].scrollIntoView();"
               ,allProducts);
       allProducts.click();

       List<WebElement> productlist = driver.findElements(this.allProducts2);
       productlist.get(ran).click();
       waitForContentLoad(productPage);
   }


    public int getQtyProduct()
    {
        WebElement aboutProduct = driver.findElement(this.aboutProduct);
        aboutProduct.click();
        waitForContentLoad(this.qtyProduct);
        WebElement qtyProduct = driver.findElement(this.qtyProduct);
        String quantity = qtyProduct.getText();
        System.out.println(quantity);
        return DataConverter.parseStockValue(quantity);

    }

    public ProductData getOpenedProductInfo() {
        CustomReporter.logAction("Get information about currently opened product");

        try{
        String name=driver.findElement(By.cssSelector("h1.h1")).getText();
        float price = DataConverter.parsePriceValue(driver.findElement(By.cssSelector(".current-price span[content]")).getText());
        int qty=Integer.parseInt(driver.findElement(By.cssSelector("#quantity_wanted")).getAttribute("value"));

        return new ProductData(name,qty,price);

        }
        catch (UnsupportedOperationException e){
        throw new UnsupportedOperationException();}
    }

    public void waitForContentLoad(By locator) {

        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
