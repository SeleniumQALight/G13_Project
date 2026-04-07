package org.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.log4j.Logger;
import org.api.dto.requestDto.DemoqaAddBooksList;
import org.api.dto.requestDto.DemoqaBookIsbn;
import org.api.dto.responseDto.DemoqaBookDto;
import org.api.dto.responseDto.DemoqaBooksListDto;
import org.api.dto.responseDto.DemoqaLoginDto;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.*;

public class DemoqaApiHelper {

    Logger logger = Logger.getLogger(getClass());

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public DemoqaLoginDto getTokenAndUserID(String userName, String password) {
        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("userName", userName);
        requestBody.put("password", password);

        return given()
                .spec(requestSpecification)
                .body(requestBody)
                .when()
                .post(DemoqaEndPoints.ACCOUNT_LOGIN)
                .then()
                .spec(responseSpecification)
                .extract()
                .body()
                .as(DemoqaLoginDto.class); // Повертаємо об'єкт класу DemoqaLogin
    }


    public ValidatableResponse deleteAllUserBooks(String token, String userId, int expectedStatusCode) {
        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)
                .when()
                .delete(DemoqaEndPoints.BOOKSTORE_BOOKS)
                .then()
                .statusCode(expectedStatusCode);
    }


    public List<DemoqaBookDto> getAllBooks() {
        DemoqaBooksListDto response = given()
                .spec(requestSpecification)
                .when()
                .get(DemoqaEndPoints.BOOKSTORE_BOOKS)
                .then()
                .spec(responseSpecification)
                .extract()
                .body()
                .as(DemoqaBooksListDto.class);

        return response.getBooks();

    }


    public List<DemoqaBookDto> getUserBooks(String token, String userId, int expectedStatusCode) {
        DemoqaBooksListDto responseUserBooks = given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .pathParam("userId", userId)
                .when()
                .get(DemoqaEndPoints.ACCOUNT_USER + "/{userId}")
                .then()
                .spec(responseSpecification)
                .statusCode(expectedStatusCode)
                .extract()
                .body()
                .as(DemoqaBooksListDto.class);

        return responseUserBooks.getBooks();

    }

    public ValidatableResponse addBookForUser(String token, String userId, String isbn, int expectedStatusCode){

        //Створюємо об'єкт для вкладеного списку
        DemoqaBookIsbn isbnObj = DemoqaBookIsbn.builder().isbn(isbn).build();

        // 3. Формуємо повне тіло запиту
        DemoqaAddBooksList requestBody = DemoqaAddBooksList.builder()
                .userId(userId)
                .collectionOfIsbns(List.of(isbnObj)) // створюємо список з одним об'єктом
                .build();

        return given()
                .spec(requestSpecification)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post(DemoqaEndPoints.BOOKSTORE_BOOKS)
                .then()
                .statusCode(expectedStatusCode);

    }
}
