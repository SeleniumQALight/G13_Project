package org.pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;

public class HeaderForLoggedUserElement extends CommonActionsWithElements {

    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    public HeaderForLoggedUserElement(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderForLoggedUserElement clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return this;
    }
}
