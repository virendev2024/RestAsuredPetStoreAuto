package api.endpoints;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestConnection {
    public static void main(String[] args) {
        String url = "https://httpbin.org/get"; // Change to your endpoint
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(20000); // 20 seconds
            connection.setReadTimeout(20000); // 20 seconds
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}