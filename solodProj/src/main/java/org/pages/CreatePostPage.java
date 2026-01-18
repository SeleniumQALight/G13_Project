package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {

    @FindBy(id = "post-title")//xpath = "//*[@id='post-title']")
    private WebElement inputTitle;
    @FindBy(name = "body")//xpath = "//*[@name='body']")
    private WebElement inputBody;

    @FindBy(xpath = "//button[text()='Save New Post']")
//xpath = ".//button[@data-original-title='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(tagName = "select") //xpath = ".//select
    private WebElement dropdowAccess;

    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement massagePostWasCreatedSeccessfully;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        //TODO check URL
        //TODO check some unique element on CreatePostPage
        return this;
    }

    public CreatePostPage enterTextInInputTitle(String titleForPost) {
        clearAndEnterTextIntoElement(inputTitle, titleForPost);
        return this;
    }

    public CreatePostPage enterTextInInputBody(String bodyForPost) {
        clearAndEnterTextIntoElement(inputBody, bodyForPost);
        return this;
    }

    public PostPage clickOnButtonSaveNewPost() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }
    public CreatePostPage selectTextInDropDownAccess(String textForSelection) {
        selectTextInDropDown(dropdowAccess, textForSelection);
        return this;
    }


}
