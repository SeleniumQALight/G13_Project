package org.bdd.stepDefinitions;

import org.bdd.helpers.WebDriverHelper;
import org.pages.PageProvider;

public class MainSteps {
    protected WebDriverHelper webDriverHelper;
    protected PageProvider pageProvider;

    public MainSteps(WebDriverHelper webDriverHelper) {
        this.webDriverHelper = webDriverHelper;
        pageProvider = new PageProvider(webDriverHelper.getWebDriver());
    }

}
