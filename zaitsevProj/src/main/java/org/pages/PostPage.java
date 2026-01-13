package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;

public class PostPage extends ParentPage {

    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement messagePostWasCreatedSuccessfully;

    @FindBy(xpath = "//p[contains(text(), 'Is this post unique')]")
    private WebElement stateIsThisPostUnique;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public PostPage checkIsRedirectToCreatePostPage() {
        //TODO check URL
        //TODO check some unique element on the page
        return this;
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

    public PostPage checkUniquePostCheckBoxState(){
        String text = stateIsThisPostUnique.getText();
        if (text.toLowerCase().contains("yes")){
            logger.info("Checkbox was selected");
        }else if (text.toLowerCase().contains("no")){
            logger.info("Checkbox was not selected");
        }else {
            logger.info("Unexpected text for unique post: " + text);
        }
        return this;
    }
}
