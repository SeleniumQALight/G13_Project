package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.pages.elements.HeaderForLoggedUserElement;

public class PostPage extends ParentPage {
    @FindBy(xpath = "//*[@class='alert alert-success text-center']")

    private WebElement messagePostWasCreatedSuccessfully;
    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;


    public PostPage(WebDriver webDriver) {
        super(webDriver);
//        PageFactory.initElements(webDriver, this); // инициализация @FindBy элементов
    }

    @Override
    String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*";
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPattern();
        // TODO Check unique element
        return this;
    }

    //check successMessagePostCreatedVisible
    public PostPage checkPostWasCreatedMessagesDisplayed() {
        checkIsElementEnabled(messagePostWasCreatedSuccessfully);
        return this;
    }

    public PostPage checkTextInSuccessMessage(String textOfMessage) {
        checkTextInElement(messagePostWasCreatedSuccessfully, textOfMessage);
        return this;
    }


    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost);
        return new MyProfilePage(webDriver);
    }
}
