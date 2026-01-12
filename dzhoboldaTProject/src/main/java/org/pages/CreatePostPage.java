package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class CreatePostPage extends ParentPage{
    private final Logger logger = Logger.getLogger(getClass());

    @FindBy(id = "post-title")
    private WebElement inputTitle;
    @FindBy(name = "body")
    private WebElement inputBody;
    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;
    @FindBy(tagName = "select")
    private WebElement dropdownAccess;


//    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
//    private WebElement messagePostWasCreatedSuccessfully;;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }


    public CreatePostPage checkIsRedirectToCreatePostPage(){
        // TODO Check URL
        // TODO Check unique element
        return this;
    }

    public CreatePostPage enterTextIntoInputTitle(String titleForPost){
        clearAndEnterTextIntoElement(inputTitle, titleForPost);
        return this;
    }
    public CreatePostPage enterTextIntoInputBody(String bodyForPost){
        clearAndEnterTextIntoElement(inputBody, bodyForPost);
        return this;
    }

    public PostPage clickOnSaveNewPostButton() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreatePostPage selectTextInDropDownAccess(String text){
        selectTextInDropDown(dropdownAccess,text);
        return this;
    }

}
