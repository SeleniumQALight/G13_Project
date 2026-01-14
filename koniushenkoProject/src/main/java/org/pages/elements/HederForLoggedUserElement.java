package org.pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;

public class HederForLoggedUserElement extends CommonActionsWithElements {

    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    public HederForLoggedUserElement(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
    }
}
