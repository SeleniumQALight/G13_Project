package org.apiTests;

import org.api.DemoQaApiHelper;
import org.api.dto.responseDto.DemoQaLoginDto;
import org.assertj.core.api.SoftAssertions;
import org.data.TestData;
import org.junit.Test;

public class DemoQaAddBookTest extends DemoQaBaseTestApi {
    DemoQaApiHelper demoQaApiHelper = new DemoQaApiHelper();

    @Test
    public void addBookToUserTest() {

        DemoQaLoginDto loginResponse = demoQaApiHelper.login(TestData.DEMO_QA_VALID_USERNAME_API, TestData.DEMO_QA_VALID_PASSWORD_API);
        String token = loginResponse.getToken();
        String userId = loginResponse.getUserId();

        demoQaApiHelper.deleteAllBooksByUser(token, userId);

        String firstBookIsbn = demoQaApiHelper.getAllBooks().getBooks().get(0).getIsbn();

        demoQaApiHelper.addBookToUser(token, userId, firstBookIsbn);

        DemoQaLoginDto userInfo = demoQaApiHelper.getUserInfo(token, userId);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions
                .assertThat(userInfo.getBooks())
                .as("Number of books for user")
                .hasSize(1);

        softAssertions
                .assertThat(userInfo.getBooks().get(0).getIsbn())
                .as("ISBN of the added book")
                .isEqualTo(firstBookIsbn);

        softAssertions.assertAll();;
    }


}
