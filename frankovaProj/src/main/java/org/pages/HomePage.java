package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage{
    private Logger logger= Logger.getLogger(getClass());

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//a[@href='/create-post']")
    private WebElement buttonCreatePost;


    public void checkIsButtonSignOutVisible(){
        checkIsElementVisible(buttonSignOut);
    }

    public void checkIsNotButtonSignOutVisible(){
        checkIsNotElementVisible(buttonSignOut);
    }

    public  void  checkIsButtonCreatePostVisible(){
        checkIsElementVisible(buttonCreatePost);
    }

}
