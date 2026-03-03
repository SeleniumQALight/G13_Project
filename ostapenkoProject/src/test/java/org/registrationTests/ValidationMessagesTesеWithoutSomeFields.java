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
public class ValidationMessagesTesеWithoutSomeFields extends BaseTest {
    @Test
    @Parameters(method = "parametersForTestValidationMessages")
    public void TC03_testValidationMessages(
            User userData, String expectedMessages) {
        pageProvider.getLoginPage().openLoginPage()
                .enterRegistrationDataIfNotNull(userData)
                .clickSignUpButton();

        pageProvider.getLoginPage()
                .checkErrorMessages(expectedMessages);
    }

    public Object[][] parametersForTestValidationMessages() {
        return new Object[][]{
                {new User("TCO31").updateUserName(null),
                        ERROR_USERNAME},
                {new User("TCO32").updateEmail(null), ERROR_EMAIL},
                {new User("TCO33").updatePassword(null), ERROR_PASSWORD}

        };
    }


}
