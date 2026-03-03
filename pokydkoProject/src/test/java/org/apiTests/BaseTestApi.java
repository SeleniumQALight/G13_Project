package org.apiTests;

import io.restassured.RestAssured;
import org.junit.Before;
import org.pages.ParentPage;

public class BaseTestApi {
    public BaseTestApi() {
        RestAssured.baseURI = ParentPage.baseUrl + "/api/";
    }

}
