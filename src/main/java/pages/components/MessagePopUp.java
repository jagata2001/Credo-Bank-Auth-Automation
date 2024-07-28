package pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MessagePopUp {

    @FindBy(css = ".notification-container.error")
    public WebElement mainContainer;

    @FindBy(id = "growlText")
    public WebElement message;

    public MessagePopUp(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
