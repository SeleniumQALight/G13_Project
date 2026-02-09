package org.pages;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializerBase;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.utils.ConfigProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

abstract public class ParentPage extends CommonActionsWithElements {






    String environment = System.getProperty("env", "aqa");
    protected String baseUrl = ConfigProvider.configProperties.base_url().replace("[env]", environment);

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    protected abstract String getRelativeUrl();
    protected void checkUrl() {
        String expected = normalizeUrl(baseUrl + getRelativeUrl());
        String actual = normalizeUrl(webDriver.getCurrentUrl());
        Assert.assertEquals("Url is not expected", expected, actual);
    }

    protected void checkUrlWithPattern() {
        String regex = "^" + Pattern.quote(baseUrl) + getRelativeUrl();
        String actual = webDriver.getCurrentUrl();
        Assert.assertTrue("Url is not expected \n"
                        + "Expected pattern: " + regex + "\n"
                        + "Actual URL: " + actual,
                actual != null && Pattern.compile(regex).matcher(actual).lookingAt());
    }

    private String normalizeUrl(String url) {
        if (url == null) return null;
        return url.endsWith("/") && url.length() > 1 ? url.substring(0, url.length() - 1) : url;
    }


    public ParentPage switchToMainTab() {
        List<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        if (tabs.isEmpty()) {
            Assert.fail("No browser tabs found");
        }
        webDriver.switchTo().window(tabs.get(0));
        return this;
    }

    public void checkIsButtonSignOutNotVisible() {
        Assert.assertFalse("Button SignOut is visible but should NOT be", isButtonSignOutVisible());
    }

    private boolean isButtonSignOutVisible() {
        try {
            return webDriver.findElement(org.openqa.selenium.By.xpath(
                    "//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), 'sign out') "
                            + "or contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), 'signout')]"
            )).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException e) {
            return false;
        }
    }
    public void checkIsButtonSignOutVisible() {
        Assert.assertTrue("Button SignOut is not visible but should be", isButtonSignOutVisible());
    }
}








