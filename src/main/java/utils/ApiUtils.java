package utils;

import io.restassured.response.Response;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import static constants.Constants.*;
import static io.restassured.RestAssured.get;

public class ApiUtils {

    public static String getYesterdayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_YEAR_MONTH_DAY);
        return dateFormat.format(calendar.getTime());
    }

    public static Response getPublicIp() {
        return get(PUBLIC_IP_API_URL);
    }

    public static String getTracerouteOutput() {
        String command;
        String os = System.getProperty(OS_NAME).toLowerCase();
        if (os.contains(WINDOWS_OS)) {
            command = WINDOWS_COMMAND;
        } else {
            command = LINUX_MAC_COMMAND;
        }
        return command;
    }
}
