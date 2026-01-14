package org.postTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    public void createNewPost() {
        //test steps should be here
        pageProvider.getLoginPage().
                openLoginPageAndFillLoginFormWithValidCred();


    }
}
