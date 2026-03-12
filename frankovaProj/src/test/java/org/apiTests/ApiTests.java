package org.apiTests;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.api.ApiHelper;
import org.api.EndPoints;
import org.api.dto.responseDto.AuthorDto;
import org.api.dto.responseDto.PostsDto;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;

public class ApiTests extends BaseTestApi {

    String sharedUserName = "autoapi";

    Logger logger = Logger.getLogger(getClass());

    ApiHelper apiHelper = new ApiHelper();

    @Test
    public void getAllPostsByUser() {

        PostsDto[] actualResponse = given()
//                .contentType(ContentType.JSON)
//                .log().all()
                .when()
                .get(EndPoints.POSTS_BY_USER, sharedUserName)
                .then()
                .statusCode(200)
                .log().all()
                //method #1 RestAssured asserts
                .body("[0].title", equalTo("The second Default post"))
                .body("author.username", everyItem(equalTo(sharedUserName)))
                //method #2 DTO or POJO
                .extract().body().as(PostsDto[].class);


        logger.info("Full response body = " + actualResponse[0].toString());
        logger.info("Number of posts = " + actualResponse.length);
        logger.info("Title[0] = " + actualResponse[0].getTitle());
        logger.info("[0].UserName = " + actualResponse[0].getAuthor().getUsername());

        for (int i = 0; i < actualResponse.length; i++) {
           /* Assert.assertEquals("UserName is not expected" =  i
                    , sharedUserName
                    , actualResponse[i].getAuthor().getUsername());*/
        }

        PostsDto[] expectedResult = {
                new PostsDto("The second Default post",
                        "This post was created automatically after cleaning the database",
                        "All Users", "no", new AuthorDto(sharedUserName), false
                ),
                new PostsDto("The first Default post",
                        "This post was created automatically after cleaning the database",
                        "All Users", "no", new AuthorDto(sharedUserName), false
                )
        };

        //додаткова бібліотека для незвич перевірок
        //виведуться філди які не співпали. Пофілдово порівняє 2 об*єкти
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions
                .assertThat(actualResponse)
                .usingRecursiveComparison()
                .ignoringFields("id", "createdDate", "author.avatar")
                .isEqualTo(expectedResult);


        softAssertions.assertAll();
    }

    @Test
    public void getAllPostsByUserNegative(){
        final String NOT_VALID_USER_NAME = "notValidUserName";

        String actualResult =
                apiHelper.getAllPostsByUserRequest(NOT_VALID_USER_NAME, HttpStatus.SC_BAD_REQUEST)
                        .extract().response().body().asString();

        Assert.assertEquals("Message in response"
                ,"\"Sorry, invalid user requested. Wrong username - " + NOT_VALID_USER_NAME +" or there is no posts. Exception is undefined\""
                , actualResult
                );
    }
}
