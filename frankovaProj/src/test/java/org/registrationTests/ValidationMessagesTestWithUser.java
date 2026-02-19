package org.registrationTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.categories.SmokeTestsFilter;
import org.data.User;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import static org.data.RegistrationValidationMessages.*;

@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestsFilter.class)
public class ValidationMessagesTestWithUser extends BaseTest {

    //параметризовані тести - які відрізняють тестової датою

    @Test
    @Parameters(method = "parametersForTC06_testValidationMessages")
    public void TC06_testValidationMessages(
            User userData, String expectedMessages) {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoRegistrationUserNameField(userData.getUserName())
                .enterTextIntoRegistrationEmailField(userData.getEmail())
                .enterTextIntoRegistrationPasswordField(userData.getPassword())
                .checkErrorsMessages(expectedMessages);
    }

    public Object[][] parametersForTC06_testValidationMessages() {
        return new Object[][]{
                // {"tr","tr2","tr3",ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {new User("TC06").updateUserName(User.SHORT_USER_NAME_NOT_VALID)
                        .updateEmail(User.SHORT_EMAIL_NOT_VALID)
                        .updatePassword(User.SHORT_PASSWORD_NOT_VALID)
                        , ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
               // {"taras", "tr1", "tr2", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {new User("TC06").updateEmail(User.SHORT_EMAIL_NOT_VALID)
                        .updatePassword(User.SHORT_PASSWORD_NOT_VALID)
                        , ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
              //  {"taras", "tr1", "123456qwerty", ERROR_EMAIL}
                {new User("TC06").updateEmail(User.SHORT_EMAIL_NOT_VALID)
                        , ERROR_EMAIL},


        };
    }

}
