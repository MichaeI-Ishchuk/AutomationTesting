package ua.mytest.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import ua.mytest.tests.utils.EventHandler;
import ua.mytest.tests.utils.Properties;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by nata on 01.04.2017.
 */
public class DriverResource {

    private static String email="webinar.test@gmail.com";
    private static String password="Xcg7299bnSmMuRLp9ITw";
    private static WebDriver driver2;

    public static WebDriver getDriver(String nameBrowser)

    {

        switch (nameBrowser) {

        case "Firefox":
            System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe");
            return new FirefoxDriver();

        case "Chrome":
            System.setProperty(
                    "webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");

            return new ChromeDriver();

        case "IExplorer":
            System.setProperty(
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
        driver2=driver;
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        EventFiringWebDriver wrappedDriver = new EventFiringWebDriver(driver);
        wrappedDriver.register(new EventHandler());


        return wrappedDriver;
    }
    //////for home2
    public static void close()

    {
        driver2.quit();

    }

    //////for home2
    protected static void sleep(long time)

    {

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //////for home2
        public static String getUrl() {

            return Properties.getBaseAdminUrl();
        }
    //////for home2
        public static String getEmail() {

            return email;

        }
    //////for home2
        public static String getPassword() {

            return password;

        }
    //////for home2
    protected static void print(String title)

    {

        System.out.println(title);

    }


}