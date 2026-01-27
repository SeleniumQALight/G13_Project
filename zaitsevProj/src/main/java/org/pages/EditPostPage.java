package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends PostPage{
    @FindBy(xpath = "//a[@data-placement='top']")
    private WebElement buttonEditPost;

    @FindBy(xpath = "//input[@name='title']")
    private WebElement inputTitle;

    @FindBy(xpath = "//button[text()='Save Updates']")
    private WebElement buttonSaveUpdates;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public EditPostPage checkIsRedirectToEditPostPage() {
        // TODO Check URL
        // TODO check some unique element on EditPostPage
        return this;
    }

    public EditPostPage clickOnEditButton() {
        clickOnElement(buttonEditPost);
        return this;
    }

    public EditPostPage enterTextIntoInputTitle(String editedTitle) {
        clearAndEnterTextIntoElement(inputTitle, editedTitle);
        return this;
    }

    public EditPostPage clickOnSaveUpdatesButton() {
        clickOnElement(buttonSaveUpdates);
        return this;
    }
}
