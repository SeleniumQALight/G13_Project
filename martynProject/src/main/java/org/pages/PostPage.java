package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.commonElements.HeaderForLoggedUserElement;

import static org.data.TestData.POST_PAGE_URL;

public class PostPage extends ParentPage{

    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement messagePostCreated;

    @FindBy(xpath = "//div[not(@class='body-content')]/p[not(.//a)]")
    private WebElement uniquePostText;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        isRedirectedToPage(POST_PAGE_URL);
        return this;
    }

    public void checkPostPageIsNotOpened() {
        isNotRedirectedToPage(POST_PAGE_URL);
    }

    public PostPage checkIsPostCreatedSuccessMessageDisplayed(){
        isElementVisible(messagePostCreated);
        checkIsElementEnabled(messagePostCreated);
        return this;
    }

    public PostPage checkTextInSuccessMessage(String textOfMessage) {
        checkTextInElement(messagePostCreated, textOfMessage);
        return this;
    }

    public PostPage checkIsPostUnique(String expectedText) {
        checkTextInElement(uniquePostText, "Is this post unique? : " + expectedText);
        return this;
    }
}
