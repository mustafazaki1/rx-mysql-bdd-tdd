package edu.orange.internship;

import cucumber.api.PendingException;
import cucumber.api.java.en.*;

/**
 * Created by Yusuf on 8/6/2016.
 */
public class HistorySteps {
    User currentUser;
    @Given("^a user$")
    public void user() throws Throwable {
        currentUser =new User();
    }

    @When("^he enters \"([^\"]*)\"$")
    public void heEntersUser_name(String username) throws Throwable {
        currentUser.setName(username);
    }

    @Then("^print welcome$")
    public void printWelcome() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("^I view user's history$")
    public void iViewUserSHistoryUrlList() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
