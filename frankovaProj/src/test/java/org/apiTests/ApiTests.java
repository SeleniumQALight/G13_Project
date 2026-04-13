package org.apiTests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.api.ApiHelper;
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

@Category(SmokeTestsFilter.class) //додаємо у Смоук сьют для рану
public class ApiTests extends BaseTestApi {

    String sharedUserName = "autoapi";

    Logger logger = Logger.getLogger(getClass());

    ApiHelper apiHelper = new ApiHelper();

    @Test
    public void getAllPostsByUser() {

        PostsDto[] actualResponse = given()//записуємо ріспонс у обєкт PostsDto[].class, тому що ріспонс - це масив обєктів
                .contentType(ContentType.JSON)
               .log().all()
                .filter(new AllureRestAssured())//щоб у Алюр репорті були дані по запиту
                .when()
                .get(EndPoints.POSTS_BY_USER, sharedUserName)
                .then()
                .statusCode(200)
                .log().all()

                //method #1 RestAssured asserts - якщо перевірити деякі поля лише
                .body("[0].title", equalTo("The second Default post"))
                .body("author.username", everyItem(equalTo(sharedUserName)))

                //method #2 DTO or POJO - для перевірки всього ріспонса, якщо багато полів і складна структура.
                // Потрібно створити класи, які відповідають структурі ріспонса
                .extract().body().as(PostsDto[].class); //витягуємо ріспонс і записуємо його в масив обєктів PostsDto




        logger.info("Full response body = " + actualResponse[0].toString());
        logger.info("Number of posts = " + actualResponse.length);
        logger.info("Title[0] = " + actualResponse[0].getTitle());
        logger.info("[0].UserName = " + actualResponse[0].getAuthor().getUsername());

        for (int i = 0; i < actualResponse.length; i++) {
            Assert.assertEquals("UserName is not expected" + i
                    , sharedUserName
                    , actualResponse[i].getAuthor().getUsername());
        }

        //формуємо очікуваний результат - вказуємо тут поля, які точно є і будемо порівнювати з отриманим
        // результатом - тра додати відповідний конструктор в клас PostsDto

        // ДТО коли маємо lobmok
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
                        .build()

                        // Без lombok приклад створення ДТО
//                new PostsDto("The second Default post",
//                        "This post was created automatically after cleaning the database",
//                        "All Users", "no", new AuthorDto(sharedUserName), false
//                ),
//                new PostsDto("The first Default post",
//                        "This post was created automatically after cleaning the database",
//                        "All Users", "no", new AuthorDto(sharedUserName), false
//                )
        };

        //додаткова бібліотека для незвич перевірок
        //виведуться філди які не співпали. Пофілдово порівняє 2 об*єкти
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions
                .assertThat(actualResponse)
                .usingRecursiveComparison()//рекурсивно порівнює всі поля обєктів, які знаходяться всередині масиву, перевіряємо у джейсоні і вкладені масиви-елементи
                .ignoringFields("id", "createdDate", "author.avatar")
                .isEqualTo(expectedResult);


        softAssertions.assertAll();
    }

    @Test
    public void getAllPostsByUserNegative() {
        final String NOT_VALID_USER_NAME = "notValidUserName";

        String actualResult =
                apiHelper.getAllPostsByUserRequest(NOT_VALID_USER_NAME, HttpStatus.SC_BAD_REQUEST)

                        //method #3 response as String
                        .extract().response().body().asString();

        Assert.assertEquals("Message in response"
                , "\"Sorry, invalid user requested. Wrong username - " + NOT_VALID_USER_NAME + " or there is no posts. Exception is undefined\""
                , actualResult
        );
    }

    @Test
    public void getPostsByUserJsonPath(){
        //method #4 response using json path
        //коли потрібно достати частину джейсон а немає ДТО

        //Response використовуємо
        Response actualResponse =
                apiHelper.getAllPostsByUserRequest(sharedUserName, HttpStatus.SC_OK)
                .extract().response();

        SoftAssertions softAssertions =  new SoftAssertions();

        //ріспонс нам вертає список постів, а ми хочемо витягнути лише їх татйтли і формуємо з них список
        List<String> actualListOfTitle = actualResponse.jsonPath().getList("title", String.class);

        for (int i = 0; i < actualListOfTitle.size(); i++) {

            softAssertions.assertThat(actualListOfTitle.get(i))
                    .as("Item number " + i)
                    .contains("Default post");

        }

        //аналогічно витягуємо з ріспонса Автора - що є окремим об*єктом - тому викор Map
        List<Map> actualAuthorList = actualResponse.jsonPath().getList("author", Map.class);

        for (Map actualAuthorObject: actualAuthorList) {
            softAssertions
                    .assertThat(actualAuthorObject.get("username"))
                    .as("Field userName in Author")
                    .isEqualTo(sharedUserName);
        }

        softAssertions.assertAll();

    }


//Тест на валідацію схеми
    @Test
    public void getAllPostsByUserSchemaValidation(){
        apiHelper.getAllPostsByUserRequest(sharedUserName, HttpStatus.SC_OK)
                .assertThat().body(matchesJsonSchemaInClasspath("response.json"));
        //response.json файл у ресорсес додаємо - описана схема
    }



}
