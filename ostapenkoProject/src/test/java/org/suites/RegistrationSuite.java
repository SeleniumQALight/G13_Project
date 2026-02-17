package org.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.loginTests.LoginTestWithPageObject;
import org.loginTests.RefreshTest;
import org.postsTests.CreateNewPostTest;
import org.registrationTests.ValidationMessagesTest;
import org.registrationTests.ValidationMessagesTesеWithoutSomeFields;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        CreateNewPostTest.class,
        ValidationMessagesTest.class,
        ValidationMessagesTesеWithoutSomeFields.class,
        RefreshTest.class
})

public class RegistrationSuite {
}
