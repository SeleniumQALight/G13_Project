package org.apiTests;
import io.restassured.RestAssured;

public class PBBaseTestApi {
    public PBBaseTestApi(){
        RestAssured.baseURI = "https://api.privatbank.ua/p24api/";
    }
}
