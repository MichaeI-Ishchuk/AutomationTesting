package ua.mytest.tests.home2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ua.mytest.tests.DriverResource;


/**
 * Created by nata on 01.04.2017.
 */
public class Test_1 extends DriverResource {



    public static void main(String[] args) throws InterruptedException {

        WebDriver webDriver =  getConfiguredDriver("Chrome");

        webDriver.get(getUrl());
        sleep(2000);

        WebElement emailElement = webDriver.findElement(By.id("email"));
        emailElement.sendKeys(getEmail());

        WebElement passwordElement = webDriver.findElement(By.id("passwd"));
        passwordElement.sendKeys(getPassword());

        WebElement submit = webDriver.findElement(By.name("submitLogin"));
        submit.click();

        sleep(2000);


        WebElement account = webDriver.findElement(By.id("header_employee_box"));
        account.click();

         WebElement logOut = webDriver.findElement(By.id("header_logout"));
         logOut.click();

        webDriver.close();

    }



}
