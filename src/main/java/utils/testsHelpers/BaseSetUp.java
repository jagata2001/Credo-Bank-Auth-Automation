package utils.testsHelpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


public class BaseSetUp {
    protected static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    @BeforeSuite
    public void installChromeDriver() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUpWebDriver() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        BaseSetUp.driver.set(driver);
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if(driver.get() != null) driver.get().quit();
    }
}
