package org.data;

import org.utils.ConfigProvider;

public class TestData {

    public static final String VALID_LOGIN = System.getProperty("defaultLogin",
            ConfigProvider.configHiddenProperties.login());
    public static final String VALID_PASSWORD = "123456qwerty";

    public final static String VALID_USERNAME_API = "yuliiaapi".toLowerCase();
    public final static String VALID_PASSWORD_API = "Julialapa1996!";

    public static final String USERNAME_DEMOQA ="yuliiafrankova";
    public static final String PASSWORD_DEMOQA = "Julialapa1996!";
}
