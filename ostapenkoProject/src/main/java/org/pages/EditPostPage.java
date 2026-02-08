package org.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPage{

    @Override
    protected String getRelativeUrl() {
        return "/edit";
    }

    @FindBy(xpath = "//button[text()='Save Updates']")
    private WebElement clickOnSaveUpdatesButton;

    @FindBy(id = "/post/[a-zA-Z0-9]*/edit")
    private WebElement newTitleForPost;

    @FindBy(xpath = "//div[text()='Post successfully updated.']")
    private WebElement messagePostWasEditedSuccessfully;

    public EditPostPage(org.openqa.selenium.WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*/edit";
    }

    public EditPostPage checkIsRedirectToEditPostPage() {
        checkUrlWithPattern();
        //TODO check some unique element on EditPostPage
        return this;
    }

    public EditPostPage clickOnSaveUpdatesButton() {
        clickOnElement(clickOnSaveUpdatesButton);
        return this;
    }

    public EditPostPage enterTextIntoInputTitle(String updatedTitle) {
            clearAndEnterTextIntoElement(newTitleForPost, updatedTitle);
            return this;
    }

    public EditPostPage checkPostWasEditedMessageIsDisplayed() {
        checkElementIsEnabled(messagePostWasEditedSuccessfully);
        return this;
    }
}