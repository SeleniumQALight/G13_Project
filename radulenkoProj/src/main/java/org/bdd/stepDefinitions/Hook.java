package org.bdd.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.bdd.helpers.WebDriverHelper;

public class Hook {
    WebDriverHelper webDriverHelper;

    public Hook(WebDriverHelper webDriverHelper){
        this.webDriverHelper = webDriverHelper;
    }

    @Before
    public void setUp(){
//        webDriverHelper = new WebDriverHelper();
    }
    @After
    public void tearDown(){
        webDriverHelper.quitDriver();
    }

}
