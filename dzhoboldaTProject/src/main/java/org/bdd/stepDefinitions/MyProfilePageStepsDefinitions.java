package org.bdd.stepDefinitions;

import io.cucumber.java.en.Then;
import org.bdd.helpers.WebDriverHelper;

public class MyProfilePageStepsDefinitions extends MainSteps{

    public MyProfilePageStepsDefinitions(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }
    @Then("I was redirected to MyProfile page")
    public void i_was_redirected_to_my_profile_page() {
        pageProvider.getMyProfilePage().checkIsRedirectToMyProfilePage();
    }

    @Then("I see {int} posts in Posts list on MyProfile Page")
    public void i_see_posts_in_posts_list_on_my_profile_page(Integer int1) {
        pageProvider.getMyProfilePage().checkNumberOfPosts(int1);
    }
}
