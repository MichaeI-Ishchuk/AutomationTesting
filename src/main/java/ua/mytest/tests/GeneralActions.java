package ua.mytest.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ua.mytest.tests.utils.Properties;

import java.util.List;

/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions {
    private WebDriver driver;
    private WebDriverWait wait;
    private static String login="webinar.test@gmail.com";
    private static String password="Xcg7299bnSmMuRLp9ITw";


    private By pass =  By.cssSelector("#passwd");
    private  By loginId = By.cssSelector("#email");
    private  By sub = By.cssSelector("#login_form [name=submitLogin]");
    private  By catalog = By.cssSelector("#subtab-AdminCatalog");
    private  By category = By.cssSelector("#subtab-AdminCategories");
    private By addCategory = By.cssSelector("#page-header-desc-category-new_category");
    private By addName = By.id("name_1");
    private By saveCategory = By.id("category_form_submit_btn");
    private By sortName = By.cssSelector("input[name=categoryFilter_name]");
    private By find = By.id("submitFilterButtoncategory");
    private  By table = By.cssSelector("tbody tr");



    public GeneralActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }


    public void login() {

        driver.navigate().to(Properties.getBaseAdminUrl());

        WebElement loginId = driver.findElement(this.loginId);
        WebElement pass = driver.findElement(this.pass);


        loginId.sendKeys(login);
        pass.sendKeys(password);

        WebElement submit = driver.findElement(this.sub);
        submit.click();







    }

    /**
     * Adds new category in Admin Panel.
     * @param categoryName
     */
    public void createCategory(String categoryName)

    {
        waitForContentLoad(catalog);
        WebElement catalog= driver.findElement(this.catalog);
        WebElement category = driver.findElement(this.category);
      //  wait.until(ExpectedConditions.stalenessOf(catalog));
        Actions action = new Actions(driver);
        action.moveToElement(catalog).moveToElement(category).click().build().perform();

        WebElement addCategories = driver.findElement(this.addCategory);

        addCategories.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.addName));

        WebElement addName = driver.findElement(this.addName);
        WebElement saveCategory = driver.findElement(this.saveCategory);

        addName.click();
        addName.sendKeys(categoryName);


       JavascriptExecutor js= (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();"
                ,saveCategory);


       saveCategory.click();

        WebElement sort = driver.findElement(this.sortName);
        WebElement find = driver.findElement(this.find);

        action.moveToElement(sort).click().sendKeys(categoryName).moveToElement(find).click().build().perform();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(table, 0));
    }

    public void close()

    {
        driver.quit();

    }


    public void waitForContentLoad(By locator) {

        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

}
