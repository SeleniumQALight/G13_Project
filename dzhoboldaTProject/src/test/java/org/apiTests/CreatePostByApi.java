package org.apiTests;

import com.github.javafaker.Faker;
import org.api.ApiHelper;
import org.api.EndPoints;
import org.api.dto.CreateNewPostDto;
import org.api.dto.responsDto.AuthorDto;
import org.api.dto.responsDto.PostsDto;
import org.assertj.core.api.SoftAssertions;
import org.data.TestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.api.ApiHelper.requestSpecification;
import static org.api.ApiHelper.responseSpecification;


public class CreatePostByApi extends BaseTestApi {
    ApiHelper apiHelper= new ApiHelper();
    String actualToken;
    Faker faker = new Faker();

    @Before
    public  void getTokenAndDeletePosts(){
        actualToken = apiHelper.getToken();
        apiHelper.deleteAllPostsTillPresent(TestData.VALID_USERNAME_API,actualToken);

    }

    @Test
    public  void createPostByApi(){
        int initialNumberOfPosst = apiHelper.getAllPostsByUserInObject().length;
        CreateNewPostDto createNewPostBody =
                CreateNewPostDto.builder()
                        .title("Post from API")
                        .body("Post body" + faker.animal().name())
                        .uniquePost("yes")
                        .select1("One Person")
                        .token(actualToken)
                        .build();
        String actualResponse =
                given()
                        .spec(ApiHelper.requestSpecification)
                        .body(createNewPostBody)
                        .post(EndPoints.CREATE_POST)
                        .then()
                        .spec(ApiHelper.responseSpecification)
                        .extract().response().body().asString();
        Assert.assertEquals("Message in response ", "\"Congrats.\"",actualResponse);

        int newNumberOfPosts = apiHelper.getAllPostsByUserInObject().length;
        Assert.assertEquals("Number of posts Before +1 and After createing posts",
                initialNumberOfPosst + 1,
                newNumberOfPosts);

        PostsDto expectedPost =
                 PostsDto.builder()
                         .title(createNewPostBody.getTitle())
                         .body(createNewPostBody.getBody())
                         .select1(createNewPostBody.getSelect1())
                         .uniquePost(createNewPostBody.getUniquePost())
                         .isVisitorOwner(false)
                         .author(AuthorDto.builder()
                         .username(TestData.VALID_USERNAME_API)
                         .build())
        .build();
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions
                .assertThat(apiHelper.getAllPostsByUserInObject()[0])
                .usingRecursiveComparison()
                .ignoringFields("id", "createdDate", "author.avatar")
                .isEqualTo(expectedPost);

        softAssertions.assertAll();



    }
}
