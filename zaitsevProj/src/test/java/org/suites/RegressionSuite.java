package org.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.loginTests.LoginTestWithPageObject;
import org.postsTests.EditPostTest;
import org.postsTests.createNewPostTest;
import org.registrationTest.ValidationMessagesTest;
import org.registrationTest.ValidationMessagesTestWithoutSomeFields;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        createNewPostTest.class,
        EditPostTest.class,
        ValidationMessagesTest.class,
        ValidationMessagesTestWithoutSomeFields.class
})
public class RegressionSuite {
}
