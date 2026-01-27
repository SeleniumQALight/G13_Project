package org.registrationTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.data.RegistrationValidationMessages;
import org.data.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.data.RegistrationValidationMessages.*;
import static org.data.RegistrationValidationMessages.ERROR_PASSWORD;

@RunWith(JUnitParamsRunner.class)
public class ValidationMessagesTest extends BaseTest {

    //параметризовані тести - які відрізняють тестової датою

    @Test
    @Parameters(method = "parametersForTC06_testValidationMessages")
    public void TC06_testValidationMessages(
            String userName, String email, String password, String expectedMessages) {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoRegistrationUserNameField(userName)
                .enterTextIntoRegistrationEmailField(email)
                .enterTextIntoRegistrationPasswordField(password)
                .checkErrorsMessages(expectedMessages);
    }

    public Object[][] parametersForTC06_testValidationMessages() {
        return new Object[][]{
                {"tr","tr2","tr3",ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"taras","tr1","tr2",ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"taras","tr1","123456qwerty",ERROR_EMAIL}


        };
    }
}
