package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {

    @FindBy(id = "post-title")
    private WebElement inputTitle;

    @FindBy(name = "body")
    private WebElement inputBody;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(tagName = "select")
    private WebElement dropdownAccess;

    @FindBy(xpath = ".//input[@type='checkbox']")
    private WebElement checkboxUniquePost;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/create-post";
    }

    public CreatePostPage checkIsRedirectToCreateNewPostPage() {
        checkUrl();
        //TODO check some unique element on CreateNewPostPage
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

    public PostPage clickOnSaveNewPostButton() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreatePostPage selectTextInDropdownAccess (String textForSelection){
        selectTextInDropDown(dropdownAccess, textForSelection);
        return this;
    }

    public CreatePostPage setCheckboxUniquePostSelected(){
        clickOnElement(checkboxUniquePost);
        return this;
    }

    public CreatePostPage setCheckboxIsUniqueState(String status) {
        setStatusToCheckbox(checkboxUniquePost, status);
        return this;
    }
}
