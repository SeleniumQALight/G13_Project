package org.bdd.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import org.api.dto.helpers.ApiHelper;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestData;
import org.pages.ParentPage;

public class Hook {
    WebDriverHelper webDriverHelper;
    ApiHelper apiHelper;

    public Hook(WebDriverHelper webDriverHelper) {
        this.webDriverHelper = webDriverHelper;
    }

    @Before(order = 10)
    public void setup() {
        RestAssured.baseURI = ParentPage.baseUrl + "/api/";
        apiHelper = new ApiHelper();
    }

    @After(order = 15)
    public void tearDown() {
        webDriverHelper.quitDriver();
    }

    @Before(value = "@deletePostsTillPresentForDefaultUser", order = 50)

    @After(value = "@deletePostsTillPresentForDefaultUser", order = 50)
    public void deletePostsTillPresent() {
//        String username = TestData.currentUser;
        String username = TestData.VALID_LOGIN_API;
        apiHelper.deleteAllPostsTillPresent(username, apiHelper.getToken(username, TestData.VALID_PASSWORD_API));

    }

}