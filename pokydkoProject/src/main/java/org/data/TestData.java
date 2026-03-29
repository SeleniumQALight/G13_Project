package org.data;

import org.utils.ConfigProvider;

import java.util.HashMap;
import java.util.Map;

public class TestData {
    public final static String VALID_LOGIN_UI = System.getProperty("defaultLogin",
            ConfigProvider.configHiddenProperties.login());
    public final static String VALID_PASSWORD_UI = "123456qwerty";

    public final static String VALID_USERNAME_API = "vlad".toLowerCase();
    public final static String VALID_PASSWORD_API = "123456qwerty";


    public static double apiBuy;
    public static double apiSale;

    public static double uiBuy;
    public static double uiSale;

    public static String currency;

    public static Map<String, Double> apiRates = new HashMap<>();
    public static Map<String, Double> uiRates = new HashMap<>();
}
