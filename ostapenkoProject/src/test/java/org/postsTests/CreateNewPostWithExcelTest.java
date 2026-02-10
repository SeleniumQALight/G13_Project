package org.postsTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;
import org.utils.ConfigProvider;
import org.utils.ExcelSpreadsheetData;

import java.io.FileInputStream;
import java.util.Collection;

public class CreateNewPostWithExcelTest extends BaseTest {

    private String createdPostTitle;

    @Test
    public void createNewPostUsingExcelData() throws Exception {

        String pathToDataFile =
                ConfigProvider.configProperties.DATA_FILE_PATH() + "TestDataSuit.xls";
        String sheetName = "createPostWithExcel";

        Collection<Object[]> testData =
                new ExcelSpreadsheetData(
                        new FileInputStream(pathToDataFile),
                        sheetName,
                        false
                ).getData();

        pageProvider.getLoginPage()
                .openLoginPageAndFillFormWithValidCred()
                .checkIsRedirectToHomePage();

        for (Object[] row : testData) {

            String titleFromExcel = row[0].toString();
            String body = row[1].toString();
            String accessType = row[2].toString();
            String checkboxState = row[3].toString();
            String successMessage = row[4].toString();
            String isUnique = row[5].toString();

            createdPostTitle = titleFromExcel + " " + System.currentTimeMillis();

            logger.info("Creating post: " + createdPostTitle);

            pageProvider.getHomePage()
                    .getHeaderForLoggedUserElement()
                    .clickOnButtonCreatePost()
                    .checkIsRedirectToCreatePostPage()
                    .enterTextIntoInputTitle(createdPostTitle)
                    .enterTextIntoInputBody(body)
                    .setStateToCheckbox(checkboxState)
                    .selectTextInDropDownViaValue(accessType)
                    .clickOnSaveNewPostButton()
                    .checkIsRedirectToPostPage()
                    .checkPostWasCreatedMessageIsDisplayed()
                    .checkTextInSuccessMessage(successMessage)
                    .checkPostIsUniqueByState(isUnique)
                    .getHeaderForLoggedUserElement()
                    .clickOnButtonMyProfile();

            pageProvider.getMyProfilePage()
                    .checkIsRedirectToMyProfilePage()
                    .checkPostWithTitle(createdPostTitle, 1);

            // Clean up after each iteration
            pageProvider.getMyProfilePage()
                    .deletePostWithTitleWhilePresent(createdPostTitle);

            createdPostTitle = null;
        }
    }

    @After
    public void cleanUpIfNeeded() {
        if (createdPostTitle != null) {
            pageProvider.getHomePage()
                    .openHomePageAndLoginNeeded()
                    .getHeaderForLoggedUserElement()
                    .clickOnButtonMyProfile()
                    .checkIsRedirectToMyProfilePage()
                    .deletePostWithTitleWhilePresent(createdPostTitle);
        }
    }
}
