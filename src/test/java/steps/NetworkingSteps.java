package steps;
import io.cucumber.java.en.*;
import tests.NetworkingTest;
import io.cucumber.java.en.Given;

public class NetworkingSteps {

    private final NetworkingTest networkingTest = new NetworkingTest();

    @Given("I have access to the internet")
    public void iHaveAccessToTheInternet() {
        networkingTest.checkIfIHaveAccessToTheInternet();
    }

    @When("I retrieve my public IP address")
    public void iRetrieveMyPublicIpAddress() {
        networkingTest.retrieveMyPublicIpAddress();
    }

    @Then("^it should not be in the range (.*) - (.*)$")
    public void itShouldNotBeInTheRange(String notUsed, String secondNotUsed) {
        networkingTest.validateIpIsNotInTheRange();
    }

    @When("I resolve the domain {string}")
    public void iResolveTheDomain(String domain) {
        networkingTest.resolveTheDomain(domain);
    }

    @Then("the resolved IP should be {string}")
    public void theResolvedIpShouldBe(String expectedIp) {
        networkingTest.validateResolvedIp(expectedIp);
    }

    @When("I perform a traceroute to {string}")
    public void iPerformATracerouteTo(String targetIp) {
        networkingTest.performATracerouteToIP(targetIp);
    }

    @Then("the target should be reached within 10 hops")
    public void theTargetShouldBeReachedWithinTenHops() {
        networkingTest.validateThatTargetReachedWithinTenHops();
    }
}