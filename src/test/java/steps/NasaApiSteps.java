package steps;

import io.cucumber.java.en.*;
import tests.NasaApiTest;

public class NasaApiSteps {

    private final NasaApiTest nasaApiTest = new NasaApiTest();
    @Given("I send a request to the NASA API with the {string} date")
    public void iSendARequestToTheNasaApiWithTheDate(String date) {
        nasaApiTest.sendRequestToTheNasaApiWithTheDate(date);
    }

    @Then("the HTTP response code should be {int}")
    public void theHttpResponseCodeShouldBe(int expectedStatusCode) {
        nasaApiTest.validateResponseCode(expectedStatusCode);
    }

    @Then("the response should include {string}, {string}, {string}, {string} and {string}")
    public void theResponseShouldInclude(String title, String explanation, String url, String mediaType, String date) {
        nasaApiTest.validateThatResponseIncludeAllInformation(title, explanation, url, mediaType, date);
    }

    @Then("the media_type should be either {string} or {string}")
    public void theMediaTypeShouldBeEither(String mediaType1, String mediaType2) {
        nasaApiTest.validateMediaType(mediaType1, mediaType2);
    }
}