package com.ecom.webservice.controller;

import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import com.ecom.webservice.entity.Product;
import com.ecom.webservice.exception.ProductNotFoundException;


@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@DisplayName(" Test  :: Product Controller")
public class ProductControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@LocalServerPort
	private int randomPort;
	
	
	@Test
	@DisplayName("Test :: List all products")
	public void testGetAllProducts() {
		String url = "http://localhost:"+randomPort+"/products";
		// Get all products
		ResponseEntity<List> response = testRestTemplate.getForEntity(url, List.class);
	
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(false, response.getBody().isEmpty());
		
	}
	
	@Test
	@DisplayName("Test :: Add Product")
	@Disabled
	public void testAddProduct() {
		
		String url = "http://localhost:"+randomPort+"/products";
		// create product
		Product product = new Product("Lenovo Laptop XYZ series", 87675.77, "It is a laptop");
		
		// Http request entity obj  -> for passing product data as request body.
		HttpEntity<Product> requestObj = new HttpEntity<>(product);
		
		// Add products
		ResponseEntity<Product> response = testRestTemplate.postForEntity(url, requestObj, Product.class);
		
		assertEquals(200, response.getStatusCodeValue());
		assertEquals("Lenovo Laptop XYZ series", response.getBody().getName());
		assertEquals(87675.77, response.getBody().getPrice());
	}
	
	@Test
	@DisplayName("Test :: Add Product Null value")
	public void testAddProductWithNull() {
		
		String url = "http://localhost:"+randomPort+"/products";
		// create product
		Product product =  null;
		
		// Http request entity obj  -> for passing product data as request body.
		HttpEntity<Product> requestObj = new HttpEntity<>(product);
		
		// Add products
		ResponseEntity<Product> response = testRestTemplate.postForEntity(url, requestObj, Product.class);
		
		assertEquals(415, response.getStatusCodeValue());
		
	}

	@Test
	@DisplayName("Test :: Get One product")
	public void testGetOneProducts() {
		String url = "http://localhost:"+randomPort+"/products/12";
		// Get all products
		ResponseEntity<Product> response = testRestTemplate.getForEntity(url, Product.class);
	
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(12, response.getBody().getId());
		assertEquals("Lenovo Laptop XYZ series", response.getBody().getName());
		assertEquals(87675.77, response.getBody().getPrice());
		
	}
	
	@Test
	@DisplayName("Test :: Get One product which does not exist")
	public void testGetOneProductsForNotExist() {
		String url = "http://localhost:"+randomPort+"/products/120";
		// Get all products
		ResponseEntity<String> response = testRestTemplate.getForEntity(url, String.class);
	
		assertEquals(404, response.getStatusCodeValue());
	}
	
	// TODO : update and delete products
	
}
