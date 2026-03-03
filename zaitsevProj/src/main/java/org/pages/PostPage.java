package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.elements.HeaderForLoggedUserElement;

public class PostPage extends ParentPage {

    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement messagePostWasCreatedSuccessfully;

    @FindBy(xpath = "//p[contains(text(), 'Is this post unique')]")
    private WebElement stateIsThisPostUnique;

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*";
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    //check success message that post was created
    public PostPage checkPostWasCreatedMessageIsDisplayed() {
        checkElementIsEnabled(messagePostWasCreatedSuccessfully);
        return this;
    }

    public PostPage checkTextInSuccessMessage(String textOfMessage) {
        checkTextInElement(messagePostWasCreatedSuccessfully, textOfMessage);
        return this;
    }

    public PostPage checkUniquePostCheckBoxState(String expectedState){
        String actualText = stateIsThisPostUnique.getText();
        String expectedText = "Is this post unique? : " + expectedState;
        Assert.assertEquals("Unique post checkbox state is correct: ", actualText, expectedText);
        logger.info("Unique post checkbox state is correct: " + expectedState);
        return this;
    }

    public PostPage checkIsRedirectToPostPage() {
        webDriverWait10.until(ExpectedConditions.urlMatches(baseUrl + "/post/[a-zA-Z0-9]*"));
        checkUrlWithPattern();
        // TODO check unique element
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost, "'Delete Post Button'");
        return new MyProfilePage(webDriver);
    }
}
