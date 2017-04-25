package home5.tests;

import home5.BaseTest;
import home5.utils.DataConverter;
import home5.utils.logging.CustomReporter;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaceOrderTest extends BaseTest {

    @Test
    public void checkSiteVersion() {

        actions.checkSiteVersion();
        // TODO open main page and validate website version
    }

    @Test
    public void createNewOrder() {


        actions.createOrder();
        System.out.println(driver.findElement(By.cssSelector("h3.h1.card-title")).getText());
        Assert.assertTrue(driver.findElement(By.cssSelector("h3.h1.card-title")).getText().toLowerCase().contains("ваш заказ подтверждён"), "Order is confirmed");

        System.out.println(actions.product.getPrice());
        System.out.println(DataConverter.parsePriceValue(driver.findElement(By.cssSelector(".text-xs-left")).getText()));

        Assert.assertTrue(driver.findElement(By.cssSelector(".col-xs-9.details span")).getText().toUpperCase().contains(actions.product.getName()), "Title is corrected");
        Assert.assertTrue(DataConverter.parsePriceValue(driver.findElement(By.cssSelector(".text-xs-left")).getText())==(actions.product.getPrice()), "Price is corrected");
        Assert.assertTrue(Integer.parseInt(driver.findElement(By.cssSelector(".col-xs-2")).getText())==actions.product.getQty(), "Qty is corrected");


        // check updated In Stock value
        actions.checkValueInStock();
        CustomReporter.logAction("Check qty");
        Assert.assertTrue(actions.getQtyProduct()==actions.getQty()-1, "Qty is less");
       
    }

}
