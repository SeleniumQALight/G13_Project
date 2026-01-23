package org.registrationTest;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.RegistrationValidationMessages.*;

public class ValidationMessagesTest extends BaseTest {

    @Test
    public void TC03_testValidationMessages(){
        pageProvider.getLoginPage().openLoginPage()
                .enterTextintoRegistrationUserField("tr")
                .enterTextintoRegistrationEmailField("tr")
                .enterTextintoRegistrationPasswordField("tr")
                .checkErrorsMessages(ERROR_USERNAME
                + SEMICOLON
                + ERROR_EMAIL
                + SEMICOLON
                + ERROR_PASSWORD);
    }
}
