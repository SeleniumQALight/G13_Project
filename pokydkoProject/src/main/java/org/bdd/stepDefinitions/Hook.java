package org.bdd.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import org.api.ApiHelper;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestData;
import org.pages.ParentPage;

public class Hook {
    WebDriverHelper webDriverHelper;
    ApiHelper apiHelper;

    public Hook(WebDriverHelper webDriverHelper) {
        this.webDriverHelper = webDriverHelper;
    }

    @Before (order = 10)
    public void setup() {
//        webDriverHelper = new WebDriverHelper();
        RestAssured.baseURI = ParentPage.baseUrl + "/api/";
        apiHelper = new ApiHelper();
    }

    @After (order = 15)
    public void tearDown() {
        webDriverHelper.quitDriver();
    }

    @Before(value = "@deletePostsTillPresentForDefaultUser", order = 50)
    @After(value = "@deletePostsTillPresentForDefaultUser", order = 50)
    public void deletePostsTillPresent() {
//            String userName = TestData.currentUser;
        String userName = TestData.VALID_USERNAME_API;
        apiHelper.deleteAllPostsTillPresent(userName, apiHelper.getToken(userName, TestData.VALID_PASSWORD_API));

    }
}
