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

    public MyProfilePage checkIsRedirectToMyProfilePage(){
        //TODO check URL
        return this;
    }

    private List<WebElement>getPostByTitle(String postTitle){
        String locator = String.format(postTitleLocator, postTitle);
        return webDriver.findElements(By.xpath(locator));
    }

    public MyProfilePage checkPostWithTitlePresent(String postTitle, int expectedNumberOfPosts) {
        Assert.assertEquals(
                "Number of post with title '" + postTitle + "'",
                expectedNumberOfPosts,
                getPostByTitle(postTitle).size()
        );
        logger.info("Number of post with title '" + postTitle + "' is equal to " + expectedNumberOfPosts);
        return this;
    }

    public MyProfilePage deletePostsTillPresent(String postTitle) {
        List<WebElement> postsList = getPostByTitle(postTitle);
        final int MAX_POST_COUNT = 100;
        int counter = 0;
        while (!postsList.isEmpty() && (counter < MAX_POST_COUNT)) {
            clickOnElement(postsList.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectToCreatePostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsMessageSuccessDeletePresent();
            logger.info("Deleted post with title '" + postTitle + "'");
            postsList= getPostByTitle(postTitle);
            counter++;
        }

        if (counter == MAX_POST_COUNT) {
            logger.warn("Reached maximum post deletion attempts for poststittle: " + postTitle + ". There might be more posts remaining.");
        }
        return this;
    }
    private MyProfilePage checkIsMessageSuccessDeletePresent() {
        checkElementIsEnabled(successDeleteMessage);
        return this;
    }
}
