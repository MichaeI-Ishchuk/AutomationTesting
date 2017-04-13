package ua.mytest.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import ua.mytest.tests.utils.EventHandler;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by nata on 01.04.2017.
 */
public class DriverResource {

  public static WebDriver getDriver(String nameBrowser)

  {

      switch (nameBrowser) {

      case "Firefox":
          System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe");
          return new FirefoxDriver();


      case "Chrome":  System.setProperty(
              "webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");

      return new ChromeDriver();

      case "IExplorer":  System.setProperty(
              "webdriver.ie.driver", "src\\main\\resources\\IEDriverServer.exe");

          return new InternetExplorerDriver();


      default:
          System.setProperty(
                  "webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");

          return new ChromeDriver();

      }

  }

    public static EventFiringWebDriver getConfiguredDriver(String browser) {
        WebDriver driver = getDriver(browser);

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        EventFiringWebDriver wrappedDriver = new EventFiringWebDriver(driver);
        wrappedDriver.register(new EventHandler());

        return wrappedDriver;
    }

  }


