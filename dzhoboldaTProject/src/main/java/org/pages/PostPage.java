package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.elements.EditPostPage;
import org.pages.elements.HeaderForLoggedUserElement;

import java.time.Duration;

public class PostPage extends ParentPage {
    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement messagePostWasCreatedSuccessfully;
    @FindBy(xpath = ".//*[contains(text(), 'Is this post unique?')]")
    private WebElement isPostUniqueText;

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    @FindBy(xpath = "//a[@data-original-title='Edit']")
    private WebElement buttonEditPost;

    @FindBy(xpath = "//input[@name='title']")
    private WebElement inputTitle;

    @FindBy(xpath = "//button[text()='Save Updates']")
    private WebElement buttonSaveUpdates;

    private final String postTitleLocator = "//a[text()='%s']";





    public PostPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this); // инициализация @FindBy элементов
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*";
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPattern();
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

    public PostPage checkIsPostUnique(String expectedText) {
        checkTextInElement(isPostUniqueText, "Is this post unique? : " + expectedText);
        return this;
    }


    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDeletePost, "Delete Post Button");
        return new MyProfilePage(webDriver);
    }



    // Метод с динамическим локатором, который берет шаблон сверху
    public PostPage clickOnSavedPost(String title) {
        // Подставляем title в наш шаблон %s
        String xpath = String.format(postTitleLocator, title);
        WebElement post = webDriverWait10.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        post.click();
        return new PostPage(webDriver);
    }
    public EditPostPage clickOnEditButton() {
        webDriverWait10.until(ExpectedConditions.elementToBeClickable(buttonEditPost));
        clickOnElement(buttonEditPost, "Edit Post Button");
        return new EditPostPage(webDriver); // Метод возвращает НОВУЮ страницу
    }
}
