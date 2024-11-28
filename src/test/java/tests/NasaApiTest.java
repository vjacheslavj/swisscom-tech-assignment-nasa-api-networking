package tests;

import utils.ApiUtils;

import java.util.Arrays;
import java.util.Map;

import static constants.Constants.*;
import static helper.CommonValues.response;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NasaApiTest {

    public void sendRequestToTheNasaApiWithTheDate(String date) {
        response = ApiUtils.getAstronomyPictureOfDay(date.equals(YESTERDAY) ? ApiUtils.getYesterdayDate() : date);
    }

    public void validateResponseCode(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    public void validateThatResponseIncludeAllInformation(String title, String explanation, String url, String mediaType, String date) {
        Map<String, Object> responseBody = response.jsonPath().getMap(EMPTY);
        for (String key : Arrays.asList(title, explanation, url, mediaType, date)) {
            assertTrue(responseBody.containsKey(key), "Response does not contain: " + key);
        }
    }

    public void validateMediaType(String mediaType1, String mediaType2) {
        String mediaType = response.jsonPath().getString(JSON_PATH_MEDIA_TYPE);
        assertTrue(mediaType.equals(mediaType1) || mediaType.equals(mediaType2));
    }
}
