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
    private Logger logger= Logger.getLogger(getClass());
    @FindBy (xpath = "//*[text()='Post successfully deleted.']")
    private WebElement successMessageDelete;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/profile/[a-zA-Z-9]*";
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
       // TODO  checkUrlContainsSubstring("my-profile");
        return this;
    }

    private List<WebElement> getPostElementsByTitle(String postTitle) {
        String locator = String.format(postTitleLocator, postTitle);
        return webDriver.findElements(By.xpath(locator));
    }

    public MyProfilePage checkPostWithTitlelPresent(String postTitle, int expectedNumberOfPosts) {
        Assert.assertEquals(
                "Number of posts with the title '" + postTitle + "' is not as expected.",
                expectedNumberOfPosts,
                getPostElementsByTitle(postTitle).size()
        );
        logger.info("Number of posts with the title '" + postTitle + "' is as expected: " + expectedNumberOfPosts);
        return this;
    }

    public MyProfilePage deletePostsTillPresent(String postTitle) {
        List<WebElement> postList = getPostElementsByTitle(postTitle);
        final int MAX_POST_COUNT = 100; //postList.size();
        int counter = 0;
        while (!postList.isEmpty()&&(counter < MAX_POST_COUNT)) {
            counter++;
            clickOnElement(postList.get(0));
            new PostPage(webDriver)
                    .chechIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsMessageSuccessDeletePresent();
            logger.info("Delete post with the title '" + postTitle + "'");
            postList = getPostElementsByTitle(postTitle);
        }
        if (counter == MAX_POST_COUNT) {
            logger.warn("Reached maximum deletion limit of " + MAX_POST_COUNT + " for posts with the title '" + postTitle + "'. Possible infinite loop avoided.");
        }
        return this;
    }

    private MyProfilePage checkIsMessageSuccessDeletePresent() {
        checkIsElementEnabled(successMessageDelete);
        return this;
    }
}
