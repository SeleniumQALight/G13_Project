package org.pages;

import org.openqa.selenium.WebDriver;

public class ParentPage extends CommonActionsWithElement {
    protected String baseUrl = "https://aqa-complexapp.onrender.com";

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }
}
