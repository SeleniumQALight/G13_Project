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

    Logger logger = Logger.getLogger(getClass());
    @Before
    public void setupDB() throws SQLException, ClassNotFoundException {
        mySQLDatabase = MySQL_Database.getDataBase();
        logger.info("Connected to DB");
    }

    @After
    public void tearDownDB() {
        try {
            mySQLDatabase.quit();
            logger.info("Connection to DB was closed");
        } catch (SQLException e) {
            logger.error("Cannot disconnect from DB. Exception: " + e);
        }
    }

    @Test
    public void testDatabaseQuery() throws SQLException, ClassNotFoundException {
        ArrayList<Map<String, String>> dataFromSeleniumTable = mySQLDatabase
                .selectTableAsMap("SELECT * FROM seleniumTable");

        logger.info("Size = " + dataFromSeleniumTable.size());

        final String LOGIN = "G13_ostapenko";

        dataFromSeleniumTable = mySQLDatabase
                .selectTableAsMap("SELECT * FROM seleniumTable WHERE login = '" + LOGIN + "'");

        Assert.assertEquals(0, dataFromSeleniumTable.size());

        int numberOfRows = mySQLDatabase
                .changeTable("INSERT INTO seleniumTable VALUES ('10928', '%s', '%s')", LOGIN, "12345");
        Assert.assertEquals(1, numberOfRows);

        dataFromSeleniumTable = mySQLDatabase
                .selectTableAsMap("SELECT * FROM seleniumTable WHERE login = '" + LOGIN + "'");

        Assert.assertEquals(1, dataFromSeleniumTable.size());

        int deletedRows = mySQLDatabase
                .changeTable("DELETE FROM seleniumTable WHERE login = '%s'", LOGIN);
        Assert.assertEquals(1, deletedRows);



        logger.info("----------------------");


        DB_Util_seleniumTable db_util_seleniumTable = new DB_Util_seleniumTable();
        logger.info(db_util_seleniumTable.getPassForLogin("VA_G11"));


    }
}
