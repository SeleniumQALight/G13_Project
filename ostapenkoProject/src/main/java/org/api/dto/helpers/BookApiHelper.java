package org.api.dto.helpers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.api.dto.requesteDto.AddBookRequestDto;
import org.api.dto.requesteDto.LoginRequestDto;
import static io.restassured.RestAssured.*;
import org.dto.IsbnDto;
import org.junit.Test;

import java.util.List;

public class BookApiHelper {
    public Response login(LoginRequestDto body) {
        return given()
                .log().all()
                .contentType("application/json")
                .body(body)
                .when()
                .post("/Account/v1/Login");
    }

    public Response deleteAllBooks(String token, String userId) {
        return given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/BookStore/v1/Books?UserId=" + userId);
    }

    public Response getAllBooks() {
        return given()
                .log().all()
                .when()
                .get("/BookStore/v1/Books");
    }

    public Response addBook(String token, AddBookRequestDto body) {
        return given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(body)
                .when()
                .post("/BookStore/v1/Books");
    }

    public Response getUser(String token, String userId) {
        return given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/Account/v1/User/" + userId);
    }
}
