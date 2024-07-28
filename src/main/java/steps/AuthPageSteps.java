package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AuthPage;

import java.time.Duration;

public class AuthPageSteps extends AuthPage {
    private final WebDriverWait wait;

    public AuthPageSteps(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Wait until auth page loads.")
    public AuthPageSteps waitAuthPageToLoad() {
        wait.until(ExpectedConditions.visibilityOf(mainContainer));
        return this;
    }

    @Step("Fill username: {0}.")
    public AuthPageSteps fillUsername(String username) {
        this.usernameInput.sendKeys(username);
        return this;
    }

    @Step("Get username input tag class names.")
    public String getUsernameClassName() {
        return usernameInput.getAttribute("class");
    }

    @Step("Fill password: {0}.")
    public AuthPageSteps fillPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Get password input tag class names.")
    public String getPasswordClassName() {
        return passwordInput.getAttribute("class");
    }

    @Step("Click on submit button.")
    public AuthPageSteps clickOnSubmitButton() {
        wait.until(
                ExpectedConditions.elementToBeClickable(submitButton)
        ).click();
        return this;
    }

    @Step("Get submit button current state.")
    public boolean getSubmitButtonStatus() {
        return submitButton.isEnabled();
    }
}
