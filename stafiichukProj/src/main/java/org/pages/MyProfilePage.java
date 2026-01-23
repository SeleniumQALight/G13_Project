package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage{
    private Logger logger = Logger.getLogger(getClass());

    private String postTitleLocator = "//*[text()='%s']";

    @FindBy(xpath = "//*[text()='Post successfully deleted.']")
    private WebElement successMessageDelete;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage(){
        // TODO check URL and unique elements
        return this;
    }

    private List<WebElement> getPostElementByTitle(String postTitle){
        String locator = String.format(postTitleLocator, postTitle);
        return webDriver.findElements(org.openqa.selenium.By.xpath(locator));
    }

    public MyProfilePage checkPostWithTitlePresent(String postTitle, int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts with title '" + postTitle + "",
                expectedNumberOfPosts,
                getPostElementByTitle(postTitle).size());
        logger.info("Number of posts with title" + postTitle + "' is as expected" + expectedNumberOfPosts);
        return this;
    }

    public MyProfilePage deletePostsTillPresent(String postTitle) {
        List<WebElement> postsList = getPostElementByTitle(postTitle);
        final int MAX_POST_COUNT = 100; // postsList.size();
        int counter = 0;
        while (!postsList.isEmpty() && (counter < MAX_POST_COUNT))  {
            clickOnElement(postsList.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsMessageSuccessDeletePresent();
            logger.info("Deleted one post with title '" + postTitle + "'");
            postsList = getPostElementByTitle(postTitle);
            counter++;

        }
        if (counter >= MAX_POST_COUNT) {
            logger.warn("Reached maximum post deletion attempts for posts with title '" + postTitle + "'. There might be more posts remaining.");
        }

        return this;
    }

    private MyProfilePage checkIsMessageSuccessDeletePresent() {
        checkIsElementEnabled(successMessageDelete);
        return this;
    }
}
