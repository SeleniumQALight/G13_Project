package org.pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElement;
import org.pages.MyProfilePage;

public class HeaderForLoggedUserElement extends CommonActionsWithElement {

    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;


    public HeaderForLoggedUserElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

}
