package org.pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.ParentPage;
import org.pages.PostPage;

public class EditPostPage extends ParentPage {
    @FindBy(xpath = "//input[@name='title']")
    private WebElement inputTitle;

    @FindBy(xpath = "//button[text()='Save Updates']")
    private WebElement buttonSaveUpdates;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*/edit"; // URL страницы редактирования
    }

    public EditPostPage checkIsRedirectToEditPostPage() {
        checkUrlWithPattern();
        return this;
    }

    public EditPostPage enterNewTitle(String newTitle) {
        clearAndEnterTextIntoElement(inputTitle, newTitle);
        return this;
    }

    public PostPage clickOnSaveUpdatesButton() {
        clickOnElement(buttonSaveUpdates, "Save Updates Button");
        return new PostPage(webDriver); // После сохранения обычно кидает обратно на PostPage
    }

}
