package io.github.jefflegendpower;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
	// write your code here

        var values = new HashMap<String, String>() {{
            put("computerToken", "4a8c44f9-4a59-4286-860a-b58e4005cb25");
            put("gameToken", "ddb1c33c-ea19-42e6-ae99-c34ff4b0a8f8");
        }};

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(values);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:3000/authenticate"))
//                .uri(URI.create("http://httpbin.org/post"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        System.out.println(requestBody);

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}
