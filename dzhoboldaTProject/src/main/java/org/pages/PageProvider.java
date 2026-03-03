package org.pages;

import org.openqa.selenium.WebDriver;

public class PageProvider {
    private final WebDriver webDriver;
    private LoginPage loginPage;
    private HomePage homePage;
    private PostPage postPage;
    private CreatePostPage createPostPage;

    public PageProvider(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(webDriver);
        }
        return loginPage;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage(webDriver);
        }
        return homePage;
    }

    public MyProfilePage getMyProfilePage() {
        return new MyProfilePage(webDriver);
    }

    public PostPage getPostPage() {
        if (postPage == null) {
            postPage = new PostPage(webDriver);
        }
        return postPage;
    }

    public CreatePostPage getCreatePostPage() {
        if (createPostPage == null) {
            createPostPage = new CreatePostPage(webDriver);
        }
        return createPostPage;
    }
}
