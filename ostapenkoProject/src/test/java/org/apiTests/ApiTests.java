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

import java.util.logging.SocketHandler;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;

public class ApiTests extends BaseTestApi {
    String sharedUserName = "autoapi";
    Logger logger = Logger.getLogger(getClass());
    ApiHelper apiHelper = new ApiHelper();

    @Test
    public void getPostsByUser() {
        PostsDto[] acttualResponse =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(EndPoints.POSTS_BY_USER, sharedUserName) // TODO: add URL
                        .then()
                        .statusCode(200)
                        .log().all()
                        // method #1: RestAssured assertions
                        .body("[0].title", equalTo("The second Default post"))
                        .body("author.username", everyItem(equalTo(sharedUserName)))
                        // method #2: DTO or POJO assertions
                        .extract().body().as(PostsDto[].class);

        logger.info("Full response: " + acttualResponse[0]);
        logger.info("Number of posts: " + acttualResponse.length);
        logger.info("Title of the first post: " + acttualResponse[0].getTitle());
        logger.info("Author of the first post: " + acttualResponse[0].getAuthor().getUsername());

        for (int i = 0; i < acttualResponse.length; i++) {
            Assert.assertEquals("Usernme is not expected " + i, acttualResponse[i].getAuthor().getUsername(), sharedUserName);
        }

        PostsDto[] expectedResult = {
                new PostsDto("The second Default post",
                        "This post was created automatically after cleaning the database",
                        "All Users", "no", new AuthorDto(sharedUserName), false),

                new PostsDto("The first Default post",
                        "This post was created automatically after cleaning the database",
                        "All Users", "no", new AuthorDto(sharedUserName), false),
        };

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions
                .assertThat(acttualResponse)
                .usingRecursiveComparison()
                .ignoringFields("id", "createdDate", "author.avatar")
                .isEqualTo(expectedResult);

        softAssertions.assertAll();
    }

    @Test
    public void getPostsByUserNegativeTest() {
        final String NOT_VALID_USER_NAME = "nonExistingUser";

        String actualResult =
                apiHelper
                        .getAllPostsByUserRequest(NOT_VALID_USER_NAME, HttpStatus.SC_BAD_REQUEST)
                        .extract().body().asString();

        Assert.assertEquals("Error message is not expected",
                "\"Sorry, invalid user requested. Wrong username - " + NOT_VALID_USER_NAME + " or there is no posts. Exception is undefined\"", actualResult);

    }

}
