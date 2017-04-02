package ua.mytest.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;

/**
 * Created by nata on 01.04.2017.
 */
public class DriverResource {



  public static WebDriver getWebDriver()

  {


          System.setProperty("webdriver.gecko.driver",

                  "src\\main\\resources\\geckodriver.exe");

              return new FirefoxDriver();


      }



  }


