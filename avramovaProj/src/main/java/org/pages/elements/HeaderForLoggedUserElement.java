package org.pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;
import org.pages.MyProfilePage;

public class HeaderForLoggedUserElement extends CommonActionsWithElements {

    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    public HeaderForLoggedUserElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }








    //ці 2 методи були додані в hw5, тому потрібно видалити їх з hw6 після мержу hw5 в main:
    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    public HeaderForLoggedUserElement checksButtonSignOutVisible() {
        checksElementVisible(buttonSignOut, "Sign Out");
        return this;
    }

    public HeaderForLoggedUserElement checksButtonSignOutNotVisible() {
        checksElementNotVisible(buttonSignOut, "Sign Out");
        return this;
    }
}
