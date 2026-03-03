package org.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {
    @FindBy(id = "post-title") // xpath = "//input[@id='post-title']"
    private WebElement inputTitle;

    @FindBy(id = "post-body")
    private WebElement inputBody;

    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement clickOnSaveNewPostButton;

    @FindBy(xpath = "//select")
    private WebElement dropdownAccess;

    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement checkbox;

    public CreatePostPage(org.openqa.selenium.WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/create-post";
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        checkUrl();
        //TODO check some unique element on CreatePostPage
        return this;
    }

    public CreatePostPage enterTextIntoInputTitle(String titleForPost) {
        clearAndEnterTextIntoElement(inputTitle, titleForPost);
        return this;
    }

    public CreatePostPage enterTextIntoInputBody(String bodyForPost) {
        clearAndEnterTextIntoElement(inputBody, bodyForPost);
        return this;
    }

    public CreatePostPage setStateToCheckbox(String state) {
        setCheckboxState(checkbox, state);
        return this;
    }

    public PostPage clickOnSaveNewPostButton() {
        clickOnElement(clickOnSaveNewPostButton);
        return new PostPage(webDriver);
    }

    public CreatePostPage selectTextInDropDownAccess(String textForSelection) {
        selectTextInDropDown(dropdownAccess, textForSelection);
        return this;
    }

    public CreatePostPage selectTextInDropDownViaValue(String textForSelection) {
        selectValueInDropDown(dropdownAccess, textForSelection);
        return this;
    }
}
