package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage {

    private Logger logger = Logger.getLogger(getClass());

    //параматрезований локатор - стрінга
    private String postTitleLocator = "//*[text()='%s']";

    @FindBy(xpath = "//*[text()='Post successfully deleted.']")
    private WebElement successMessageDelete;

    public MyProfilePage(org.openqa.selenium.WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "/profile/[a-zA-Z0-9]*";
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
        //TODO check URL and unique element on MyProfilePage
        return this;
    }

    private List<WebElement> getPostElementsByTitle(String postTitle) {
        String locator = String.format(postTitleLocator, postTitle);
        return webDriver.findElements(By.xpath(locator));//вертає список елементів
    }

    public MyProfilePage checkIsPostWithTitlePresent(String postTitle, int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts with title '" + postTitle + "' is not as expected",
                expectedNumberOfPosts,
                getPostElementsByTitle(postTitle).size());
        logger.info("Post with title '" + postTitle + "' is as expected: " + expectedNumberOfPosts + " times");
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
                    .checkIsMessageSuccessDeletePostPresent();
            logger.info("Post with title '" + postTitle + "' was deleted");
            postsList = getPostElementsByTitle(postTitle);
            counter++;
        }

        if (counter >= MAX_POST_COUNT) {
            logger.warn("Reached maximum post deletion limit of " + MAX_POST_COUNT + ". Stopping further deletions to prevent infinite loop.");
        }
        return this;

    }

        private MyProfilePage checkIsMessageSuccessDeletePostPresent () {
            checkIsElementEnabled(successMessageDelete);
            return this;
        }

    public PostPage clickOnPostWithTitle(String postTitle) {
        List<WebElement> postsList = getPostElementsByTitle(postTitle);
        if (!postsList.isEmpty()) {
            clickOnElement(postsList.get(0));
            logger.info("Clicked on post with title '" + postTitle + "'");
            return new PostPage(webDriver);
        } else {
            String errorMessage = "Post with title '" + postTitle + "' not found.";
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}

