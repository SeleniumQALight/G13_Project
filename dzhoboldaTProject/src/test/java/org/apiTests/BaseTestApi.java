package org.apiTests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.pages.ParentPage;

public class BaseTestApi {
public BaseTestApi(){
    RestAssured.baseURI = ParentPage.baseUrl + "/api/";
}



protected io.restassured.specification.RequestSpecification getRequestSpecification() {
    return new io.restassured.builder.RequestSpecBuilder()
            .setBaseUri("https://api.privatbank.ua")
            .setAccept(io.restassured.http.ContentType.JSON)
            .build();
}
    }
