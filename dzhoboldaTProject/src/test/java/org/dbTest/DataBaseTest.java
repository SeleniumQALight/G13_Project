package org.dbTest;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.utils.DB_Util_seleniumTable;
import org.utils.Database;
import org.utils.MySQL_Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class DataBaseTest {

    private Database mySQLDatabase;
    private Logger logger = Logger.getLogger(getClass());


    @Before
    public void setUpDB() throws SQLException, ClassNotFoundException {
        mySQLDatabase = MySQL_Database.getDataBase();
        logger.info(" Connection to MySQL database was setup ");

    }

    @After
    public  void tearDownDB() {
        try {
            mySQLDatabase.quit();
            logger.info(" Connection to MySQL database was closed ");
        } catch (SQLException e) {
            logger.error(" Failed to close connection to MySQL database " + e.getMessage());
        }

    }

    @Test
    public void testDataBaseFromDB() throws SQLException, ClassNotFoundException {
        ArrayList<Map<String,String>>dataFromSeleniumTable = mySQLDatabase
                .selectTableAsMap("SELECT * FROM seleniumTable ");
        logger.info(dataFromSeleniumTable);
        logger.info("Size = " + dataFromSeleniumTable.size());

        final String LOGIN = "selenium_13";

        dataFromSeleniumTable = mySQLDatabase
                .selectTableAsMap("SELECT * FROM seleniumTable WHERE login = ' " + LOGIN + "'");
        Assert.assertEquals(0, dataFromSeleniumTable.size());

        int numberOfEffectedRows = mySQLDatabase
                .changeTable("INSERT INTO seleniumTable VALUES('99999','%s','%s')", LOGIN, "selenium_13");
        Assert.assertEquals("Number of insert rows ", 1, numberOfEffectedRows);

        dataFromSeleniumTable = mySQLDatabase
                .selectTableAsMap("SELECT * FROM seleniumTable WHERE login='" + LOGIN + "'");

        Assert.assertEquals(1, dataFromSeleniumTable.size());

        int numberOfDeletedRows = mySQLDatabase
                .changeTable("DELETE FROM seleniumTable WHERE login='%s' ", LOGIN);
        Assert.assertEquals("Number of deleted rows ", 1, numberOfDeletedRows);

        logger.info(("****************************************"));

        DB_Util_seleniumTable dbUtilSeleniumTable = new DB_Util_seleniumTable();
        dbUtilSeleniumTable.getPassForLogin("VA_G11");
        logger.info(dbUtilSeleniumTable.getPassForLogin("VA_G11"));


    }
}
