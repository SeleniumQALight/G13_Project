package org.apiTests;

import io.restassured.RestAssured;
import org.data.TestData;
import org.pages.ParentPage;

public class BaseTestApi {
public BaseTestApi() {
    RestAssured.baseURI = ParentPage.baseUrl + "/api/";
}
}
