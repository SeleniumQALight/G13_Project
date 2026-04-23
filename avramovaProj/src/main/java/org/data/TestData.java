package org.data;

import org.utils.ConfigProvider;

import java.util.Locale;

public class TestData {
    public final static String VALID_LOGIN_UI = System.getProperty("defaultLogin",
            ConfigProvider.configHiddenProperties.login());
    public final static String VALID_PASSWORD_UI = "123456qwerty";

    public final static String VALID_USERNAME_API = "qaavramova".toLowerCase();
    public final static String VALID_PASSWORD_API = "qaavramova123";

    public final static String DEMO_QA_VALID_USERNAME_API = "qaavramova".toLowerCase();
    public final static String DEMO_QA_VALID_PASSWORD_API = "QaAvramova123!";
}
