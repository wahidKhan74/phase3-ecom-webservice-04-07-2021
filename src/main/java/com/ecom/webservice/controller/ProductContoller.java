package com.ecom.webservice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.webservice.entity.Product;
import com.ecom.webservice.exception.InvalidProductException;
import com.ecom.webservice.exception.ProductAlreadyExistException;
import com.ecom.webservice.exception.ProductNotFoundException;
import com.ecom.webservice.repository.ProductRepository;

@RestController
@RequestMapping(value = "/api/")
public class ProductContoller {

	@Autowired
	private ProductRepository productRepo;
	// crud operations for product

	// get one product
	@RequestMapping(value = "/products/{id}", method=RequestMethod.GET)
	public Product getOneProduct(@PathVariable("id") long id) {
		return this.productRepo.findById(id).orElseThrow(()->{
			throw new ProductNotFoundException();
		});
	}


	// get all products
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public List<Product> getAllProducts() {
		return this.productRepo.findAll();
	}

	// create product
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public Product addProduct(@RequestBody(required=false) Product productObj){
		if(productObj == null) {
			throw new InvalidProductException();
		}
		return this.productRepo.save(productObj);
	}
	
	// update product
	@RequestMapping(value = "/products/{id}", method= RequestMethod.PUT)
	public Product updateOneProduct(@PathVariable("id") long id, @RequestBody(required=false) Product productObj) {
		//find record 
		this.productRepo.findById(id).orElseThrow(()->{
			throw new ProductNotFoundException();
		});
		return this.productRepo.save(productObj);
	}
	
	// delete product
	@RequestMapping(value = "/products/{id}", method=RequestMethod.DELETE)
	public void deleteOneProduct(@PathVariable("id") long id) {
		
		// verify product exist
		Product fetchedProduct = this.productRepo.findById(id).orElseThrow(()->{
			throw new ProductNotFoundException();
		});
		
		this.productRepo.delete(fetchedProduct);
		
	}

}
