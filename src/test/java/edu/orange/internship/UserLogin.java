package edu.orange.internship;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by Mustafa on 8/2/2016.
 */
public class UserLogin {
    User currentUser;
    @When("^he enters \"([^\"]*)\"$")
    public void heEntersUser_name(String username) throws Throwable {
        currentUser.setName(username);
    }

    @Then("^print welcome$")
    public void printWelcome() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
    }

    @Given("^user$")
    public void user() throws Throwable {
        currentUser=new User();
    }
}
