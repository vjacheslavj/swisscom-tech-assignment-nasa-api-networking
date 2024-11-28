package tests;

import utils.ApiUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;

import static constants.Constants.*;
import static helper.CommonValues.*;
import static org.junit.jupiter.api.Assertions.*;

public class NetworkingTest {

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
        try {
            Process process = Runtime.getRuntime().exec("traceroute " + targetIp);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            int hopCount = 0;

            while ((line = reader.readLine()) != null) {
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
