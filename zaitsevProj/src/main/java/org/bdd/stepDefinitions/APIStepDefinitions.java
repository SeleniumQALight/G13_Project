package org.bdd.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import org.api.ApiHelper;
import org.data.TestData;

import static org.bdd.stepDefinitions.MainSteps.DEFAULT;

public class APIStepDefinitions {
    private ApiHelper apiHelper = new ApiHelper();

    @Given("I create {} new posts via API for {string} user and {string} password")
    public void iCreateNumberOfPostsNewPostsViaAPIForDefaultUserAndDefaultPassword(
            Integer numberOfPosts, String username, String password, DataTable dataTable) {
        if (DEFAULT.equalsIgnoreCase(username)){
            username = TestData.VALID_USERNAME_API;
        }
        if (DEFAULT.equalsIgnoreCase(password)){
            password = TestData.VALID_PASSWORD_API;
        }

        TestData.currentUser = username;

        String actualToken = apiHelper.getToken(username, password);
        apiHelper.createPosts(numberOfPosts, actualToken, dataTable.asMap());
    }
}
