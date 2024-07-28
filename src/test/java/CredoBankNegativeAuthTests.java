import data.dataproviders.AuthDataProviders;
import data.enums.LanguageEnum;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steps.AuthPageSteps;
import steps.components.HeaderSteps;
import steps.components.LanguagePopUpSteps;
import steps.components.MessagePopUpSteps;
import utils.testsHelpers.BaseSetUp;

import static data.AuthData.invalidClassName;
import static data.CommonData.baseUrl;


@Epic("Credo bank tests")
@Feature("Auth negative test cases")
public class CredoBankNegativeAuthTests extends BaseSetUp {
    private static final ThreadLocal<HeaderSteps> headerSteps = new ThreadLocal<HeaderSteps>();
    private static final ThreadLocal<AuthPageSteps> authPageSteps = new ThreadLocal<AuthPageSteps>();
    private static final ThreadLocal<LanguagePopUpSteps> languagePopUpSteps = new ThreadLocal<LanguagePopUpSteps>();
    private static final ThreadLocal<MessagePopUpSteps> messagePopUpSteps = new ThreadLocal<MessagePopUpSteps>();
    private static final ThreadLocal<SoftAssert> softAssert = new ThreadLocal<SoftAssert>();

    @BeforeMethod(alwaysRun = true)
    public void initPageAndSetUp(Object[] params) {
        headerSteps.set( new HeaderSteps(driver.get()));
        authPageSteps.set(new AuthPageSteps(driver.get()));
        languagePopUpSteps.set(new LanguagePopUpSteps(driver.get()));
        messagePopUpSteps.set(new MessagePopUpSteps(driver.get()));
        softAssert.set(new SoftAssert());

        driver.get().get(baseUrl + "/landing/main/auth");
        LanguageEnum language = (LanguageEnum) params[0];
        headerSteps.get().waitUntilHeaderLoads();
        if (language.toString().contains(headerSteps.get().getCurrentLanguage())) return;
        headerSteps.get().clickOnLanguageSwitchButton();
        languagePopUpSteps.get().clickOnLanguage(language);
    }

    @Test(description = "Auth test with empty username. Expect to get red border on username input and submit button should be in disabled state.",
            dataProviderClass = AuthDataProviders.class, dataProvider = "missingUsername")
    @Story("Auth test with empty username. Expect to get red border on username input and submit button should be in disabled state.")
    public void loginUsernameMissing(LanguageEnum language, String username, String password) {
        authPageSteps
                .get()
                .waitAuthPageToLoad()
                .fillUsername(username)
                .fillPassword(password);

        softAssert.get().assertFalse(authPageSteps.get().getSubmitButtonStatus(), "Submit button should be in disabled state");
        softAssert.get().assertTrue(authPageSteps.get().getUsernameClassName().contains(invalidClassName), "User name should have red border");
        softAssert.get().assertAll();
    }

    @Test(description = "Auth test with empty password. Expect to get red border on password input and submit button should be in disabled state.",
            dataProviderClass = AuthDataProviders.class, dataProvider = "missingPassword", priority = 1)
    @Story("Auth test with empty password. Expect to get red border on password input and submit button should be in disabled state.")
    public void loginPasswordMissing(LanguageEnum language, String username, String password) {
        authPageSteps
                .get()
                .waitAuthPageToLoad()
                .fillUsername(username)
                .fillPassword(password);

        softAssert.get().assertFalse(authPageSteps.get().getSubmitButtonStatus(), "Submit button should be in disabled state");
        softAssert.get().assertTrue(authPageSteps.get().getPasswordClassName().contains(invalidClassName), "Password should have red border");
        softAssert.get().assertAll();
    }

    @Test(description = "Auth test with empty username and password. Expect to get red border on username password input, submit button should be in disabled state.",
            dataProviderClass = AuthDataProviders.class, dataProvider = "missingUsernameAndPassword", priority = 2)
    @Story("Auth test with empty username and password. Expect to get red border on username password input, submit button should be in disabled state.")
    public void loginUsernameAndPasswordMissing(LanguageEnum language, String username, String password) {
        authPageSteps.get()
                .waitAuthPageToLoad()
                .fillUsername(username)
                .fillPassword(password);

        softAssert.get().assertFalse(authPageSteps.get().getSubmitButtonStatus(), "Submit button should be in disabled state");
        softAssert.get().assertTrue(authPageSteps.get().getUsernameClassName().contains(invalidClassName), "User name should have red border");
        softAssert.get().assertTrue(authPageSteps.get().getPasswordClassName().contains(invalidClassName), "Password should have red border");
        softAssert.get().assertAll();
    }

    @Test(description = "Auth test with invalid username and password. Expect to get error message in specified language.",
            dataProviderClass = AuthDataProviders.class, dataProvider = "invalidUsernameAndPassword", priority = 3)
    @Story("Auth test with invalid username and password. Expect to get error message in specified language.")
    public void invalidUsernameAndPassword(LanguageEnum language, String username, String password, String expectedErrorMessage) {
        authPageSteps.get()
                .waitAuthPageToLoad()
                .fillUsername(username)
                .fillPassword(password)
                .clickOnSubmitButton();
        messagePopUpSteps.get().waitUntilMessageContainerLoads();
        Assert.assertEquals(messagePopUpSteps.get().getMessageText(), expectedErrorMessage);
    }
}
