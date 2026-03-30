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
    ApiHelper apiHelper ;

    public  Hook(WebDriverHelper webDriverHelper){
        this.webDriverHelper = webDriverHelper;
    }

    @Before(order   =10)
    public void setUp(){
        RestAssured.baseURI = ParentPage.baseUrl + "/api/";
        apiHelper = new ApiHelper();
    }

    @After(order = 15)
    public void  tearDown(){
        webDriverHelper.quitDriver();
    }

    @Before(value = "@deleteAllPostsTillPresentForDefaultUser",order =50)
    @After (value = "@deleteAllPostsTillPresentForDefaultUser",order =50)
    public  void deletePostTillPresent(){
        String userName = TestData.VALID_USERNAME_API;
//        String userName = TestData.currentUser;
                apiHelper.deleteAllPostsTillPresent(userName,apiHelper.getToken(userName,TestData.VALID_PASSWORD_API));
    }

//    @Before
//     public void deletePostsTillPresent(){
//        String userName = TestData.currentUser;
//    }

}

