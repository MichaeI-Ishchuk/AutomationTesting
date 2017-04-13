package ua.mytest.tests.home2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ua.mytest.tests.DriverResource;
import ua.mytest.tests.utils.Properties;

import java.util.List;

/**
 * Created by nata on 02.04.2017.
 */
public class Test_2 {


    private static String email="webinar.test@gmail.com";
    private static String password="Xcg7299bnSmMuRLp9ITw";


    public static void main(String[] args) throws InterruptedException {

        WebDriver webDriver = DriverResource.getConfiguredDriver("Chrome");

        webDriver.get(Properties.getBaseAdminUrl());
        sleep(2000);

        WebElement emailElement = webDriver.findElement(By.id("email"));
        emailElement.sendKeys(email);

        WebElement passwordElement = webDriver.findElement(By.id("passwd"));
        passwordElement.sendKeys(password);

        WebElement submit = webDriver.findElement(By.name("submitLogin"));
        submit.click();

        sleep(4000);

        WebElement menu = webDriver.findElement(By.className("menu")).findElement(By.id("subtab-AdminParentOrders"));
        menu.click();
        sleep(2000);
        String title = webDriver.findElement(By.className("page-head")).findElement(By.className("page-title")).getText();
        print(title);
        sleep(4000);
        webDriver.navigate().refresh();
        sleep(2000);
        String titleCheck = webDriver.findElement(By.className("page-head")).findElement(By.className("page-title")).getText();
        if (titleCheck.equals(title)){
        print("Ok");}else {print("error");}
        sleep(2000);

       WebElement menu2 = webDriver.findElement(By.id("subtab-AdminCatalog"));
        menu2.click();
        sleep(4000);
        String title2 = webDriver.findElement(By.className("header-toolbar")).findElement(By.tagName("h2")).getText();
        print(title2);
        sleep(2000);
        webDriver.navigate().refresh();
        sleep(2000);
        String titleCheck2 = webDriver.findElement(By.className("header-toolbar")).findElement(By.tagName("h2")).getText();
        if (titleCheck2.equals(title2)){
            print("Ok");}else {print("error");}


        List<WebElement> elementList = webDriver.findElements(By.className("link-levelone"));

        for (WebElement webElement : elementList
             ) {  String text = webElement.findElement(By.tagName("span")).getText();
                      if (text.contains("Клиенты")){webElement.click();
                               break;}
        }

        sleep(4000);
        String title3 = webDriver.findElement(By.className("page-head")).findElement(By.tagName("h2")).getText();
        print(title3);
        sleep(2000);
        webDriver.navigate().refresh();
        sleep(2000);
        String titleCheck3 = webDriver.findElement(By.className("page-head")).findElement(By.tagName("h2")).getText();
        if (titleCheck3.equals(title3)){
            print("Ok");}else {print("error");}
        sleep(2000);

        WebElement menu4 = webDriver.findElement(By.id("subtab-AdminParentCustomerThreads"));
        menu4.click();
        sleep(4000);
        String title4 = webDriver.findElement(By.className("page-head")).findElement(By.tagName("h2")).getText();
        print(title4);
        sleep(2000);
        webDriver.navigate().refresh();
        sleep(2000);
        String titleCheck4 = webDriver.findElement(By.className("page-head")).findElement(By.tagName("h2")).getText();
        if (titleCheck4.equals(title4)){
            print("Ok");}else {print("error");}
        sleep(2000);

        WebElement menu5 = webDriver.findElement(By.id("subtab-AdminStats"));
        menu5.click();
        sleep(4000);
        String title5 = webDriver.findElement(By.className("page-head")).findElement(By.tagName("h2")).getText();
        print(title5);
        sleep(2000);
        webDriver.navigate().refresh();
        sleep(2000);
        String titleCheck5 = webDriver.findElement(By.className("page-head")).findElement(By.tagName("h2")).getText();
        if (titleCheck5.equals(title5)){
            print("Ok");}else {print("error");}


        webDriver.quit();
    }

    private static void sleep(long time)

    {

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void print(String title)

    {

        System.out.println(title);

    }

}
