package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;

public class PostPage extends ParentPage {

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement messagePostWasCreatedSuccessfully;

    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "/post/[a-zA-Z0-9]*";
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public PostPage checkIsRedirectToCreatePostPage() {
        checkUrlWithPattern();
        //TODO check some unique element on the page
        return this;
    }

    //check success message that post was created
    public PostPage checkPostWasCreatedMessageIsDisplayed() {
        checkElementIsEnabled(messagePostWasCreatedSuccessfully);
        return this;
    }

    public PostPage checkTextInSuccessMessage(String textOfMessage) {
        checkTextInElement(messagePostWasCreatedSuccessfully, textOfMessage);
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost, "'Delete post button'");
        return new MyProfilePage(webDriver);
    }
}
