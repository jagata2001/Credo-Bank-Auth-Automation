package pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header {
    @FindBy(id = "respWrap1")
    protected WebElement mainContainer;

    @FindBy(id = "languageSwitcherBtn")
    protected WebElement languageSwitchButton;

    @FindBy(xpath = "//div[@id='languageSwitcherBtn']/p")
    protected WebElement currentLanguage;

    public Header(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
