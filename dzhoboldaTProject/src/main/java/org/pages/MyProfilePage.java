package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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
    String getRelativeUrl() {
        return ".*/profile/[a-zA-Z0-9]*";
    }

    public MyProfilePage checkIsRedirectToMyProfilePage(){
        checkUrlWithPattern();
        return this;
    }

    private List<WebElement>getPostElementsByTitle(String postTitle){
        String locator = String.format(postTitleLocator, postTitle);
        return webDriver.findElements(By.xpath(locator));
    }

    public MyProfilePage checkPostWithTitelProfile(String postTitle, int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts with title " + postTitle + " is not matched",
                expectedNumberOfPosts,
                getPostElementsByTitle(postTitle).size()
        );
        logger.info("Number of posts with title " + postTitle + " is matched" + expectedNumberOfPosts);

        return this;
    }

    public MyProfilePage deletePosTitlePresent(String postTitle) {
        List<WebElement> postsList = getPostElementsByTitle(postTitle);
        final int MAX_Post_COUNT = 100;
        int counter = 0;
        while (!postsList.isEmpty() && (counter < MAX_Post_COUNT)) {
            clickOnElement(postsList.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsMessageSuccessDeletePresent();
            logger.info("Deleted post with title " + postTitle + "");
            postsList = getPostElementsByTitle(postTitle);
            counter++;
        }
        if (counter == MAX_Post_COUNT) {
            logger.warn("Reached max delete attempts for posts with title " + postTitle + ". There might be more posts remaining.");
        }
        return this;
    }

    private MyProfilePage checkIsMessageSuccessDeletePresent() {
        checkIsElementEnabled(successMessageDelete);
        return this;
    }

    public MyProfilePage checkPostWithTitleVisible(String title) {
        checkIsElementDisplayed(
                webDriver.findElement(By.xpath("//a[text()='" + title + "']"))
        );
        return this;
    }

    public PostPage clickOnPostWithTitle(String title) {
        WebElement post = webDriver.findElement(By.xpath(String.format(".//*[text()='%s']", title)));;
        post.click();
        return new PostPage(webDriver); // возвращаем PostPage для редактирования
    }

}
