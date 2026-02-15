package org.pages;

import org.openqa.selenium.WebDriver;
import org.pages.elements.HeaderForLoggedUserElement;

public class PageProvider {
    private WebDriver webDriver;

    public PageProvider(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public LoginPage getLoginPage(){
        return new LoginPage(webDriver);
    }

    public HomePage getHomePage(){
        return new  HomePage(webDriver);
    }

    public MyProfilePage getMyProfilePage(){
        return new MyProfilePage(webDriver);
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement(){
        return new HeaderForLoggedUserElement(webDriver);}

    }
