package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    private String postTitleLocator = "//*[text()='%s']";

    @FindBy(xpath = "//*[text()='Post successfully deleted.']")
    private WebElement successDeleteMessage;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        //TODO CHECK URL and unique element
        return this;
    }


    private List<WebElement> getPostElementsByTitle(String postTitle) {
        String locator = String.format(postTitleLocator, postTitle);
        return webDriver.findElements(By.xpath(locator));
    }

    public MyProfilePage checkPostWithTitlePresent(String postTitle, int expectedNumberOfPosts) {
        Assert.assertEquals(
                "Number of posts with title '" + postTitle + "' is not as expected",
                expectedNumberOfPosts,
                getPostElementsByTitle(postTitle).size()
        );
        logger.info("Number of posts with title '" + postTitle + "' is as expected: " + expectedNumberOfPosts);
        return this;
    }

    public MyProfilePage deletePostsTillPresent(String postTitle) {
        List<WebElement> postsList = getPostElementsByTitle(postTitle);
        final int MAX_POST_COUNT = 100;
        int counter = 0;
        while (!postsList.isEmpty() && (counter < MAX_POST_COUNT)) {
            clickOnElement(postsList.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkisMessageSuccessDeletePresent();
            logger.info("Deleted one post with title '" + postTitle + "'");
            postsList = getPostElementsByTitle(postTitle);
            counter++;
        }
        if (counter == MAX_POST_COUNT) {
            logger.warn("Reached maximum deletion attempts for posts with little " + postTitle + "'. There might be more posts remaining.");
        }
        return this;
    }

    private MyProfilePage checkisMessageSuccessDeletePresent() {
        checkIsElementEnabled(successDeleteMessage);
        return this;
    }
}
