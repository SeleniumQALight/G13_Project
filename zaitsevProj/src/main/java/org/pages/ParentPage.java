package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.utils.ConfigProvider;

import java.util.Set;

abstract public class ParentPage extends CommonActionsWithElement {

    String environment = System.getProperty("env", "aqa");

  //  protected String baseUrl = "https://"+environment+"-complexapp.onrender.com";
    protected String baseUrl = ConfigProvider.configProperties.base_url().replace("[env]", environment);
    private Logger logger = Logger.getLogger(getClass());
    protected String baseUrl = "https://aqa-complexapp.onrender.com";
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract protected String getRelativeUrl();

    protected void checkUrl(){
        Assert.assertEquals("URL is not as expected",
                baseUrl + getRelativeUrl(),
                webDriver.getCurrentUrl()
        );
    }

    // метод по перевірці чи відкрита потрібна сторінка по патерну
    //https://aqa-complexapp.onrender.com/post/64d21e84903640003414c338
    // regex for 64d21e84903640003414c338
    // [a-zA-Z0-9]{24}
    protected void checkUrlWithPattern(){
        Assert.assertTrue("URL is not expected \n" +
                        "Expected url: " + baseUrl + getRelativeUrl() +
                        "\n Actual url: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeUrl()));
    }

    public void refreshPage() {
        try {
            ((JavascriptExecutor) webDriver).executeScript("location.reload();");
            logger.info("Page was refreshed");
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    public String getCurrentTabHandle(){
        return webDriver.getWindowHandle();
    }

    public Set<String> getAllTabHandles(){
        return webDriver.getWindowHandles();
    }

    public void openNewTab() {
        try {
            ((JavascriptExecutor) webDriver).executeScript("window.open()");
            logger.info("New tab was opened");
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    public void switchToTab(String tabHandle) {
        try {
            webDriver.switchTo().window(tabHandle);
            logger.info("Switched to tab with handle: " + tabHandle);
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

    public String switchToNewTab(Set<String> oldHandles){
        Set<String> currentHandles = webDriver.getWindowHandles();
        currentHandles.removeAll(oldHandles);

        if (currentHandles.isEmpty()){
            Assert.fail("New tab was not opened");
        }
        if (currentHandles.size() > 1){
            Assert.fail("More than one new tab was opened");
        }
        String newHandle = currentHandles.iterator().next();
        switchToTab(newHandle);
        return newHandle;
    }

    public void closeCurrentTabAndSwitchToTab(String handleToSwitch) {
        try {
            webDriver.close();
            logger.info("Current tab was closed");
            switchToTab(handleToSwitch);
        } catch (Exception e) {
            printErrorAndStopTest();
        }
    }

}
