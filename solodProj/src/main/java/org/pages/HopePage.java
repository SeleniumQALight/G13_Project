package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HopePage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    public HopePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkIsButtonSingOutVisible() {
        Assert.assertTrue("Button SingOut is not visible", isButtonSingOutVisible());
        logger.info("Button SingOut is visible");
    }

    public boolean isButtonSingOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info("Element state" + state);
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;

        }
    }

}
