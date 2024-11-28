package utils;

import io.restassured.response.Response;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static constants.Constants.*;
import static io.restassured.RestAssured.get;

public class ApiUtils {
    public static Response getAstronomyPictureOfDay(String date) {
        return get(NASA_API_BASE_URL + AND_DATE + date);
    }

    public static String getYesterdayDate() {
        return LocalDate.now().minusDays(1).format(DateTimeFormatter.ISO_DATE);
    }

    public static Response getPublicIp() {
        return get(PUBLIC_IP_API_URL);
    }
}
