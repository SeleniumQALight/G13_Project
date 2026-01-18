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
    private WebElement successMessageDelete;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        // TODO check URL and some unique element
        return this;
    }

    private List<WebElement> getPostListByTitle(String postTitle) {
        String locator = String.format(postTitleLocator, postTitle);
        return webDriver.findElements(By.xpath(locator));
    }

    public MyProfilePage checkPostWithTitle(String postTitle, int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts with title " + postTitle + "",
                expectedNumberOfPosts, getPostListByTitle(postTitle).size());
        logger.info("Number of posts with title " + postTitle + " is expected: " + expectedNumberOfPosts);
        return this;
    }

    public MyProfilePage deletePostWithTitleWhilePresent(String postTitle) {
        List<WebElement> postsList = getPostListByTitle(postTitle);
        final int MAX_POST_COUNT = 100; // postsList.size()
        int counter = 0;
        while (!postsList.isEmpty() && counter < MAX_POST_COUNT) {
            clickOnElement(postsList.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeletePostButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkMessagePostWasDeleted();
            logger.info("Post with title '" + postTitle + "' was deleted");
            postsList = getPostListByTitle(postTitle);
            counter++;
        }

        if (counter == MAX_POST_COUNT) {
            logger.warn("Reached maximum deletion attempts for post with title: " + postTitle);

        }
        return this;
    }

    private MyProfilePage checkMessagePostWasDeleted() {
        checkElementIsEnabled(successMessageDelete);
        return this;
    }
}
