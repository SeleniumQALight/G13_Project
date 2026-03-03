package org.registrationTest;

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
public class ValidationMessagesTestWithoutSomeFields extends BaseTest {

    @Test
    @Parameters(method = "parametersForTestValidationMessages")
    public void TC031_testValidationMessages(User userData, String expectedMessages) {
        pageProvider.getLoginPage().openLoginPage()
                .enterRegistrationDataIfNotNull(userData)
                .clickOnSignUpButton();
        pageProvider.getLoginPage().checkErrorsMessages(expectedMessages);
    }

    public Object[][] parametersForTestValidationMessages() {
        return new Object[][]{
                {new User("TC031").updateUserName(null),ERROR_USERNAME},
                {new User("TC031").updateEmail(null), ERROR_EMAIL},
                {new User("TC031").updatePassword(null), ERROR_PASSWORD}
        };
    }
}
