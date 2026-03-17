package org.apiTests;

import io.restassured.RestAssured;
import org.pages.ParentPage;

public class DemoqaBaseTestApi {
public DemoqaBaseTestApi(){
    RestAssured.baseURI = "https://demoqa.com";
}
}
