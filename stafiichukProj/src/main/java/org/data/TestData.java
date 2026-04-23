package org.data;

import org.utils.ConfigProvider;

import java.util.Locale;

public class TestData {
    public final static String VALID_LOGIN_UI = System.getProperty("defaultLogin",
            ConfigProvider.configHiddenProperties.login());
    public final static String VALID_PASSWORD_UI = "123456qwerty";

    public final static String VALID_USERNAME_API = "QAYuliiG13API".toLowerCase(Locale.ROOT);
    public final static String VALID_PASSWORD_API = "Qwerty123456";

}
