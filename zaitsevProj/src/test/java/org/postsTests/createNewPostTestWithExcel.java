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
import java.io.IOException;
import java.util.Collection;

@RunWith(JUnitParamsRunner.class)
public class createNewPostTestWithExcel extends BaseTest {
    private String createdPostTitle;

    @Test
    @Parameters(method = "parametersForCreatePostWithExcel")
    public void TC01_createNewPostWithExcel(String titlePattern,
                                            String bodyPattern,
                                            String dropdownValue,
                                            String checkboxState,
                                            String expectedSuccessMessage,
                                            String expectedCheckboxUniqueState) {

        createdPostTitle = String.format(titlePattern, "Zaitsev_Test_Post", Utils_Custom.getDateAndTimeFormatted());
        String body = String.format(bodyPattern, Utils_Custom.getDateAndTimeFormatted());
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .clickOnButtonCreatePost()
                .checkIsRedirectToCreatePostPage()
                .enterTextIntoInputTitle(createdPostTitle)
                .enterTextIntoInputBody(body)
                .selectTextInDropdownAccessByValue(dropdownValue)
                .setUniquePostCheckBox(checkboxState)
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkPostWasCreatedMessageIsDisplayed()
                .checkTextInSuccessMessage(expectedSuccessMessage)
                .checkUniquePostCheckBoxState(expectedCheckboxUniqueState)
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile();

        pageProvider.getMyProfilePage()
                .checkIsRedirectToMyProfilePage()
                .checkPostWithTitlePresent(createdPostTitle, 1);
    }

         public Collection parametersForCreatePostWithExcel() throws IOException {
                String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
                String sheetName = "createPostWithExcel";
                boolean skipFirstRow = false;
                return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
         }

    @After
    public void deletePosts(){
        logger.info("Post condition - delete posts");
        pageProvider.getHomePage()
                .openHomePageAndLoginIfNeeded()
                .getHeaderForLoggedUserElement().clickOnButtonMyProfile()
                .checkIsRedirectToMyProfilePage()
                .deletePostsTillPresent(createdPostTitle);

        }
 }
