package steps.components;

import data.enums.LanguageEnum;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.components.LanguagePopUp;

import java.time.Duration;

public class LanguagePopUpSteps extends LanguagePopUp {
    private final WebDriverWait wait;

    public LanguagePopUpSteps(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Wait until language pop up loads.")
    public LanguagePopUpSteps waitUntilPopUpLoads() {
        wait.until(ExpectedConditions.visibilityOf(mainContainer));
        return this;
    }

    @Step("click on language: {0}")
    public LanguagePopUpSteps clickOnLanguage(LanguageEnum language) {
        getLanguageByName(language.toString()).click();
        return this;
    }


}
