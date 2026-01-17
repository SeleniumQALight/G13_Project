package org.pages;

import org.openqa.selenium.WebDriver;

public class PageProvider {
    private final WebDriver webDriver;
    private LoginPage loginPage;
    private HomePage homePage;

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

}
