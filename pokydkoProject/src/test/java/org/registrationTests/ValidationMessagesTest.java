package org.registrationTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.testdata.RegistrationValidationMessages.*;

@RunWith(JUnitParamsRunner.class)
public class ValidationMessagesTest extends BaseTest {

    @Test
    @Parameters(method = "parametersForTestValidationMessages")
    public void TC03_testValidationMessages(
            String username, String email, String password, String expectedMessages) {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoRegistrationUserNameField(username)
                .enterTextIntoRegistrationEmailField(email)
                .enterTextIntoRegistrationPasswordField(password)
                .checkErrorsMessages(expectedMessages);
    }

    public Object[][] parametersForTestValidationMessages() {
        return new Object[][]{
                {"tr", "tr1", "tr2", ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"taras", "tr1", "tr2", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"taras", "tr1", "123456qwerty", ERROR_EMAIL}
        };
    }
}
