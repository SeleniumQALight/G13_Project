package org.postsTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.utils.ConfigProvider;
import org.utils.ExcelSpreadsheetData;
import org.utils.Utils_Custom;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

@RunWith(JUnitParamsRunner.class)
public class CreateNewPostWithExcelTest extends BaseTest {
    private String postTitleDelete;

    //GUID - генерує унік значення для кожного запуску тесту, щоб не було конфлікту з існуючими постами

    //private final String POST_TITLE = "TC04 G13 Frankova" + Utils_Custom.getDateAndTimeFormatted();

    @Test
    @Parameters(method = "parametersForCreateNewPost")
    public void TC04_createNewPost(String title, String body, String dropdownValue, String checkboxState, String expectedMessage,
                                   String expectedCheckboxState) {
        String postTitleReady = String.format(title, "TC04 G13 Frankova", Utils_Custom.getDateAndTimeFormatted());
        String postBodyReady = String.format(body, "TC04 G13 Frankova");
        this.postTitleDelete = postTitleReady;

        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement().clickOnButtonCreateNewPost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(postTitleReady)
                .enterTextIntoInputBody(postBodyReady)
                .selectTextInDropdownAccess(dropdownValue)
                .enterStateForCheckboxUniquePost(checkboxState)

                .clickOnButtonSaveNewPost()
                .checkIsRedirectToPostPage()
                .checkPostWasCreatedMessageIsDisplayed()
                .checkTextInSuccessMessage(expectedMessage)
                .checkCheckboxStateInCreatedPost(expectedCheckboxState)
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile();

        pageProvider.getMyProfilePage()
                .checkIsRedirectToMyProfilePage()
                .checkIsPostWithTitlePresent(postTitleReady, 1);

    }

    public Collection parametersForCreateNewPost() throws IOException {
        String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        String sheetName = "createPostWithExcelFrankova";
        boolean skipFirstRow = false;
        logger.info("pathToDataFile =  " + pathToDataFile);
        logger.info("sheetName =  " + sheetName);
        logger.info("skipFirstRow =  " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }

    @After
    public void deletePost() {
        logger.info("Post condition - delete posts");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(postTitleDelete);
    }
}
