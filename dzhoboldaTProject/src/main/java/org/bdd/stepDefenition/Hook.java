package org.bdd.stepDefenition;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.bdd.helpers.WebDriverHelper;


class Hook {
    WebDriverHelper webDriverHelper;

    public  Hook(WebDriverHelper webDriverHelper){
        this.webDriverHelper = webDriverHelper;
    }

    @Before
    public void setUp(){
    }

    @After
    public void  tearDown(){
        webDriverHelper.quitDriver();
    }

}

