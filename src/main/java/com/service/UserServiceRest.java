package com.service;

import com.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserServiceRest implements UserService {

    private final RestTemplate restTemplate;
    private final String serverUrl;
    private HttpHeaders headers;

    public UserServiceRest(RestTemplate restTemplate, @Value("${application.server.url}") String serverUrl) {
        this.restTemplate = restTemplate;
        this.serverUrl = serverUrl;
        this.headers = new HttpHeaders();
    }

    @Override
    public String getAllUsers() {
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<User[]> response = restTemplate.exchange(serverUrl, HttpMethod.GET, entity, User[].class);
        String body = response.getBody().toString();
        User[] users = response.getBody();
        headers.set("Cookie", response.getHeaders().get("Set-Cookie").get(0).split(";")[0]);
        for(User user: users) {
            System.out.println(user);
        }
        return body;
    }

    @Override
    public String addUser() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 3);
        map.put("name", "James");
        map.put("lastName", "Brown");
        map.put("age", 29);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(serverUrl, entity, String.class);
        return response.getBody();
    }
    @Override
    public String editUser() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 3);
        map.put("name", "Thomas");
        map.put("lastName", "Shelby");
        map.put("age", 15);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.exchange(serverUrl, HttpMethod.PUT, entity, String.class);
        return response.getBody();
    }
    @Override
    public String deleteUser() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 3);
        map.put("name", "Thomas");
        map.put("lastName", "Shelby");
        map.put("age", 15);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.exchange(serverUrl+"/3", HttpMethod.DELETE, entity, String.class);
        return response.getBody();
    }
}