package home4.tests;
import home4.BaseTest;
import home4.model.ProductData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CreateProductTest extends BaseTest {



    @Test(dataProvider = "logIn")
    public void createNewProduct(String login, String password) {

        actions.login(login, password);
        Assert.assertTrue(driver.findElement(By.id("main")).getText().contains("Пульт"), "Wrong title");

        actions.createProduct(ProductData.generate());

            }

    @Test(dependsOnMethods="createNewProduct")
    public void checkNewProduct() {
        actions.checkProduct();

    }


    // TODO implement logic to check product visibility on website
}
