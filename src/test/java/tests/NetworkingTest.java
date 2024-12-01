package tests;

import utils.ApiUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

import static constants.Constants.*;
import static helper.CommonValues.*;
import static org.junit.jupiter.api.Assertions.*;

public class NetworkingTest {

    public void checkIfIHaveAccessToTheInternet() {
        try {
            URL url = new URL(TEST_URL_GOOGLE);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(GET);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();

            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed to access the internet. HTTP Response Code: " + responseCode);
            }

            System.out.println("Successfully connected to the internet.");

        } catch (Exception e) {
            throw new RuntimeException("Internet access check failed: " + e.getMessage(), e);
        }
    }

    public void retrieveMyPublicIpAddress() {
        response = ApiUtils.getPublicIp();
    }

    public void validateIpIsNotInTheRange() {
        String publicIp = response.getBody().asString().trim();
        String[] ipParts = publicIp.split(DOT_REGEX);
        int thirdOctet = Integer.parseInt(ipParts[2]);
        assertFalse(thirdOctet >= 28 && thirdOctet <= 29);
    }

    public void resolveTheDomain(String domain) {
        try {
            InetAddress inetAddress = InetAddress.getByName(domain);
            resolvedIp = inetAddress.getHostAddress();
        } catch (Exception e) {
            fail("Domain resolution failed: " + e.getMessage());
        }
    }

    public void validateResolvedIp(String expectedIp) {
        assertEquals(expectedIp, resolvedIp);
    }

    public void performATracerouteToIP(String targetIp) {
        String command = ApiUtils.getTracerouteOutput();

        try {
            Process process = Runtime.getRuntime().exec(command + targetIp);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            hopCount = 0;

            while (reader.readLine() != null) {
                hopCount++;
            }
            process.waitFor();
        } catch (Exception e) {
            fail("Traceroute failed: " + e.getMessage());
        }
    }

    public void validateThatTargetReachedWithinTenHops() {
        assertTrue(hopCount <= 10, "Target was not reached within 10 hops");
    }
}
