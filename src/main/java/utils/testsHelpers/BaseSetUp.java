package utils.testsHelpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


public class BaseSetUp {
    protected ChromeDriver driver;
    @BeforeSuite
    public void installChromeDriver() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUpWebDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if(driver != null) driver.quit();
    }
}
