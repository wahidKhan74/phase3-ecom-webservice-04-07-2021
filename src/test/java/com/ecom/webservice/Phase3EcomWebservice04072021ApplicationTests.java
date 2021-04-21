package com.ecom.webservice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.ecom.webservice.controller.ProductContoller;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@DisplayName(" Test  :: Main Application Test")
class Phase3EcomWebservice04072021ApplicationTests {

	@Autowired
	private ProductContoller contoller;
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@LocalServerPort
	private int randomPort;
	
	
	@Test
	@DisplayName(" Test  :: Load Application Context")
	void contextLoads() {
		assertNotNull(contoller);
	}
	
	@Test
	@DisplayName("Test :: Server Running test")
	void testForRunningServer() {
		String url = "http://localhost:"+randomPort+"/";
		ResponseEntity<String> response = testRestTemplate.getForEntity(url, String.class);
		
		assertEquals("Server is running !", response.getBody());
		assertEquals(200, response.getStatusCodeValue());
	}

}
