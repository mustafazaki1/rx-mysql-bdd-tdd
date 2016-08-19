package edu.orange.internship;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mustafa on 8/1/2016.
 */
public class DownloadsTest {

    private Manager manager;
    String command;

    @Given("^User$")
    public void downloader() throws Throwable {
        manager = new Manager();
    }

    @When("^enters an \"([^\"]*)\"$")
    public void invalidUrl (String url) throws Throwable {
        command = url + " temp.html";
    }


    @Then("^print error$")
    public void printError() throws Throwable {
        assertFalse(manager.downloadURL(command));
    }


    @Then("^download$")
    public void download() throws Throwable {
        assertTrue(manager.downloadURL(command));
    }

}
