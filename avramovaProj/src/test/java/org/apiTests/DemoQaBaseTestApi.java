package org.apiTests;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class DemoQaBaseTestApi {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://demoqa.com";
    }
}
