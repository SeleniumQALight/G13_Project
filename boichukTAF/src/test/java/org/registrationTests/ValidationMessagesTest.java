package org.registrationTests;

import org.baseTest.BaseTest;
import org.data.RegistrationValidationMessages;
import org.junit.Test;

import static org.data.RegistrationValidationMessages.*;
import static org.data.RegistrationValidationMessages.ERROR_PASSWORD;

public class ValidationMessagesTest extends BaseTest {
    @Test
    public void TC05_testValidationMessages() {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoRegistrationUserNameField("ar")
                .enterTextIntoRegistrationEmailField("er")
                .enterTextIntoRegistrationPasswordField("rr")
                .checkErrorsMessages(ERROR_USERNAME
                        + SEMICOLON
                        + ERROR_EMAIL
                        + SEMICOLON
                        + ERROR_PASSWORD);
    }
}