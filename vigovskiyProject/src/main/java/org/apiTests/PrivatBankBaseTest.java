package org.apiTests;
import io.restassured.RestAssured;
public class PrivatBankBaseTest {
    public PrivatBankBaseTest() {
        RestAssured.baseURI = "https://api.privatbank.ua";
    }
}
