package com.sdet.task;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class GraphQLRequest {
    private static final String CONFIG_FILE = "config.properties";
    private static final String API_URL_PROPERTY = "api_url";

    public static String executeQuery(String query) throws IOException {
        String apiUrl = getConfigProperty(API_URL_PROPERTY);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(apiUrl);
        request.setEntity(new StringEntity(query));

        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            try (InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                return result.toString();
            }
        }
        return null;
    }

    private static String getConfigProperty(String propertyName) throws IOException {
        InputStream inputStream = GraphQLRequest.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties.getProperty("api_url");
    }
}
