package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPage {

    @FindBy(id = "post-title")
    private WebElement inputTitleEdit;

    @FindBy(xpath = "//button[text() ='Save Updates']")
    private WebElement buttonSaveUpdates;

    @FindBy(xpath = "//a[contains (text(),'Back to post permalink')]")
    private WebElement linkBackToPostPermalink;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "/post/[a-zA-Z0-9]*/edit";
    }


    public EditPostPage checkIsRedirectToEditPostPage() {
        checkUrlWithPattern();
        //TODO check some unique element on EditPostPage
        return this;
    }

    public EditPostPage enterTextIntoInputTitleEdit(String updatedPostTitle) {
        clearAndEnterTextIntoElement(inputTitleEdit, updatedPostTitle);
        return this;
    }

    public EditPostPage clickOnButtonSaveUpdates() {
        clickOnElement(buttonSaveUpdates);
        return this;
    }

    public PostPage clickOnBackToPostPermalink() {
        clickOnElement(linkBackToPostPermalink);
        return new PostPage(webDriver);
    }
}
