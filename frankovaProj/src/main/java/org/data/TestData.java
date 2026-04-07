package org.data;

import org.utils.ConfigProvider;

public class TestData {

    public static final String VALID_LOGIN = System.getProperty("defaultLogin",
            ConfigProvider.configHiddenProperties.login());
    public static final String VALID_PASSWORD = "123456qwerty";

    public final static String VALID_USERNAME_API = "yuliiaapi".toLowerCase();
    public final static String VALID_PASSWORD_API = "Julialapa1996!";
    public static String currentUser;

    public static Double buyRateAPI;
    public static Double saleRateAPI;

    public static Double buyRateUI;
    public static Double saleRateUI;
}
