package org.apiTests;

import com.github.javafaker.Faker;
import org.api.ApiHelper;
import org.api.EndPoints;
import org.api.dto.requestDto.CreateNewPostDto;
import org.api.dto.responseDto.AuthorDto;
import org.api.dto.responseDto.PostsDto;
import org.assertj.core.api.SoftAssertions;
import org.data.TestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.api.ApiHelper.requestSpecification;
import static org.api.ApiHelper.responseSpecification;

public class CreatePostByAPI extends BaseTestApi {
    ApiHelper apiHelper = new ApiHelper();
    String actualToken;
    Faker faker = new Faker();

    @Before
    public void getTokenAndDeletePosts() {
        actualToken = apiHelper.getToken();
        System.out.println("Token = " + actualToken);
    }


    @Test
    public void createPostByAPI() {
        int initialNumberOfPosts = apiHelper.getAllPostsByUserInObject().length;

        //для створення боді за допом ДТО, щоб не писати багато коду в тесті, а просто заповнити поля і передати цей об'єкт в запит
        CreateNewPostDto createNewPostBody =
                CreateNewPostDto.builder()
                        .title("API post Frankova")
                        .body("Post body " + faker.animal().name())
                        .select1("One Person")
                        .uniquePost("yes")
                        .token(actualToken) //токен потрібно передати в тілі запиту, бо так реалізована логіка бекенду,
                        .build();

        String actualResponse =
                given()
                        .spec(requestSpecification)
                        .body(createNewPostBody)
                        .when()
                        .post(EndPoints.CREATE_POST)
                        .then()
                        .spec(responseSpecification)
                        .extract().response().body().asString();


        Assert.assertEquals("Message in response ", "\"Congrats.\"", actualResponse);

        int newNumberOfPosts = apiHelper.getAllPostsByUserInObject().length;

        Assert.assertEquals("Number of posts Before+1 and After creating new post "
                , initialNumberOfPosts + 1
                , newNumberOfPosts);

        PostsDto expectedPost =
                PostsDto.builder()
                        .title(createNewPostBody.getTitle())
                        .body(createNewPostBody.getBody())
                        .select(createNewPostBody.getSelect1())
                        .uniquePost(createNewPostBody.getUniquePost())
                        .isVisitorOwner(false)
                        .author(AuthorDto.builder()
                                .username(TestData.VALID_USERNAME_API)
                                .build())//беремо автора з першого поста, бо він буде однаковий для всіх постів цього юзера
                        .build();

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(apiHelper.getAllPostsByUserInObject()[0])
                .usingRecursiveComparison()
                .ignoringFields("id", "createdDate", "author.avatar")
                .isEqualTo(expectedPost);

        softAssertions.assertAll();

    }

}
