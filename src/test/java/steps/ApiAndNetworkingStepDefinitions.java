//package steps;
//
//import io.cucumber.java.en.*;
//import io.restassured.response.Response;
//import org.junit.jupiter.api.Assertions;
//import utils.ApiUtils;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.InetAddress;
//import java.util.Arrays;
//import java.util.Map;
//
//import static constants.Constants.*;
//import static org.junit.jupiter.api.Assertions.*;
//import static helper.CommonValues.*;
//
//public class ApiAndNetworkingStepDefinitions {
//
//    // Task 1: NASA API Interaction
//    @Given("I send a request to the NASA API with the date {string}")
//    public void iSendARequestToTheNasaApiWithTheDate(String date) {
//        response = ApiUtils.getAstronomyPictureOfDay(date.equals(YESTERDAY) ? ApiUtils.getYesterdayDate() : date);
//    }
//
//    @Then("the HTTP response code should be {int}")
//    public void theHttpResponseCodeShouldBe(int expectedStatusCode) {
//        assertEquals(expectedStatusCode, response.getStatusCode());
//    }
//
//    @Then("the response should include {string}, {string}, {string}, {string}, and {string}")
//    public void theResponseShouldInclude(String title, String explanation, String url, String mediaType, String date) {
//        Map<String, Object> responseBody = response.jsonPath().getMap(EMPTY);
//        for (String key : Arrays.asList(title, explanation, url, mediaType, date)) {
//            assertTrue(responseBody.containsKey(key), "Response does not contain: " + key);
//        }
//    }
//
//    @Then("the media_type should be either {string} or {string}")
//    public void theMediaTypeShouldBeEither(String mediaType1, String mediaType2) {
//        String mediaType = response.jsonPath().getString(JSON_PATH_MEDIA_TYPE);
//        assertTrue(mediaType.equals(mediaType1) || mediaType.equals(mediaType2));
//    }
//
//    // Task 2: Networking Validation
//    @When("I retrieve my public IP address")
//    public void iRetrieveMyPublicIpAddress() {
//        response = ApiUtils.getPublicIp();
//    }
//
//    @Then("it should not be in the range 101.33.28.0 - 101.33.29.0")
//    public void itShouldNotBeInTheRange() {
//        String publicIp = response.getBody().asString().trim();
//        String[] ipParts = publicIp.split(DOT_REGEX);
//        int thirdOctet = Integer.parseInt(ipParts[2]);
//        assertFalse(thirdOctet >= 28 && thirdOctet <= 29);
//    }
//
//    @When("I resolve the domain {string}")
//    public void iResolveTheDomain(String domain) {
//        try {
//            InetAddress inetAddress = InetAddress.getByName(domain);
////            response = io.restassured.RestAssured.given().baseUri(inetAddress.getHostAddress()).get();
//            resolvedIp = inetAddress.getHostAddress();
//        } catch (Exception e) {
//            fail("Domain resolution failed: " + e.getMessage());
//        }
//    }
//
//    @Then("the resolved IP should be {string}")
//    public void theResolvedIpShouldBe(String expectedIp) {
//        assertEquals(expectedIp, resolvedIp);
//    }
//
//    @When("I perform a traceroute to {string}")
//    public void iPerformATracerouteTo(String targetIp) {
//        try {
//            // Execute the traceroute command (works on Linux/macOS, for Windows use "tracert")
//            Process process = Runtime.getRuntime().exec("traceroute " + targetIp);
//
//            // Capture the output of the traceroute command
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String line;
//            int hopCount = 0;
//
//            // Read the output line by line
//            while ((line = reader.readLine()) != null) {
//                // Increment hop count for each line (each line represents a hop)
//                hopCount++;
//            }
//
//            // Wait for the process to complete
//            process.waitFor();
//
//            // Store the hop count for later validation
//        } catch (Exception e) {
//            fail("Traceroute failed: " + e.getMessage());
//        }
//    }
//
//    @Then("the target should be reached within 10 hops")
//    public void theTargetShouldBeReachedWithin10Hops() {
//        // Validate that the number of hops is within the limit
//        assertTrue(hopCount <= 10, "Target was not reached within 10 hops");
//    }
//}

