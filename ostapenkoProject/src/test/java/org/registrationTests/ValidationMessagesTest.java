package org.registrationTests;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.RegistrationValidationMessages.*;

public class ValidationMessagesTest extends BaseTest {
    @Test
    public void TC03_testValidationMessages() {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoRegistrationUserNameField("tr")
                .enterTextIntoRegistrationEmailField("tr")
                .enterTextIntoRegistrationPasswordField("tr")
                .checkErrorMessages(ERROR_USERNAME
                        + SEMICOLON
                        + ERROR_EMAIL
                        + SEMICOLON
                        + ERROR_PASSWORD);
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
