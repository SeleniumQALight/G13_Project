package org.postTests;

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
import java.io.IOException;
import java.util.Collection;

@RunWith(JUnitParamsRunner.class)
public class CreatePostWithExcelTest extends BaseTest {

    private String excelPostTitle;
    private String uniqueBody;

    @Test
    @Parameters(method = "parametersForCreatePost")
    public void TC07_createNewPostWithExcel(String title, String body, String dropdownValue,
                                            String checkboxState, String expectedMessage,
                                            String expectedUniqueValue) {
        excelPostTitle = String.format(title, "G13 Yulii", Utils_Custom.getDateAndTimeFormatted());
        uniqueBody = String.format(body, Utils_Custom.getDateAndTimeFormatted());

        logger.info("dropdownValue = " + dropdownValue);
        logger.info("checkboxState = " + checkboxState);

        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreateNewPostPage()
                .enterTextIntoInputTitle(excelPostTitle)
                .enterTextIntoInputBody(uniqueBody)
                .selectTextInDropdownAccess(dropdownValue)
                .setCheckBoxState(checkboxState)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkPostWasCreatedMessageIsDisplayed()
                .checkIsPostUnique(expectedUniqueValue)
                .checkTextInSuccessMessage(expectedMessage)
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile();

        pageProvider.getMyProfilePage()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitlePresent(excelPostTitle, 1);
    }

    public Collection parametersForCreatePost() throws IOException {
        String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuitStfTest.xls";
        String sheetName = "createPostWithExcel";
        boolean skipFirstRow = false;
        logger.info("pathToDataFile = " + pathToDataFile);
        logger.info("sheetName = " + sheetName);
        logger.info("skipFirstRow = " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }

    @After
    public void deletePosts() {
        logger.info("Post condition - delete posts");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(excelPostTitle);
    }
}
