package org.registrationTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.categories.SmokeTestsFilter;
import org.data.RegistrationValidationMessages;
import org.data.User;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.internal.runners.JUnit38ClassRunner;
import org.junit.runner.RunWith;

import java.util.Scanner;

import static org.data.RegistrationValidationMessages.*;
import static org.data.RegistrationValidationMessages.ERROR_PASSWORD;


@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestsFilter.class)
public class ValidationMessagesTest extends BaseTest {
    @Test
    @Parameters(method = "parametersForTestValidationMessages")
    public void TC05_testValidationMessages(
            User userData, String expectedMessages) {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoRegistrationUserNameField(userData.getUserName())
                .enterTextIntoRegistrationEmailField(userData.getEmail())
                .enterTextIntoRegistrationPasswordField(userData.getPassword())
                .checkErrorsMessages(expectedMessages);
    }

    public Object[][] parametersForTestValidationMessages() {
        return new Object[][]{
               // {"tr", "tr1", "tr2", ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {new User("TC03").updateUserName(User.SHORT_USER_NAME_NOT_VALID)
                                          .updateEmail(User.SHORT_EMAIL_NOT_VALID)
                                          .updatePassword(User.SHORT_PASSWORD_NOT_VALID)
                        , ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
               // {"taras", "tr", "tr2", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {new User("TC03").updateEmail(User.SHORT_EMAIL_NOT_VALID)
                        .updatePassword(User.SHORT_PASSWORD_NOT_VALID)
                        , ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
               // {"taras", "tr1", "123456qwerty", ERROR_EMAIL}
                {new User("TC03").updateEmail(User.SHORT_EMAIL_NOT_VALID)
                        , ERROR_EMAIL},
        };
    }
}