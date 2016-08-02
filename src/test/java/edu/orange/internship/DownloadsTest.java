package edu.orange.internship;

import cucumber.api.PendingException;
import cucumber.api.java.en.*;
import cucumber.api.java.eo.Do;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mustafa on 8/1/2016.
 */
public class DownloadsTest {

    private Downloader downloader;

    @Given("^Downloader$")
    public void downloader() throws Throwable {
        downloader = new Downloader();
    }

    @When("^enters an \"([^\"]*)\"$")
    public void invalidUrl (String url) throws Exception {
        downloader.enterUrl(url);
    }


    @Then("^print error$")
    public void printError() throws Throwable {
        assertEquals(false,downloader.state);
    }


    @Then("^download$")
    public void download() throws Throwable {
        assertEquals(true,downloader.download("out.html"));
    }

}
