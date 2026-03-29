package org.data;

import org.utils.ConfigProvider;

public class TestData {
    public final static String VALID_LOGIN_UI = System.getProperty("defaultLogin",
            ConfigProvider.configHiddenProperties.login());
    public final static String VALID_PASSWORD_UI = "123456qwerty";

    public final static String VALID_LOGIN_API = "aostapenko".toLowerCase();
    public final static String VALID_PASSWORD_API = "123456qwerty";
    public static String currentUser;
}
