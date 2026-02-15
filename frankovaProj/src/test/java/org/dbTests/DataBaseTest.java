package org.dbTests;

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

    Logger logger = Logger.getLogger(getClass());


    @Before
    public void setUpDB() throws SQLException, ClassNotFoundException {
        mySQLDatabase = MySQL_Database.getDataBase();
        logger.info("MySQL Database connection established.");
    }


    @After
    public void tearDown() {
        try {
            mySQLDatabase.quit();
            logger.info("MySQL Database connection closed.");

        } catch (SQLException e) {
            logger.error("Error closing database connection: " + e.getMessage());
        }
    }

    @Test
    public void testDataFromDB() throws SQLException, ClassNotFoundException {
        ArrayList<Map<String, String>> dataFromSeleniumTable = mySQLDatabase
                .selectTableAsMap("SELECT * FROM seleniumTable");

        logger.info (dataFromSeleniumTable);
        logger.info("Size = " + dataFromSeleniumTable.size());

        final String LOGIN = "G13_Yuliia";

        dataFromSeleniumTable = mySQLDatabase
                .selectTableAsMap("SELECT * FROM seleniumTable WHERE login = '" + LOGIN + "'");

       Assert.assertEquals(0, dataFromSeleniumTable.size());

        int numberOfEffectedRows = mySQLDatabase
                .changeTable("INSERT INTO seleniumTable VALUES('11774','%s','%s')", LOGIN, "12345");
        Assert.assertEquals("Number of insert rows ", 1, numberOfEffectedRows);

        dataFromSeleniumTable = mySQLDatabase
                .selectTableAsMap("SELECT * FROM seleniumTable WHERE login='" + LOGIN + "'");
        Assert.assertEquals(1, dataFromSeleniumTable.size());

        int numberOfDeletedRows = mySQLDatabase
                .changeTable("DELETE FROM seleniumTable WHERE login = '%s')", LOGIN);
        Assert.assertEquals("Number of deleted rows ", 1, numberOfDeletedRows);


        logger.info("---------------------");

        DB_Util_seleniumTable db_util_seleniumTable = new DB_Util_seleniumTable();
        logger.info (db_util_seleniumTable.getPassForLogin("VA_G11"));




    }
}

