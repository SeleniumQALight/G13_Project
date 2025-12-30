package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends ParentPage{
    private Logger logger = Logger.getLogger(getClass());

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkIsButtonSignOutVisible(){
        Assert.assertTrue("Button SignOut is not visible", isButtonSignOutVisible());
        logger.info("Button SignOut is visible");
    }

     boolean isButtonSignOutVisible() {
         try {
             boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
             logger.info("Element state: + " + state);
             return state;
         } catch (Exception e) {
             logger.info("Element is not found");
             return false;
         }
     }
}
