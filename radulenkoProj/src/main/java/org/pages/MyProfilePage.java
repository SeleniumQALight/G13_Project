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

    @Override
    protected String getRelativeURL() {
        return "/profile/[a-zA-Z0-9]*";
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
        // TODO check unique elements
        return this;
    }

    private List<WebElement> getPostElementsByTitle(String postTitle) {
        String locator = String.format(postTitleLocator, postTitle);
        return webDriver.findElements(By.xpath(locator));
    }

    public MyProfilePage checkPostWithTitleIPresent(String postTitle, int expectedNumberOfPosts) {
        Assert.assertEquals(
                "Number of posts with title '" + postTitle + "'",
                expectedNumberOfPosts,
                getPostElementsByTitle(postTitle).size()
        );
        logger.info("Number of posts with title '" + postTitle + "' is as expected: " + expectedNumberOfPosts);
        return this;
    }

    public MyProfilePage deletePostsTillPresent(String postTitle) {
        List<WebElement> postsList = getPostElementsByTitle(postTitle);
        final int MAX_POST_COUNT = 100; // postsList.size()
        int counter = 0;
        while (!postsList.isEmpty() && (counter < MAX_POST_COUNT)) {
            clickOnElement(postsList.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsMessageSuccessDeletePresent();
            logger.info("Deleted one post with title '" + postTitle + "'");
            postsList = getPostElementsByTitle(postTitle);
            counter++;
        }

        if (counter == MAX_POST_COUNT) {
            logger.warn("Reached maximum deletion attempts for posts with title '" + postTitle + "'. There might be more posts remaining.");
        }
        return this;
    }

    private MyProfilePage checkIsMessageSuccessDeletePresent() {
        checkIsElementEnabled(successMessageDelete);
        return this;
    }
}
