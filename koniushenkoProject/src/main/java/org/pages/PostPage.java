package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.elements.HederForLoggedUserElement;

public class PostPage extends ParentPage {
    @FindBy (xpath = "//*[@class='alert alert-success text-center']")
    private WebElement messagePostWasCreatedSuccessfully;
    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*";
    }

    public HederForLoggedUserElement getHederForLoggedUserElement() {
        return new HederForLoggedUserElement(webDriver);
    }

    public PostPage chechIsRedirectToPostPage() {
        checkUrlWithPattern();
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

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost, "Delete Post Button");
        return new MyProfilePage(webDriver);
    }
}
