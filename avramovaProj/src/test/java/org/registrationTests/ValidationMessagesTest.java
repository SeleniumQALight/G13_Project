package org.registrationTests;

import junitparams.JUnitParamsRunner;
import org.baseTest.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.data.RegistrationValidationMessages.*;
import static org.data.RegistrationValidationMessages.ERROR_PASSWORD;

@RunWith(JUnitParamsRunner.class)
public class ValidationMessagesTest extends BaseTest {

    @Test
    @junitparams.Parameters(method = "parametersForTestValidationMessages")
    public void TC03_testValidationMessages(
            String userName, String email, String password, String expectedMessages) {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoRegistrationUserNameField(userName)
                .enterTextIntoRegistrationEmailField(email)
                .enterTextIntoRegistrationPasswordField(password)
                .checkErrorMessages(expectedMessages);
    }

    public Object[][] parametersForTestValidationMessages() {
        return new Object[][]{
                {"tr", "tr1", "tr2", ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"taras", "tr1", "tr2", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"taras", "tr1", "123456qwerty", ERROR_EMAIL}
        };
    }
}
