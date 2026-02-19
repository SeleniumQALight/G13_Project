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
    private Database mySQL_DataBase;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setUpDB() throws SQLException, ClassNotFoundException {
        mySQL_DataBase = MySQL_Database.getDataBase();
        logger.info("Connection to MySQL database was setup");
    }

    @After
    public void tearDown(){
        try {
            mySQL_DataBase.quit();
            logger.info("Connection to MySQL database was closed");
        } catch (SQLException e) {
            logger.error("Failed to close the database connection", e);
        }
    }

    @Test
    public void testDataFromDB() throws SQLException, ClassNotFoundException {
        ArrayList<Map<String, String>> dataFromSeleniumTable = mySQL_DataBase
                .selectTableAsMap("select * from seleniumTable WHERE id = 16");

        logger.info(dataFromSeleniumTable);
        logger.info("Size = " + dataFromSeleniumTable.size());

        final String LOGIN = "G13_oleg";

        dataFromSeleniumTable = mySQL_DataBase
                .selectTableAsMap("select * from seleniumTable WHERE login = '" + LOGIN + "'");

        Assert.assertEquals(0, dataFromSeleniumTable.size());

        int numberOfEffectedRows = mySQL_DataBase
                .changeTable("INSERT INTO seleniumTable VALUES('13745','%s','%s')", LOGIN, "somePassword123");
        Assert.assertEquals(1, numberOfEffectedRows);

        dataFromSeleniumTable = mySQL_DataBase
                .selectTableAsMap("select * from seleniumTable WHERE login = '" + LOGIN + "'");

        Assert.assertEquals(1, dataFromSeleniumTable.size());

        int numberOfDeletedRows = mySQL_DataBase
                .changeTable("DELETE FROM seleniumTable WHERE login = '%s'", LOGIN);
        Assert.assertEquals(1, numberOfDeletedRows);


        logger.info("------------------");

        DB_Util_seleniumTable db_util_seleniumTable = new DB_Util_seleniumTable();
        logger.info(db_util_seleniumTable.getPassForLogin("VA_G11"));

    }
}
