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
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoRegistrationUserNameField(userData.getUsername())
                .enterTextIntoRegistrationEmailField(userData.getEmail())
                .enterTextIntoRegistrationPasswordField(userData.getPassword())
                .checkErrorsMessages(expectedMessages);
    }

    public Object[][] parametersForTestValidationMessages() {
        return new Object[][]{
                //{"tr", "tr1", "tr2", ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {new User("TC03").updateUsername(User.SHORT_USERNAME_NOT_VALID)
                        .updateEmail(User.SHORT_EMAIL_NOT_VALID)
                        .updatePassword(User.SHORT_PASSWORD_NOT_VALID)
                        , ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                //{"taras", "tr1", "tr2", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {new User("TC03").updateEmail(User.SHORT_EMAIL_NOT_VALID)
                        .updatePassword(User.SHORT_PASSWORD_NOT_VALID)
                , ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                //{"taras", "tr1", "123456qwerty", ERROR_EMAIL}
                {new User("TC03").updateEmail(User.SHORT_EMAIL_NOT_VALID)
                , ERROR_EMAIL},
        };
    }
}
