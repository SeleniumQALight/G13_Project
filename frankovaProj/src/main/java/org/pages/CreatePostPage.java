package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {

    @FindBy(id = "post-title")
    private WebElement inputTitle;

    @FindBy(name = "body")
    private WebElement inputBody;

    @FindBy(xpath = "//button[text() ='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(tagName = "select")
    private WebElement dropdownAccess;

    @FindBy(name="uniquePost")
        private WebElement checkboxUniquePost;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    private Logger logger = Logger.getLogger(getClass());

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        //TODO check URL
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

    public PostPage clickOnButtonSaveNewPost() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreatePostPage selectTextInDropdownAccess(String textForSelection) {
        selectTextInDropDown(dropdownAccess, textForSelection);
        return this;
    }

    public CreatePostPage enterStateForCheckboxUniquePost(String state) {
        enterStateForCheckbox(state, checkboxUniquePost);
        return this;
    }

}
