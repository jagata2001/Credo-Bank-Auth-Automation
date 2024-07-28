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
    private AuthPageSteps authPageSteps;
    private MessagePopUpSteps messagePopUpSteps;
    private SoftAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    public void initPageAndSetUp(Object[] params) {
        HeaderSteps headerSteps = new HeaderSteps(driver);
        LanguagePopUpSteps languagePopUpSteps = new LanguagePopUpSteps(driver);
        authPageSteps = new AuthPageSteps(driver);
        messagePopUpSteps = new MessagePopUpSteps(driver);
        softAssert = new SoftAssert();

        driver.get(baseUrl + "/landing/main/auth");
        LanguageEnum language = (LanguageEnum) params[0];
        headerSteps.waitUntilHeaderLoads();
        if (language.toString().contains(headerSteps.getCurrentLanguage())) return;
        headerSteps.clickOnLanguageSwitchButton();
        languagePopUpSteps.clickOnLanguage(language);
    }

    @Test(description = "Auth test with empty username. Expect to get red border on username input and submit button should be in disabled state.",
            dataProviderClass = AuthDataProviders.class, dataProvider = "missingUsername")
    @Story("Auth test with empty username. Expect to get red border on username input and submit button should be in disabled state.")
    public void loginUsernameMissing(LanguageEnum language, String username, String password) {
        authPageSteps
                .waitAuthPageToLoad()
                .fillUsername(username)
                .fillPassword(password);

        softAssert.assertFalse(authPageSteps.getSubmitButtonStatus(), "Submit button should be in disabled state");
        softAssert.assertTrue(authPageSteps.getUsernameClassName().contains(invalidClassName), "User name should have red border");
        softAssert.assertAll();
    }

    @Test(description = "Auth test with empty password. Expect to get red border on password input and submit button should be in disabled state.",
            dataProviderClass = AuthDataProviders.class, dataProvider = "missingPassword", priority = 1)
    @Story("Auth test with empty password. Expect to get red border on password input and submit button should be in disabled state.")
    public void loginPasswordMissing(LanguageEnum language, String username, String password) {
        authPageSteps
                .waitAuthPageToLoad()
                .fillUsername(username)
                .fillPassword(password);

        softAssert.assertFalse(authPageSteps.getSubmitButtonStatus(), "Submit button should be in disabled state");
        softAssert.assertTrue(authPageSteps.getPasswordClassName().contains(invalidClassName), "Password should have red border");
        softAssert.assertAll();
    }

    @Test(description = "Auth test with empty username and password. Expect to get red border on username password input, submit button should be in disabled state.",
            dataProviderClass = AuthDataProviders.class, dataProvider = "missingUsernameAndPassword", priority = 2)
    @Story("Auth test with empty username and password. Expect to get red border on username password input, submit button should be in disabled state.")
    public void loginUsernameAndPasswordMissing(LanguageEnum language, String username, String password) {
        authPageSteps
                .waitAuthPageToLoad()
                .fillUsername(username)
                .fillPassword(password);

        softAssert.assertFalse(authPageSteps.getSubmitButtonStatus(), "Submit button should be in disabled state");
        softAssert.assertTrue(authPageSteps.getUsernameClassName().contains(invalidClassName), "User name should have red border");
        softAssert.assertTrue(authPageSteps.getPasswordClassName().contains(invalidClassName), "Password should have red border");
        softAssert.assertAll();
    }

    @Test(description = "Auth test with invalid username and password. Expect to get error message in specified language.",
            dataProviderClass = AuthDataProviders.class, dataProvider = "invalidUsernameAndPassword", priority = 3)
    @Story("Auth test with invalid username and password. Expect to get error message in specified language.")
    public void invalidUsernameAndPassword(LanguageEnum language, String username, String password, String expectedErrorMessage) {
        authPageSteps
                .waitAuthPageToLoad()
                .fillUsername(username)
                .fillPassword(password)
                .clickOnSubmitButton();
        messagePopUpSteps.waitUntilMessageContainerLoads();
        Assert.assertEquals(messagePopUpSteps.getMessageText(), expectedErrorMessage);
    }
}
