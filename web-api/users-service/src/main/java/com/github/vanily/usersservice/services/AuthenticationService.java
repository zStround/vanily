package com.github.vanily.usersservice.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class AuthenticationService {

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.client_id}")
    private String clientId;

    @Value("${keycloak.client_secret}")
    private String clientSecret;

    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<?> login(String username, String password) {
        String url = "http://keycloak:8080/realms/" + realm + "/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("client_id", clientId);
        requestBody.add("client_secret", clientSecret);
        requestBody.add("grant_type", "password");
        requestBody.add("username", username);
        requestBody.add("password", password);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestBody, headers);

        try {
            return restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    public ResponseEntity<?> register(String username, String password) {

        try {

            String url = "http://keycloak:8080/admin/realms/" + realm + "/users";
            final String accessToken = getAccessToken();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(accessToken);

            Map<String, Object> user = new HashMap<>();
            user.put("username", username);
            user.put("enabled", true);
            user.put("credentials", new Object[] {
                    Map.of("type", "password", "value", password, "temporary", false)
            });

            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(user, headers);
            return restTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class);

        }catch (RestClientException e) {

            if (e instanceof HttpClientErrorException.Conflict)
                return ResponseEntity.status(HttpStatus.CONFLICT).build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    public ResponseEntity<?> exists(String username) {

        try {
            String url = "http://keycloak:8080/admin/realms/" + realm + "/users?username=" + username;
            final String accessToken = getAccessToken();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(accessToken);

            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(new HashMap<>(), headers);
            ResponseEntity<Object[]> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Object[].class);

            if (!response.hasBody() || Objects.requireNonNull(response.getBody()).length == 0)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (RestClientException e) {

            if (e instanceof HttpClientErrorException.NotFound)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }



    }

    private String getAccessToken() {
        String url = "http://keycloak:8080/realms/" + realm + "/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = String.format("client_id=%s&client_secret=%s&grant_type=client_credentials", clientId, clientSecret);

        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<>() {});

        return (String) response.getBody().get("access_token");
    }

}
