package org.apiTests;

import io.restassured.http.ContentType;
import org.api.AddBookRequestDto;
import org.api.IsbnDto;
import org.api.dto.responsDto.LoginResponseDto;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class BooksTests {
    @Test
    public void addBookToUserTest() {
        SoftAssertions softAssert = new SoftAssertions();

        // 1. токен
        LoginResponseDto loginBody = LoginResponseDto.builder()
                .userName("Dzh2399")
                .password("Password123!")
                .build();

        LoginResponseDto loginResponse = given()
                .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Referer", "https://demoqa.com/login") // Добавляем, откуда мы "пришли"
                .header("Origin", "https://demoqa.com")
                .body(loginBody)
                .log().all()
                .when()
                .post("https://demoqa.com/Account/v1/Login")
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(LoginResponseDto.class);


        String token = loginResponse.getToken();
        String userId = loginResponse.getUserId();

        given()
                .contentType(ContentType.JSON)
                .body(loginBody)
                .when()
                .post("https://demoqa.com/Account/v1/Authorized")
                .then()
                .statusCode(200);

        // 2. очистка
        given()
                .header("Authorization", "Bearer " + token)
                .header("Accept", "application/json") // Добавь этот заголовок и сюда тоже
                .when()
                .delete("https://demoqa.com/BookStore/v1/Books?UserId=" + userId) // Укажи полный URL
                .then()
                .log().all() // Добавь лог, чтобы видеть причину, если снова будет 403
                .statusCode(204);


        // 3. первая книга
        String firstIsbn = given()
                .when()
                .get("https://demoqa.com/BookStore/v1/Books")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("books[0].isbn");

        // 4. добавление
        AddBookRequestDto addBookBody = AddBookRequestDto.builder()
                .userId(userId)
                .collectionOfIsbns(List.of(new IsbnDto(firstIsbn)))
                .build();

        given()
                .header("Authorization", "Bearer " + token)
                .header("Accept", "application/json")
                .contentType(ContentType.JSON)
                .body(addBookBody)
                .when()
                .post("https://demoqa.com/BookStore/v1/Books")
                .then()
                .statusCode(201);

        // 5.  (GET /Account/v1/User/{UUID})
        var userProfile = given()
                .header("Authorization", "Bearer " + token)
                .header("Accept", "application/json")
                .when()
                .get("https://demoqa.com/Account/v1/User/" + userId)
                .then()
                .statusCode(200)
                .extract().jsonPath();

        softAssert.assertThat(userProfile.getList("books").size())
                .as("Amount of books").isEqualTo(1);
        softAssert.assertThat(userProfile.getString("books[0].isbn"))
                .as("ISBN added book").isEqualTo(firstIsbn);

        softAssert.assertAll();
    }
}
