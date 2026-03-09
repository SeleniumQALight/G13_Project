package org.apiTests;

import io.restassured.RestAssured;

public class BaseTestPrivatBankApi {

    public BaseTestPrivatBankApi() {
        RestAssured.baseURI = "https://api.privatbank.ua";
    }
}