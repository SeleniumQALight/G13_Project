package org.suites;

import org.apiTests.ApiTests;
import org.categories.SmokeTestsFilter;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.loginTests.LoginTestWithPageObject;
import org.postsTests.CreateNewPostTest;
import org.registrationTests.ValidationMessagesTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestsFilter.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        CreateNewPostTest.class,
        ValidationMessagesTest.class,
        ApiTests.class
})

public class SmokeSuite {
}
