package com.carte.archicarte.dao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProassurService {

    private final String apiUrl = "http://192.168.2.246/app_agence/WebUI/Services/WsAssur.aspx?method=getclientbycode&uid=25123804";

    public String fetchDataFromApi() {
        RestTemplate restTemplate = new RestTemplate();

        // Make a GET request to the API endpoint
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        // Retrieve the response body
        String responseBody = responseEntity.getBody();

        // Process or print the response as needed
        System.out.println("API Response: " + responseBody);
        return responseBody;
    }
}

