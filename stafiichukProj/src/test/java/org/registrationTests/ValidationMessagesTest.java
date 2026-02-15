package org.registrationTests;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.RegistrationValidationMessages.*;
import static org.data.RegistrationValidationMessages.ERROR_PASSWORD;

public class ValidationMessagesTest extends BaseTest {

    @Test
    public void TC03_testValidationMessages(){
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
}
