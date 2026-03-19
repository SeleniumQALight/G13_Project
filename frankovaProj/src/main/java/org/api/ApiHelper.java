package org.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.api.dto.responseDto.PostsDto;
import org.data.TestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;


public class ApiHelper {
    Logger logger = Logger.getLogger(getClass());

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .addFilter(new AllureRestAssured())
            .log(LogDetail.ALL)
            .build();//створюємо об'єкт специфікації запиту, який можна використовувати в кожному тесті, щоб не повторювати однакові частини коду

    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();


//отримаємо усі пости юзера і зберігаємо через DTO в масив об'єктів, щоб потім з ними працювати в тесті, а не з сирим JSON рядком
    public PostsDto[] getAllPostsByUserInObject() {
        return getAllPostsByUserInObject(TestData.VALID_USERNAME_API, HttpStatus.SC_OK);
    }

    public PostsDto[] getAllPostsByUserInObject(String userName, int status) {
        return getAllPostsByUserRequest(userName, status)
                .extract().body()
                .as(PostsDto[].class);
    }

    public ValidatableResponse getAllPostsByUserRequest(String userName, int expectedStatusCode) {
        return given()
//                .contentType(ContentType.JSON)
//                .log().all()
                .spec(requestSpecification)
                .when()
                .get(EndPoints.POSTS_BY_USER, userName)
                .then()
                .log().all()
                .statusCode(expectedStatusCode);

    }

    //для дефолтного юзера
    public String getToken() {
        return getToken(TestData.VALID_USERNAME_API, TestData.VALID_PASSWORD_API);
    }

    //для будь-якого юзера, якщо передані в тесті параметри
    public String getToken(String userName, String password) {

        //формуємо тіло запиту у вигляді HashMap, щоб RestAssured міг його серіалізувати в JSON
//        HashMap requestBody = new HashMap();
//        requestBody.put("username", userName);
//        requestBody.put("password", password);

        //за допомогою Jackson формуємо JSON-об'єкт, який потім буде серіалізований в JSON рядок для тіла запиту
        // 1. Створюємо екземпляр ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        // 2. Створюємо порожній JSON-об'єкт (вузол)
        ObjectNode requestBody = mapper.createObjectNode();
        // 3. Наповнюємо його даними (як у Map, але через методи put)
        requestBody.put("username", userName);
        requestBody.put("password", password);


        return given()
                .spec(requestSpecification)//специфікація - виносимо однакові чатсини - тут ContentType
                .body(requestBody)
                .when()
                .post(EndPoints.LOGIN)
                .then()
//                .statusCode(HttpStatus.SC_OK)
//                .log().all()
                .spec(responseSpecification)
                .extract().body().asString().replace("\"", "");

    }

    public void deleteAllPostsTillPresent(String validUsernameApi, String actualToken) {
        PostsDto[] listOfPosts = this.getAllPostsByUserInObject(validUsernameApi, HttpStatus.SC_OK);

        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(actualToken, listOfPosts[i].getId());
            logger.info(
                    String.format("Post with id %s and title %s was deleted "
            ,listOfPosts[i].getId(), listOfPosts[i].getTitle()));
        }
    }

    private void deletePostById(String actualToken, String id) {
        //формуємо ріквест боді для запиту, бо так передаємо токен
        HashMap<String,String> bodyRequest = new HashMap<>();
        bodyRequest.put("token", actualToken);

        given()
                .spec(requestSpecification)
                .body(bodyRequest)
                .when()
                .delete(EndPoints.DELETE_POST, id)
                .then()
                .spec(responseSpecification);

    }
}
