package steps.components;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.components.Header;

import java.time.Duration;

public class HeaderSteps extends Header {
    private final WebDriverWait wait;

    public HeaderSteps(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Wait until header loads.")
    public HeaderSteps waitUntilHeaderLoads() {
        wait.until(ExpectedConditions.visibilityOf(mainContainer));
        return this;
    }

    @Step("Click on language switch button.")
    public HeaderSteps clickOnLanguageSwitchButton() {
        languageSwitchButton.click();
        return this;
    }

    @Step("Get current selected language.")
    public String getCurrentLanguage() {
        return currentLanguage.getText();
    }

}
