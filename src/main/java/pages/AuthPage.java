package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthPage {

    @FindBy(css = "app-main .left")
    protected WebElement mainContainer;

    @FindBy(id = "userName")
    protected WebElement usernameInput;

    @FindBy(id = "newPass")
    protected WebElement passwordInput;

    @FindBy(id = "submitAuth")
    protected WebElement submitButton;

    public AuthPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
