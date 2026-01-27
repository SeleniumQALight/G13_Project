package org.registrationTests;

import org.baseTest.BaseTest;
import org.data.RegistrationValidationMessages;
import org.data.TestData;
import org.junit.Test;

import static org.data.RegistrationValidationMessages.*;
import static org.data.RegistrationValidationMessages.ERROR_PASSWORD;

public class ValidationMessagesTest extends BaseTest {

    @Test
    public void TC06_testValidationMessages() {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoRegistrationUserNameField("tr")
                .enterTextIntoRegistrationEmailField("tr")
                .enterTextIntoRegistrationPasswordField("tr")
                .checkErrorsMessages(ERROR_USERNAME
                        + SEMICOLON
                        + ERROR_EMAIL
                        + SEMICOLON
                        + ERROR_PASSWORD);
    }

    @Test
    public void TC09_testValidationMessagesWithTabAndEnter() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getCommonActionsWithElements().pressTabKeyOnKeyboard();
        pageProvider.getCommonActionsWithElements().pressTabKeyOnKeyboard();
        pageProvider.getCommonActionsWithElements().pressTabKeyOnKeyboard();
        pageProvider.getCommonActionsWithElements().pressTabKeyOnKeyboard();
        pageProvider.getCommonActionsWithElements().pressTabKeyOnKeyboard();
        pageProvider.getCommonActionsWithElements().enterTextInInputWithActions("tr");
        pageProvider.getCommonActionsWithElements().pressTabKeyOnKeyboard();
        pageProvider.getCommonActionsWithElements().enterTextInInputWithActions("tr");
        pageProvider.getCommonActionsWithElements().pressTabKeyOnKeyboard();
        pageProvider.getCommonActionsWithElements().enterTextInInputWithActions("tr");
        pageProvider.getCommonActionsWithElements().pressEnterKeyOnKeyboard();
        pageProvider.getLoginPage().checkErrorsMessages(ERROR_USERNAME
                + SEMICOLON
                + ERROR_EMAIL
                + SEMICOLON
                + ERROR_PASSWORD);
    }
}
