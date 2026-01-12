package org.pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElement;

public class HeaderForLoggedUserElement extends CommonActionsWithElement {

    @FindBy(xpath = "//img[@alt='My Profile']")
    private WebElement buttonMyProfile;

    public HeaderForLoggedUserElement(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
    }
}