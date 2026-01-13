package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;

public class PostPage extends ParentPage{
    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement massagePostWasCreatedSeccessfully;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }


    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }
    public PostPage checkIsRedirectToPostPage() {
        //TODO check URL
        //TODO check some unique element on PostPage
        return this;
    }

    public PostPage checkPostWasCreatedMassagelsDisplay() {
        checkIsElementEnabled(massagePostWasCreatedSeccessfully);
        return this;

    }

    public PostPage checkTextInSuccessMessage(String textMassage) {
        checkTextInElement(massagePostWasCreatedSeccessfully, textMassage);
        return this;
    }
}
