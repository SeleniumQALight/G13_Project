package org.data;

import org.utils.ConfigProperties;
import org.utils.ConfigProvider;

public class TestData {
    public final static String ValidLogin =  System.getProperty("defolt login",
            ConfigProvider.configHiddenProperties.login()
            );
    public final static String ValidPassword = "Stringi23992399";

    public  final  static  String VALID_USERNAME_API = "dzh9923".toLowerCase();
    public final  static String VALID_PASSWORD_API = "Stringi23992399";

}
