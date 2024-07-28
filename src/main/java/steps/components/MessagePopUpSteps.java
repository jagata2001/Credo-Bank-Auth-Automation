package steps.components;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.components.MessagePopUp;

import java.time.Duration;

public class MessagePopUpSteps extends MessagePopUp {
    private final WebDriverWait wait;

    public MessagePopUpSteps(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Wait until message pop up loads.")
    public MessagePopUpSteps waitUntilMessageContainerLoads() {
        wait.until(ExpectedConditions.visibilityOf(mainContainer));
        return this;
    }

    @Step("Get message text.")
    public String getMessageText() {
        return message.getText();
    }
}
