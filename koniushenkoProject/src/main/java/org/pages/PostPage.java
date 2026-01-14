package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.elements.HederForLoggedUserElement;

public class PostPage extends ParentPage {
    @FindBy (xpath = "//*[@class='alert alert-success text-center']")
    private WebElement messagePostWasCreatedSuccessfully;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HederForLoggedUserElement getHederForLoggedUserElement() {
        return new HederForLoggedUserElement(webDriver);
    }

    public PostPage chechIsRedirectToPostPage() {
        //TODO Check URL
        //TODO check some unique element on Post Page
        return this;
    }

    public PostPage checkPostWasCreatedMessageIsDisplayed() {
        checkIsElementEnabled(messagePostWasCreatedSuccessfully);
        return this;
    }

    public PostPage checkTextInSuccessMessage(String textOfMessage) {
        checkTextInElement(messagePostWasCreatedSuccessfully, textOfMessage);
        return this;
    }
}
