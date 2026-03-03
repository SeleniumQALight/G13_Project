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

import static org.data.RegistrationValidationMessages.*;

@RunWith(JUnitParamsRunner.class)
public class ValidationMessagesTestWithExcel extends BaseTest {

    //параметризовані тести - які відрізняють тестової датою

    @Test
    @Parameters(method = "parametersForTestValidationMessages")
    public void TC06_testValidationMessages(
            String userName, String email, String password, String expectedMessages) {
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
        logger.info("pathToDataFile =  " + pathToDataFile);
        logger.info("sheetName =  " + sheetName);
        logger.info("skipFirstRow =  " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();

    }
}
