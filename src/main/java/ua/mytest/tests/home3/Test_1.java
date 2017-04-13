package ua.mytest.tests.home3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ua.mytest.tests.DriverResource;
import ua.mytest.tests.GeneralActions;
import ua.mytest.tests.utils.Properties;

/**
 * Created by nata on 01.04.2017.
 */
public class Test_1 extends DriverResource {




    public static void main(String[] args) throws InterruptedException {

        WebDriver webDriver = getConfiguredDriver("Chrome");
        GeneralActions actions = new GeneralActions(webDriver);
        actions.login();
        actions.createCategory("NewCategory");
        actions.close();


        WebDriver webDriver2 = getConfiguredDriver("Firefox");
        GeneralActions actions2 = new GeneralActions(webDriver2);
        actions2.login();
        actions2.createCategory("NewCategory2");
        actions2.close();

        WebDriver webDriver3 = getConfiguredDriver("IExplorer");
        GeneralActions actions3 = new GeneralActions(webDriver3);
        actions3.login();
        actions3.createCategory("NewCategory3");
        actions3.close();


    }

}
