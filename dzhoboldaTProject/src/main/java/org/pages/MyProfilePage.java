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

    @FindBy(xpath = "//button[text()='Search']")
    private WebElement buttonSearch;

    // 2. Шаблон для динамического локатора (выносим вверх)
//    private final String postTitleLocator = ".//*[text()='%s']";


    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
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
        // Используем шаблон, который уже есть вверху класса
        String xpath = String.format(postTitleLocator, title);
        WebElement post = webDriverWait10.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))
        );

        checkIsElementDisplayed(post);
        return this;
    }


    public PostPage clickOnPostWithTitle(String title) {
        // Формируем XPath из шаблона
        String xpath = String.format(postTitleLocator, title);

        // Используем ожидание из базового класса для надежности
        WebElement post = webDriverWait10.until(
                ExpectedConditions.elementToBeClickable(By.xpath(xpath))
        );

        clickOnElement(post, "Post with title: " + title);
        return new PostPage(webDriver);
    }
}




