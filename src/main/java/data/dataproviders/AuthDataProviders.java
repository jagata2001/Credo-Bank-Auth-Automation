package data.dataproviders;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;

import static data.AuthData.*;
import static data.enums.LanguageEnum.*;

public class AuthDataProviders {

    @DataProvider
    public Object[][] missingUsername() {
        return new Object[][]{
                {GEO, "", RandomStringUtils.randomAlphabetic(8)},
                {MEG, "", RandomStringUtils.randomAlphabetic(8)},
                {SVA, "", RandomStringUtils.randomAlphabetic(8)}
        };
    }

    @DataProvider
    public Object[][] missingPassword() {
        return new Object[][]{
                {GEO, RandomStringUtils.randomAlphabetic(8), ""},
                {MEG, RandomStringUtils.randomAlphabetic(8), ""},
                {SVA, RandomStringUtils.randomAlphabetic(8), ""}
        };
    }

    @DataProvider
    public Object[][] missingUsernameAndPassword() {
        return new Object[][]{
                {GEO, "", ""},
                {MEG, "", ""},
                {SVA, "", ""}
        };
    }

    @DataProvider
    public Object[][] invalidUsernameAndPassword() {
        return new Object[][]{
                {GEO, RandomStringUtils.randomAlphabetic(8), RandomStringUtils.randomAlphabetic(8), errorMessageGEO},
                {MEG, RandomStringUtils.randomAlphabetic(8), RandomStringUtils.randomAlphabetic(8), errorMessageMEG},
                {SVA, RandomStringUtils.randomAlphabetic(8), RandomStringUtils.randomAlphabetic(8), errorMessageSVA}
        };
    }

}
