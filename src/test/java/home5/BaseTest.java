package home5;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Base script functionality, can be used for all Selenium scripts.
 */
public abstract class BaseTest {
    protected WebDriver driver;
    protected GeneralActions actions;
    protected boolean isMobileTesting;

    /**
     * Prepares {@link WebDriver} instance with timeout and browser window configurations.
     *
     * Driver type is based on passed parameters to the automation project,
     * creates {@link ChromeDriver} instance by default.
     *
     */
    @BeforeClass
    @Parameters({"selenium.browser", "selenium.grid"})
    public void setUp(String browser,  String gridUrl) {
        try{
            DesiredCapabilities capabilities;
        switch (browser) {
        case "android":
             capabilities = DesiredCapabilities.android();
           break;
        case "firefox":
            capabilities = DesiredCapabilities.firefox();
            break;
        case "ie":
        case "internet explorer":
            capabilities = DesiredCapabilities.internetExplorer();
            break;
        case "phantomjs":
            capabilities = DesiredCapabilities.phantomjs();
            break;
        case "chrome":
        default:
           capabilities = DesiredCapabilities.chrome();
                    }
        driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
        }
       catch (Exception e)
       {

           e.printStackTrace();
           throw new SkipException("Unable to create RemoteWebDriver instance");
       }
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        // unable to maximize window in mobile mode
        if (!isMobileTesting(browser))
            driver.manage().window().maximize();

        isMobileTesting = isMobileTesting(browser);

        actions = new GeneralActions(driver);
    }

    /**
     * Closes driver instance after test class execution.
     */
//    @AfterClass
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
    /**
     *
     * @return Whether required browser displays content in mobile mode.
     */
    private boolean isMobileTesting(String browser) {
        switch (browser) {
            case "android":
                return true;
            case "firefox":
            case "ie":
            case "internet explorer":
            case "chrome":
            case "phantomjs":
            default:
                return false;
        }
    }
}
