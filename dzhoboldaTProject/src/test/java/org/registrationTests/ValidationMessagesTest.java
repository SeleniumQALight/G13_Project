package org.registrationTests;

import org.baseTest.BaseTest;
import org.data.RegistrationVadidationMessages;
import org.data.TestData;
import org.junit.Test;

import static org.data.RegistrationVadidationMessages.*;
import static org.data.RegistrationVadidationMessages.ERROR_PASSWORD;

public class ValidationMessagesTest  extends BaseTest {

    @Test
    public void TC03_testValidationMessages() {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoRegistrationUserNameField("mi")
                .enterTextIntoRegistrationEmailField("mi")
                .enterTextIntoRegistrationPasswordField("mi")
                .checkErrorsMessages(ERROR_USERNAME
                        +  SEMICOLON
                + ERROR_EMAIL
                + SEMICOLON
                + ERROR_PASSWORD);

    }

    }

