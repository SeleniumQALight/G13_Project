package org.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.loginTests.LoginTestWithPageObject;
import org.postsTests.CreateNewPostTest;
import org.postsTests.EditPostTest;
import org.registrationTests.ValidationMessagesTest;
import org.registrationTests.ValidationMessagesTestWithUser;
import org.registrationTests.ValidationMessagesTestWithoutSomeFields;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        CreateNewPostTest.class,
        ValidationMessagesTest.class,
        ValidationMessagesTestWithUser.class,
        ValidationMessagesTestWithoutSomeFields.class,
        EditPostTest.class

})
public class RegressionSuite {
}
