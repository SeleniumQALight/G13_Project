package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPage{

    @FindBy(id = "post-title")
    private WebElement inputTitle;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    private WebElement buttonSaveUpdates;

    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public EditPostPage enterTextIntoInputTitle(String title) {
        clearAndEnterTextIntoElement(inputTitle, title);
        return this;
    }

    public PostPage clickOnSaveChangesButton() {
        clickOnElement(buttonSaveUpdates);
        return new PostPage(webDriver); // Після збереження сайт зазвичай повертає на сторінку поста
    }

    public EditPostPage checkIsRedirectToEditPostPage() {
        // Тут можна додати перевірку, що в URL є слово "/edit"
        return this;
    }

    public EditPostPage checkPostWasUpdatedMessageIsDisplayed() {
        checkIsElementEnabled(successMessage);
        return this;
    }

    public EditPostPage checkTextInSuccessMessage(String expectedText) {
        checkTextInElement(successMessage, expectedText);
        return this;
    }
}
