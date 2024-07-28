package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LanguagePopUp {
    private WebDriver driver;

    @FindBy(css = ".popup-wrapper")
    protected WebElement mainContainer;

    public LanguagePopUp(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    protected WebElement getLanguageByName(String languageName) {
        return this.driver.findElement(
                By.xpath("//div[@class='popup-wrapper']//div[@class='sub-language']/p[text()='%s']"
                        .formatted(languageName)
                )
        );
    }
}
