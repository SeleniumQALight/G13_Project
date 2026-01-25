package org.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

 abstract public class ParentPage extends CommonActionsWithElements{
    protected  String baseUrl = "https://aqa-complexapp.onrender.com";

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }
    abstract String getRelativeUrl();

    protected void checkUrl(){
        Assert.assertEquals("Url is not expected"
                ,baseUrl + getRelativeUrl()
                , webDriver.getCurrentUrl());
    }

      // метод по перевірці чи відкрита потрібна сторінка по патерну
     // https://aqa-complexapp.onrender.com/post/64d21e84903640003414c338
    // regex for 64d21e84903640003414c338
   // [a-zA-Z0-9]{24}
     protected void checkUrlWithPattern(){
        Assert.assertTrue("Url is not expected \n"
                + "Expected pattern: " + baseUrl + getRelativeUrl() + "\n"
                + "Actual URL: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
     }


 }
