package com.backendclients;

import java.net.URI;
import org.junit.jupiter.api.Test;
import java.net.URISyntaxException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.junit.jupiter.api.TestMethodOrder;
import com.backendclients.model.entity.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class RestControllerTest {
	
	@Test
	@Order(1)
	public void testAddClientSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + 8080 + "/api/addClient/";
        URI uri = new URI(baseUrl);
        
        Client client = new Client();
        client.setName("Adam");
        client.setSurname("Gilly");
        client.setAge(33);
        client.setEmail("test@email.com");
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        
        HttpEntity<Client> request = new HttpEntity<>(client, headers);
        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
        
        //Verify request succeed
        Assertions.assertEquals(201, result.getStatusCodeValue());
    }
	
	@Test
	@Order(2)
	public void testAddClientFailed() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + 8080 + "/api/addClient/";
        URI uri = new URI(baseUrl);
        
        Client client = new Client();
        client.setName("Adam");
        client.setSurname("Gilly");
        client.setAge(33);
        client.setEmail("test@email.com");
        
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Client> request = new HttpEntity<>(client, headers);
        
        try {
        	restTemplate.postForEntity(uri, request, String.class);
        	Assertions.fail();
        } catch (HttpClientErrorException ex) {
        	Assertions.assertEquals(409, ex.getRawStatusCode());
        	Assertions.assertEquals(true, ex.getResponseBodyAsString().contains(""));
		}
	}
}