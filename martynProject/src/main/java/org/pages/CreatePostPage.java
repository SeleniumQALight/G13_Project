package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.data.TestData.CREATE_POST_PAGE_URL;

public class CreatePostPage extends ParentPage{
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(id="post-title")
    private WebElement inputPostTitle;

    @FindBy(id="post-body")
    private WebElement inputBody;

    @FindBy(xpath=".//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(tagName="select")
    private WebElement dropdownAccess;

    @FindBy(xpath = ".//input[@name='uniquePost']")
    private WebElement checkboxUniquePost;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        isRedirectedToPage(CREATE_POST_PAGE_URL);
        isElementVisible(buttonSaveNewPost);
        return this;
    }

    public CreatePostPage checkIsInputTitleEnabled(boolean shouldBeEnabled) {
        if (shouldBeEnabled) {
            Assert.assertTrue("Input Title isn't enabled", isElementEnabled(inputPostTitle));
            logger.info("Input Title is enabled");
        } else {
            Assert.assertFalse("Input Title is enabled", isElementEnabled(inputPostTitle));
            logger.info("Input Title isn't enabled");
        }
        return this;
    }

    public CreatePostPage checkIsInputBodyEnabled(boolean shouldBeEnabled) {
        if (shouldBeEnabled) {
            Assert.assertTrue("Input Body isn't enabled", isElementEnabled(inputBody));
            logger.info("Input Body is enabled");
        } else {
            Assert.assertFalse("Input Body is enabled", isElementEnabled(inputBody));
            logger.info("Input Body isn't enabled");
        }
        return this;
    }

    public CreatePostPage checkIsButtonSaveNewPostEnabled(boolean shouldBeEnabled) {
        if (shouldBeEnabled) {
            Assert.assertTrue("Button Save New Post isn't enabled", isElementEnabled(buttonSaveNewPost));
            logger.info("Button Save New Post is enabled");
        } else {
            Assert.assertFalse("Button Save New Post is enabled", isElementEnabled(buttonSaveNewPost));
            logger.info("Button Save New Post isn't enabled");
        }
        return this;
    }

    public CreatePostPage enterTextIntoInputTitle(String titleForPost) {
        clearAndEnterTextIntoElement(inputPostTitle, titleForPost);
        return this;
    }

    public CreatePostPage enterTextIntoInputBody(String bodyForPost) {
        clearAndEnterTextIntoElement(inputBody, bodyForPost);
        return this;
    }

    public CreatePostPage selectTextInDropdownAccesss(String textForSelection) {
        selectTextInDropDown(dropdownAccess, textForSelection);
        clickOnElement(dropdownAccess);
        return this;
    }

    public CreatePostPage checkCheckboxUniquePost() {
        setCheckboxState(checkboxUniquePost, "check");
        return this;
    }

    public CreatePostPage uncheckCheckboxUniquePost() {
        setCheckboxState(checkboxUniquePost, "uncheck");
        return this;
    }

    public void clickOnButtonSaveNewPost() {
        clickOnElement(buttonSaveNewPost);
    }
}
