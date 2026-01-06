package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class createNewPostTest extends BaseTest {
    @Test
    public void createNewPost(){
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCred();   }
}
