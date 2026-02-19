package org.registrationTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.categories.SmokeTestsFilter;
import org.data.RegistrationVadidationMessages;
import org.data.TestData;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import static org.data.RegistrationVadidationMessages.*;
import static org.data.RegistrationVadidationMessages.ERROR_PASSWORD;


@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestsFilter.class)
public class ValidationMessagesTest  extends BaseTest {

    @Test
    @Parameters(method = "parametersForTestValidationMessages")
    public void TC03_testValidationMessages(String userName,
                                            String email, String password, String expectedMessage
    ) {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoRegistrationUserNameField(userName)
                .enterTextIntoRegistrationEmailField(email)
                .enterTextIntoRegistrationPasswordField(password)
                .checkErrorsMessages(expectedMessage);


    }

    public Object[][] parametersForTestValidationMessages() {
        return new Object[][]{
                {"mi", "mi1", "mi2", ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"mio", "mi1", "mi254344", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"taras", "mi1", "mi2344gffthds", ERROR_EMAIL}

        };

    }
}

