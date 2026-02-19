package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.commonElements.HeaderForLoggedUserElement;

import static org.data.TestData.HOME_PAGE_URL;

public class HomePage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = ".//h2[text()='Latest posts from those you follow']")
    private WebElement headerOnHomePage;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }





    public HomePage checkIsRedirectToHomePage() {
        isRedirectedToPage(HOME_PAGE_URL);
        isElementVisible(headerOnHomePage);
        return this;
    }
}
