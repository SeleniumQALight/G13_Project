package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;

public class PostPage extends ParentPage {

    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement messagePostWasCreatedSuccessfully;

    @FindBy(xpath = "//p[contains(text(), 'Is this post unique?')]")
    private WebElement messageWithStateOfCheckbox;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    private Logger logger = Logger.getLogger(getClass());

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        //TODO check URL
        //TODO check some unique element on PostPage
        return this;
    }

    public PostPage checkPostWasCreatedMessageIsDisplayed() {
        checkIsElementEnabled(messagePostWasCreatedSuccessfully);
        return this;
    }


    public PostPage checkTextInSuccessMessage(String textOfMessage) {
        checkTextInElement(messagePostWasCreatedSuccessfully, textOfMessage);
        return this;
    }

    public PostPage checkStateOfCheckboxInCreatedPost(String stateOfCheckbox) {
        if (stateOfCheckbox == null || stateOfCheckbox.trim().isEmpty()) {
            logger.info("State of checkbox is null or empty");
            throw new AssertionError("State of checkbox is null or empty");
        }

        String expectedState = stateOfCheckbox.trim().equalsIgnoreCase("check") ? "yes" : "no";
        String expectedMessage = "Is this post unique? : " + expectedState;
        checkTextInElement(messageWithStateOfCheckbox, expectedMessage);
        return this;
    }

}
