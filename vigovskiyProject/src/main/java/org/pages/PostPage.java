package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;

public class PostPage extends ParentPage {
    @FindBy(xpath = "//*[@class=\"alert alert-success text-center\"]")
    private WebElement messagePostWasCreatedSuccessfully;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }
    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        //TODO check URL
        //TODO check some unique element on the page
        return this;
    }

    //check successful post creation
    public PostPage checkPostWasCreatedMessageIsDisplayed(String titleForPost) {
        checkIsElementEnabled(messagePostWasCreatedSuccessfully);
        return this;
    }

    public PostPage checkTextInSuccessMessage(String textOfMessage) {
        checkTextInElement(messagePostWasCreatedSuccessfully, textOfMessage);
        return this;
    }
}
