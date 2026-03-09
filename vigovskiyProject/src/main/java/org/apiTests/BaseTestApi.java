package org.apiTests;

import io.restassured.RestAssured;

public class BaseTestApi {
    public BaseTestApi(){
        RestAssured.baseURI="https://aqa-complexapp.onrender.com"+"/api/";
    }
}
