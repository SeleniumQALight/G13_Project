package org.apiTests;

import io.restassured.RestAssured;

public class BaseTestPrivatApi {

    public BaseTestPrivatApi() {
        RestAssured.baseURI = "https://api.privatbank.ua";
    }
}