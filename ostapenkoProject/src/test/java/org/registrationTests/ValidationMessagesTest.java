package org.registrationTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.data.RegistrationValidationMessages.*;

@RunWith(JUnitParamsRunner.class)
public class ValidationMessagesTest extends BaseTest {
    @Test
    @Parameters(method = "parametersForTestValidationMessages")
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
