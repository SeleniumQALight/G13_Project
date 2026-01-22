package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.elements.HeaderForLoggedUserElement;

import java.time.Duration;

public class PostPage extends ParentPage {


    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement messagePostWasCreatedSuccessfully;

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    @FindBy(xpath = "//a[text()='Edit']")
    private WebElement buttonEditPost;

    @FindBy(xpath = "//input[@name='title']")
    private WebElement inputTitle;

    @FindBy(xpath = "//button[text()='Save Updates']")
    private WebElement buttonSaveUpdates;



    public PostPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this); // инициализация @FindBy элементов
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        // TODO Check URL
        // TODO Check unique element
        return this;
    }

    //check successMessagePostCreatedVisible
    public PostPage checkPostWasCreatedMessagesDisplayed() {
        checkIsElementEnabled(messagePostWasCreatedSuccessfully);
        return this;
    }

    public PostPage checkTextInSuccessMessage(String textOfMessage) {
        checkTextInElement(messagePostWasCreatedSuccessfully, textOfMessage);
        return this;
    }


    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost);
        return new MyProfilePage(webDriver);
    }


    public PostPage editPostTitle(String newTitle) {
        clickOnElement(buttonEditPost);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(inputTitle));
        clearAndEnterTextIntoElement(inputTitle, newTitle);
        clickOnElement(buttonSaveUpdates);
        return this;
    }

    public PostPage clickOnSavedPost(String title) {
        // Ждем, пока пост появится на странице (явное ожидание)
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement post = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[text()='" + title + "']")
        ));
        post.click();
        return new PostPage(webDriver);
    }
}
