package org.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.data.TestData.PROFILE_PAGE_URL;

public class ProfilePage extends ParentPage{

    @FindBy(xpath = ".//div[contains(@class,'profile-nav')]")
    private WebElement navMenuOnProfilePage;

    @FindBy(xpath = ".//div[@class='list-group']/a/strong")
    private List <WebElement> postRows;

    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public ProfilePage checkIsProfilePageOpened() {
        isRedirectedToPage(PROFILE_PAGE_URL);
        isElementEnabled(navMenuOnProfilePage);
        return this;
    }

    public ProfilePage checkIsCreatedPostListed(String postTitle) {
        long matches = postRows.stream()
                .filter(e -> e.getText().contains(postTitle))
                .count();

        Assert.assertEquals("Post should be listed exactly once", 1, matches);
        return this;
    }
}
