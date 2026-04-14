package org.apiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class AddMultipleBookTest {

    private String baseUrl = "https://bookstore.toolsqa.com";
    private String userId;
    private String token;
    private String userName;
    private String password = "Qwerty12345!";

    @Before
    public void setUp() {
        RestAssured.baseURI = baseUrl;

        userName = "Alyona" + System.currentTimeMillis();

        String createUserBody = "{\n" +
                "  \"userName\": \"" + userName + "\",\n" +
                "  \"password\": \"" + password + "\"\n" +
                "}";

        Response createUserResponse = given()
                .contentType("application/json")
                .body(createUserBody)
                .post("/Account/v1/User");

        createUserResponse.prettyPrint();

        userId = createUserResponse.jsonPath().getString("userID");

        if (userId == null) {
            throw new RuntimeException("User was not created!");
        }

        String tokenBody = "{\n" +
                "  \"userName\": \"" + userName + "\",\n" +
                "  \"password\": \"" + password + "\"\n" +
                "}";

        Response tokenResponse = given()
                .contentType("application/json")
                .body(tokenBody)
                .post("/Account/v1/GenerateToken");

        tokenResponse.prettyPrint();

        token = tokenResponse.jsonPath().getString("token");

        if (token == null) {
            throw new RuntimeException("Token was not generated!");
        }
    }

    @Test
    public void addBookToUserAndVerify() {

        SoftAssertions softAssertions = new SoftAssertions();

        String addBookBody = "{\n" +
                "  \"userId\": \"" + userId + "\",\n" +
                "  \"collectionOfIsbns\": [\n" +
                "    { \"isbn\": \"9781449325862\" }\n" +
                "  ]\n" +
                "}";

        System.out.println("REQUEST BODY:\n" + addBookBody);

        Response addBookResponse = given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(addBookBody)
                .post("/BookStore/v1/Books");

        addBookResponse.prettyPrint();

        int statusCode = addBookResponse.getStatusCode();
        softAssertions.assertThat(statusCode)
                .as("Status code check")
                .isIn(200, 201);

        Response getUserResponse = given()
                .header("Authorization", "Bearer " + token)
                .get("/Account/v1/User/" + userId);

        getUserResponse.prettyPrint();

        String returnedIsbn = getUserResponse.jsonPath()
                .getString("books[0].isbn");

        softAssertions.assertThat(returnedIsbn)
                .as("ISBN check")
                .isEqualTo("9781449325862");

        softAssertions.assertAll();
    }
}