package org.registrationTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.utils.ConfigProvider;
import org.utils.ExcelSpreadsheetData;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import static org.data.RegistrationVadidationMessages.*;


@RunWith(JUnitParamsRunner.class)
public class ValidationMessagesTestWithExcel extends BaseTest {

    @Test
    @Parameters(method = "parametersForTestValidationMessages")
    public void TC03_testValidationMessages(String userName,
                                            String email, String password, String expectedMessage
    ) {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoRegistrationUserNameField(userName)
                .enterTextIntoRegistrationEmailField(email)
                .enterTextIntoRegistrationPasswordField(password)
                .checkErrorsMessages(expectedMessage);


    }

    public Collection parametersForTestValidationMessages() throws IOException {
        String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        String sheetName = "registrationErrors";
        boolean skipFirstRow = false;
        logger.info("pathToDataFile = " + pathToDataFile );
        logger.info("sheetName = " + sheetName);
        logger.info(" skipFirstRow = " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow ).getData();
    }

//    public Collection parametersForTestValidationMessages() throws IOException {
//        String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuite.xls";
//        String sheetName = "registrationErrors";
//        boolean skipFirstRow = false;
//        logger.info("pathToDataFile = " + pathToDataFile);
//        logger.info("sheetName = " + sheetName);
//        logger.info("skipFirstRow = " + skipFirstRow);
//        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile),sheetName,skipFirstRow).getData();
//
//    }

//    public Object[][] parametersForTestValidationMessages() {
//        return new Object[][]{
//                {"mi", "mi1", "mi2", ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
//                {"mio", "mi1", "mi254344", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
//                {"taras", "mi1", "mi2344gffthds", ERROR_EMAIL}
//
//        };
//
//    }
}

