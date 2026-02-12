package org.registrationTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pages.utils.ConfigProvider;
import org.pages.utils.ExcelSpreadsheetData;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;

import static org.data.RegistrationValidationMessages.*;


@RunWith(JUnitParamsRunner.class)
public class ValidationMessagesTestWithExel extends BaseTest {
    @Test
    @Parameters(method = "parametersForTestValidationMessages")
    public void TC05_testValidationMessages(String userName, String email, String password, String expectedMessages) {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoRegistrationUserNameField(userName)
                .enterTextIntoRegistrationEmailField(email)
                .enterTextIntoRegistrationPasswordField(password)
                .checkErrorsMessages(expectedMessages);
    }

    public Collection parametersForTestValidationMessages() throws IOException {
        String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        String sheetName = "registrationErrors";
        boolean skipFirstRow = false;
        logger.info("pathToDataFile = " + pathToDataFile);
        logger.info("sheetName = " + sheetName);
        logger.info("skipFirstRow = " + skipFirstRow);
        return  new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }
}