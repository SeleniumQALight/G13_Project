package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage {
    private String postTitleLocator = "//*[text()='%s']";
    private Logger logger = Logger.getLogger(getClass());
    @FindBy(xpath = "//*[text()='Post successfully deleted.']")
    private WebElement successMessageDelete;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        //TODO check URL and unique element

        return this;
    }

    private List<WebElement> getPostWithByTitle(String postTitle) {
        String locator = String.format(postTitleLocator, postTitle);
        return webDriver.findElements(By.xpath(locator));
    }

    public MyProfilePage checkPostWithTitlePresent(String postTitle, int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts with title " + postTitle + " is not matched",
                expectedNumberOfPosts,
                getPostWithByTitle(postTitle).size());
        logger.info("Number of posts with title " + postTitle + " is expected: " + expectedNumberOfPosts);
        return this;
    }

    public MyProfilePage deletePostWithTitleTillPresent(String postTitle) {
        List<WebElement> postsList = getPostWithByTitle(postTitle);
        final int MAX_POST_COUNT = 100;
        int counter = 0;
        while (!postsList.isEmpty() && counter < MAX_POST_COUNT) {
            clickOnElement(postsList.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checksMessageSuccessDeletePresent();
            logger.info("Deleted post with title: " + postTitle);
            postsList = getPostWithByTitle(postTitle);
            counter++;
        }
        if (counter == MAX_POST_COUNT) {
            logger.warn("Reached max post delete limit of " + MAX_POST_COUNT);
        }
        return this;
    }

    private MyProfilePage checksMessageSuccessDeletePresent() {
        checkIsElementEnabled(successMessageDelete);

        return this;
    }
}
