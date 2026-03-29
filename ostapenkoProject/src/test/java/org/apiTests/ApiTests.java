package org.apiTests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.api.dto.helpers.ApiHelper;
import org.api.EndPoints;
import org.api.dto.responseDto.AuthorDto;
import org.api.dto.responseDto.PostsDto;
import org.assertj.core.api.SoftAssertions;
import org.categories.SmokeTestsFilter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;

@Category(SmokeTestsFilter.class)
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
                        .filter(new AllureRestAssured())
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
                PostsDto.builder()
                        .title("The second Default post")
                        .body("This post was created automatically after cleaning the database")
                        .select("All Users")
                        .uniquePost("no")
                        .author(new AuthorDto(sharedUserName))
                        .isVisitorOwner(false)
                        .build(),

                PostsDto.builder()
                        .title("The first Default post")
                        .body("This post was created automatically after cleaning the database")
                        .select("All Users")
                        .uniquePost("no")
                        .author(new AuthorDto(sharedUserName))
                        .isVisitorOwner(false)
                        .build(),

//                new PostsDto("The second Default post",
//                        "This post was created automatically after cleaning the database",
//                        "All Users", "no", new AuthorDto(sharedUserName), false),
//
//                new PostsDto("The first Default post",
//                        "This post was created automatically after cleaning the database",
//                        "All Users", "no", new AuthorDto(sharedUserName), false),
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
                        // method #3: response as String
                        .extract().body().asString();

        Assert.assertEquals("Error message is not expected",
                "\"Sorry, invalid user requested. Wrong username - " + NOT_VALID_USER_NAME
                        + " or there is no posts. Exception is undefined\"", actualResult);
    }

    @Test
    public void getPostsByUserJsonPath() {
        // method #4: JsonPath assertions
        Response actualResponse = apiHelper
                .getAllPostsByUserRequest(sharedUserName, HttpStatus.SC_OK)
                .extract().response();

        SoftAssertions softAssertions = new SoftAssertions();

        List<String> actualListOfTitles = actualResponse.jsonPath().getList("title", String.class);
        for (int i = 0; i < actualListOfTitles.size(); i++) {
            softAssertions.assertThat(actualListOfTitles.get(i))
                    .as("Title of post with index " + i)
                    .contains("Default post");
        }

        List<Map> actualAuthorsList = actualResponse.jsonPath().getList("author", Map.class);
        for (Map author : actualAuthorsList) {
            softAssertions.assertThat(author.get("username"))
                    .as("Field username in author object")
                    .isEqualTo(sharedUserName);

        }
        softAssertions.assertAll();
    }

    @Test
    public void getAllPostsByUserSchemaValidation() {
        apiHelper.getAllPostsByUserRequest(sharedUserName, HttpStatus.SC_OK)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("response.json"));

    }
}