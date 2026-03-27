package org.bdd.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import org.api.PBApiHelper;
import org.bdd.helpers.WebDriverHelper;

public class PBHook {
    WebDriverHelper webDriverHelper;
    PBApiHelper pbApiHelper;

    public PBHook(WebDriverHelper webDriverHelper){
        this.webDriverHelper=webDriverHelper;
    }

    @Before
    public void setUp(){
        RestAssured.baseURI = "https://api.privatbank.ua/p24api/";
        pbApiHelper = new PBApiHelper();

    }

    @After
    public void tearDown(){
        webDriverHelper.quiteDriver();
    }
}
