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
public class ValidationMessagesTest extends BaseTest {
    @Test
    @Parameters(method = "parametersForTestValidationMessages")
    public void TC03_testValidationMessages(
            User userData, String expectedMessages) {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoRegistrationUserNameField(userData.getUserName())
                .enterTextIntoRegistrationEmailField(userData.getEmail())
                .enterTextIntoRegistrationPasswordField(userData.getPassword())
                .checkErrorMessages(expectedMessages);
    }

    public Object[][] parametersForTestValidationMessages() {
        return new Object[][]{
//                {"tr", "tr1", "tr2", ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {new User("TCO3").updateUserName(User.SHORT_USERNAME_NOT_VALID)
                        .updateEmail(User.SHORT_EMAIL_NOT_VALID)
                        .updatePassword(User.SHORT_PASSWORD_NOT_VALID),
                        ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},

//                {"taras", "tr1", "tr2", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {new User("TCO3").updateEmail(User.SHORT_EMAIL_NOT_VALID)
                        .updatePassword(User.SHORT_PASSWORD_NOT_VALID),
                        ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},

//                {"taras", "tr1", "123456qwerty", ERROR_EMAIL}
                {new User("TCO3").updateEmail(User.SHORT_EMAIL_NOT_VALID),
                        ERROR_EMAIL}
        };
    }


    @Test
    public void TC04_testValidationMessagesViaTabAntEnter() {

        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoRegistrationUserNameFieldUsingActions("tr")
                .enterTextIntoRegistrationEmailFieldUsingActions("tr")
                .enterTextIntoRegistrationPasswordFieldUsingActions("tr")
                .pressEnterKeyOnRegistrationForm()
                .checkErrorMessages(ERROR_USERNAME
                        + SEMICOLON
                        + ERROR_EMAIL
                        + SEMICOLON
                        + ERROR_PASSWORD);
    }
}
