package org.apiTests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.api.ApiHelper;
import org.api.EndPoints;
import org.api.dto.responsDto.AuthorDto;
import org.api.dto.responsDto.PostsDto;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;

public class ApiTests extends  BaseTestApi{
    String sharedUserName = "autoapi";
    Logger logger = Logger.getLogger(getClass());
    ApiHelper apiHelper =new ApiHelper();

    @Test
    public  void getAllPostsByUser(){
        PostsDto[] actualResponse =  given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPoints.POSTS_BY_USER,sharedUserName)//URL
                .then()
                .statusCode(200)
                .log().all()
                //method 1 RestAssured asserts
                .body("[0].title",equalTo("The second Default post"))
                .body("author.username",everyItem(equalTo(sharedUserName)))
        //method 2 DTO or POJO
                .extract().body().as(PostsDto[].class);


        logger.info("Number of post = " + actualResponse);
        logger.info("full response body" + actualResponse[0]);
        logger.info("Title [0]=" + actualResponse[0].getTitle());
        logger.info("[0].UserName =" + actualResponse[0].getAuthor().getUsername());

        for (int i = 0; i < actualResponse.length; i++) {

            Assert.assertEquals("UserName is not expected in post " + i,
                    sharedUserName,
                    actualResponse[i].getAuthor().getUsername());

        }

        PostsDto[] expectedResult = {
                PostsDto.builder()
                        .title("The second Default post")
                        .body("This post was created automatically after cleaning the database")
                        .select1("All Users")
                        .uniquePost("no")
                        .author(new  AuthorDto(sharedUserName) )
                        .isVisitorOwner(false)
                        .build(),
                PostsDto.builder()
                        .title("The first Default post")
                        .body("This post was created automatically after cleaning the database")
                        .select1("All Users")
                        .uniquePost("no")
                        .author(new  AuthorDto(sharedUserName) )
                        .isVisitorOwner(false)
                        .build()
//                new PostsDto("The second Default post",
//                        "This post was created automatically after cleaning the database",
//                        "All Users", "no",new AuthorDto(sharedUserName),false),
//                new PostsDto("The first Default post",
//                        "This post was created automatically after cleaning the database",
//                        "All Users", "no",new AuthorDto(sharedUserName),false)
        };

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actualResponse)
                .usingRecursiveComparison()
                .ignoringFields("id","createdDate","author.avatar")
        .isEqualTo(expectedResult);

        softAssertions.assertAll();


    }

    @Test
    public void getAllPostsByUserNegative(){
        final String NOT_VALID_USER_NAME = "NotValidUser";

        String actualResalt = apiHelper.getAllPostsByUserRequest(NOT_VALID_USER_NAME, HttpStatus.SC_BAD_REQUEST)
                .extract().response().body().asString();

        Assert.assertEquals(
                "Message in response",
                "Sorry, invalid user requested. Wrong username - "
                        + NOT_VALID_USER_NAME
                        + " or there is no posts. Exception is undefined",
                actualResalt.replace("\"","")
        );

//        Assert.assertEquals("Message in response",
//                "\"Sorry, invalid user requested. Wrong username -"+NOT_VALID_USER_NAME+"or there is no posts. " +
//                        "Exception is undefined",actualResalt);


    }

    @Test
    public void getPostsByUserJsonPath(){
        Response actualResponse = apiHelper.getAllPostsByUserRequest(sharedUserName,HttpStatus.SC_OK)
                .extract().response();

        SoftAssertions softAssertions =new SoftAssertions();

        List<String> actualListOfTitle = actualResponse.jsonPath().getList("title", String.class);
        for (int i = 0; i <actualListOfTitle.size() ; i++) {
            softAssertions.assertThat(actualListOfTitle.get(i))
                    .as("Item number" + i)
                    .contains("Default post");

        }

        List<Map>actualAuthorList = actualResponse.jsonPath().getList("author",Map.class);
        for (Map actualAuthorObject:actualAuthorList) {

            softAssertions
                    .assertThat(actualAuthorObject.get("username"))
                    .as("Field userName in Author")
                    .isEqualTo(sharedUserName);
        }

        softAssertions.assertAll();
    }

}
