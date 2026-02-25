package org.registrationTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.categories.SmokeTestsFilter;
import org.data.User;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import static org.data.RegistrationValidationMessages.*;


@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestsFilter.class)
public class ValidationMessagesTestWithoutSomeFields extends BaseTest {
    @Test
    @Parameters(method = "parametersForTestValidationMessages")
    public void TC05_testValidationMessages(
            User userData, String expectedMessages) {
        pageProvider.getLoginPage().openLoginPage()
                .enterRegistrationDataNotNull(userData)
                .clickOnSignUpButton();
        pageProvider.getLoginPage()
                .checkErrorsMessages(expectedMessages);
    }

    public Object[][] parametersForTestValidationMessages() {
        return new Object[][]{
                {new User("TC03").updateUserName(null), ERROR_USERNAME},
                {new User("TC03").updateEmail(null), ERROR_EMAIL},
                {new User("TC03").updatePassword(null), ERROR_PASSWORD}

        };
    }
}