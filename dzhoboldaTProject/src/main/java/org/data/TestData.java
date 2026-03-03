package org.data;

import org.utils.ConfigProperties;
import org.utils.ConfigProvider;

public class TestData {
    public final static String ValidLogin =  System.getProperty("defolt login",
            ConfigProvider.configHiddenProperties.login()
            );
    public final static String ValidPassword = "Stringi23992399";

}
