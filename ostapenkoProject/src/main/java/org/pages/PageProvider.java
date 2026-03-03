package org.pages;

import org.openqa.selenium.WebDriver;

public class PageProvider {
    private WebDriver webDriver;

    public PageProvider(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public LoginPage getLoginPage() {
        return new LoginPage(webDriver);
    }

    private HomePage homePage;

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
        return new PostPage(webDriver);
    }
}